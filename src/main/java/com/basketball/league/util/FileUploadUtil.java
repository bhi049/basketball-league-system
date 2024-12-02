package com.basketball.league.util;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;

public class FileUploadUtil {
    public static String saveFile(MultipartFile file, String uploadDir) {
        try {
            String originalFilename = file.getOriginalFilename();
            String filePath = uploadDir + originalFilename;

            // Ensure the directory exists
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Save the file
            File destinationFile = new File(filePath);
            file.transferTo(destinationFile);

            return "/images/logos/" + originalFilename;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
