package web.app.moldunity.service.image;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import web.app.moldunity.util.ImageConverterUtil;
import web.app.moldunity.util.ImageFormatUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ImageConvertService {
    public Mono<List<File>> convertImages(List<File> images) {
        if (images == null || images.isEmpty()) {
            return Mono.just(Collections.emptyList());
        }

        return Flux.fromIterable(images)
                .flatMap(f -> convertImageAsync(f)
                        .doOnError(e -> log.warn("Failed to convert image: {}", f.getName(), e))
                        .onErrorResume(e -> Mono.empty()))
                .collectList()
                .flatMap(convertedImages -> {
                    for (File f : images) {
                        if (!f.delete()) {
                            log.warn("Failed to delete file immediately: {}", f.getName());
                        }
                    }
                    return Mono.just(convertedImages);
                })
                .onErrorResume(e -> {
                    log.error("Error processing images", e);
                    return Mono.just(Collections.emptyList());
                });
    }

    private static Mono<File> convertImageAsync(File f) {
        return Mono.defer(() -> {
            String baseName = f.getName();
            String inputPath = f.getAbsolutePath();
            String outputWEBPPath = "/tmp/" + baseName + ".webp";

            return Mono.fromCallable(() -> {
                boolean heic = ImageFormatUtil.isHEIC(f);
                boolean jpg  = ImageFormatUtil.isJPEG(f);
                boolean png  = ImageFormatUtil.isPNG(f);
                boolean webp = ImageFormatUtil.isWebP(f);

                if (heic) {
                    String outputJPG = "/tmp/" + baseName + ".jpg";
                    if (ImageConverterUtil.convertFromHeic(inputPath, outputJPG, 80)) {
                        if (ImageConverterUtil.convertToWebp(outputJPG, outputWEBPPath, 80, 960, 540)) {
                            File fjpg = new File(outputJPG);
                            if(!fjpg.delete()){
                                log.warn("Error to delete output jpg file: " + outputJPG);
                                fjpg.deleteOnExit();
                            }
                            return new File(outputWEBPPath);
                        } else {
                            log.error("Error converting HEIC to WebP: " + inputPath);
                            throw new RuntimeException("Failed HEIC to WebP conversion");
                        }
                    } else {
                        log.error("Error converting HEIC to JPG: " + inputPath);
                        throw new RuntimeException("Failed HEIC to JPG conversion");
                    }
                } else if (jpg || png || webp) {
                    if (ImageConverterUtil.convertToWebp(inputPath, outputWEBPPath, 80, 960, 540)) {
                        return new File(outputWEBPPath);
                    } else {
                        log.error("Error converting to WebP: " + inputPath);
                        throw new RuntimeException("Failed WebP conversion");
                    }
                }

                throw new RuntimeException("Unsupported image format");

            }).subscribeOn(Schedulers.boundedElastic());
        });
    }

    public Mono<List<File>> convertAllImages(List<File> images) {
        if (images == null || images.isEmpty()) {
            return Mono.just(Collections.emptyList());
        }

        return Mono.fromCallable(() -> {
                    List<File> outputWebp = new ArrayList<>();

                    // Categorize images
                    Map<String, List<File>> categorized = images.stream()
                            .collect(Collectors.groupingBy(file -> {
                                if(ImageFormatUtil.isHEIC(file)) return "heic";
                                if(ImageFormatUtil.isJPEG(file)) return "jpg";
                                if(ImageFormatUtil.isPNG(file))  return "png";
                                if(ImageFormatUtil.isWebP(file)) return "webp";
                                return "other";
                            }));

                    // Process HEIC images
                    List<File> heicFiles = categorized.getOrDefault("heic", Collections.emptyList());
                    for (File f : heicFiles) {
                        String baseName = f.getName();
                        String inputPath = f.getAbsolutePath();
                        String outputJPG = "/tmp/" + baseName + ".jpg";
                        String outputWEBP = "/tmp/" + baseName + ".webp";

                        if (ImageConverterUtil.convertFromHeic(inputPath, outputJPG, 80)) {
                            if (ImageConverterUtil.convertToWebp(outputJPG, outputWEBP, 80, 800, 600)) {
                                outputWebp.add(new File(outputWEBP));
                                File fjpg = new File(outputJPG);
                                if(!fjpg.delete()){
                                    log.warn("Error to delete output jpg file: " + outputJPG);
                                    fjpg.deleteOnExit();
                                }
                            } else {
                                log.error("Error converting to WebP: " + outputJPG);
                                throw new RuntimeException("Failed HEIC to WebP conversion");
                            }
                        } else {
                            log.error("Error converting HEIC to JPG: " + inputPath);
                            throw new RuntimeException("Failed HEIC to JPG conversion");
                        }
                    }

                    Consumer<File> convertToWebpAndCollect = f -> {
                        String inputPath = f.getAbsolutePath();
                        String outputWEBP = "/tmp/" + f.getName() + ".webp";
                        if (ImageConverterUtil.convertToWebp(inputPath, outputWEBP, 80, 800, 600)) {
                            outputWebp.add(new File(outputWEBP));
                        } else {
                            log.error("Error converting to WebP: " + inputPath);
                            throw new RuntimeException("Failed WebP conversion");
                        }
                    };

                    // Convert JPEG images
                    categorized.getOrDefault("jpg", Collections.emptyList()).forEach(convertToWebpAndCollect);
                    // Convert PNG images
                    categorized.getOrDefault("png", Collections.emptyList()).forEach(convertToWebpAndCollect);
                    // Convert Webp images
                    categorized.getOrDefault("webp", Collections.emptyList()).forEach(convertToWebpAndCollect);

                    // Delete original files
                    for (File f : images) {
                        if (!f.delete()) {
                            log.error("Error deleting file: {}", f.getAbsolutePath());
                            f.deleteOnExit();
                        }
                    }
                    return outputWebp;
                })
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorResume(e -> {
                    log.error("Error processing images", e);
                    return Mono.just(Collections.emptyList());
                });
    }

    public Mono<File> convertImage(File image){
        return convertImageAsync(image)
                .doFinally(s -> {
                    if(!image.delete()){
                        log.warn("Failed to delete temp input file: {}", image.getAbsolutePath());
                        image.deleteOnExit();
                    }
                });
    }
}
