package web.app.moldunity.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ImageConverterUtil {

    public static boolean convertToWebp(String inputPath, String outputPath, Integer quality, Integer height, Integer width) {
        try {
            String[] command = {
                    "cwebp",
                    "-q", String.valueOf(quality),
                    "-resize", String.valueOf(height), String.valueOf(width),
                    inputPath,
                    "-o", outputPath
            };

            return convertImage(command);
        } catch (IOException | InterruptedException e) {
           log.error("Error convert to Webp: {}", e.getMessage(), e);
           return false;
        }
    }

    public static boolean convertFromHeic(String inputPath, String outputPath, Integer quality){
        try {
            String[] command = {
                    "heif-convert",
                    "-q", String.valueOf(quality),
                    inputPath,
                    outputPath
            };

            return convertImage(command);
        } catch (IOException | InterruptedException e) {
            log.error("Error convert from Heic: {}", e.getMessage(), e);
            return false;
        }
    }

    public static boolean convertImage(String[] command) throws IOException, InterruptedException {
        String output = command[command.length - 1];
        if (executeCommand(command)){
            log.info("Conversion successful: " + output);
            return true;
        }else {
            log.error("Conversion failed: " + output);
            return false;
        }
    }

    private static boolean executeCommand(String[] command) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(command);
        return pb.start().waitFor() == 0;
    }
}
