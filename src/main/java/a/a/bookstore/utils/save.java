package a.a.bookstore.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class save {

    private final static String FILE_UPLOAD_URI = "src/main/resources/static/imgs/";

    public static String saveFile(MultipartFile file){

        try{
            Path uploadPath = Paths.get(FILE_UPLOAD_URI);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filepPath = uploadPath.resolve(fileName);
            Files.copy( file.getInputStream(), filepPath);

            return fileName;
        }catch(IOException e){
            System.out.println("Can't save file" + e.getMessage());
            return null;
        }

    }

}
