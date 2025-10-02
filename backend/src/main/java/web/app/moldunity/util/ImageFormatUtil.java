package web.app.moldunity.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Service
@Slf4j
public class ImageFormatUtil {
    public static boolean isWebP(File file) {
        if (file == null || !file.exists() || !file.isFile()) {
            return false;
        }

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] header = new byte[12];
            int bytesRead = fis.read(header);
            if (bytesRead < 12) {
                return false;
            }

            if (header[0] == 'R' && header[1] == 'I' && header[2] == 'F' && header[3] == 'F') {
                if (header[8] == 'W' && header[9] == 'E' && header[10] == 'B' && header[11] == 'P') {
                    return true;
                }
            }
        } catch (IOException e) {
            log.error("Error to check if file is Webp: {}", e.getMessage(), e);
        }
        return false;
    }

    public static boolean isHEIC(File file) {
        if (file == null || !file.exists() || !file.isFile()) {
            return false;
        }

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[20];
            int bytesRead = fis.read(buffer);
            if (bytesRead < 20) {
                return false;
            }

            String boxType = new String(buffer, 4, 4, StandardCharsets.US_ASCII);
            if (!"ftyp".equals(boxType)) {
                return false;
            }

            String brand = new String(buffer, 8, 4, StandardCharsets.US_ASCII);

            String[] heicBrands = {"heic", "heix", "mif1", "msf1", "hevc", "mshp"};
            for (String b : heicBrands) {
                if (brand.startsWith(b)) {
                    return true;
                }
            }
        } catch (IOException e) {
            log.error("Error to check if file is HEIC: {}", e.getMessage(), e);
        }
        return false;
    }

    public static boolean isJPEG(File file) {
        if (file == null || !file.exists() || !file.isFile()) {
            return false;
        }

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] header = new byte[2];
            int bytesRead = fis.read(header);

            if (bytesRead != 2) {
                return false;
            }

            return (header[0] & 0xFF) == 0xFF && (header[1] & 0xFF) == 0xD8;
        }catch (IOException e){
            log.error("Error to check if file is JPEG: {}", e.getMessage(), e);
        }

        return false;
    }

    public static boolean isPNG(File file) {
        if (file == null || !file.exists() || !file.isFile()) {
            return false;
        }

        byte[] pngSignature = new byte[] {
                (byte) 0x89, 0x50, 0x4E, 0x47,
                0x0D, 0x0A, 0x1A, 0x0A
        };

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] header = new byte[8];
            int bytesRead = fis.read(header);

            if (bytesRead != 8) {
                return false;
            }

            for (int i = 0; i < pngSignature.length; i++) {
                if (header[i] != pngSignature[i]) {
                    return false;
                }
            }
            return true;
        }catch (IOException e){
            log.error("Error to check if file is PNG: {}", e.getMessage(), e);
        }

        return false;
    }
}










