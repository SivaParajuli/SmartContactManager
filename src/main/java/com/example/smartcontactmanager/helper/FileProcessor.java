package com.example.smartcontactmanager.helper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileProcessor {
    public String saveFile(MultipartFile multipartFile){
        try{
            if(multipartFile.isEmpty()) {
                return "The file is empty!!";
            }
            File saveToFile = new ClassPathResource("static/img/user").getFile();
            String fileName = UUID.randomUUID()+"_"+multipartFile.getOriginalFilename();
            Files.copy(multipartFile.getInputStream(), Paths.get(saveToFile.getAbsolutePath()+File.separator+fileName), StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        }catch (Exception e){
            e.printStackTrace();
            return "Some error occurs "+e;
        }
    }
    public boolean deleteFile(String path){
        try{
           File file = new ClassPathResource("static/img/user/").getFile();
           File fileToDelete = new File(file.getAbsoluteFile()+File.separator+path);
            System.out.println(fileToDelete);
           return fileToDelete.delete();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
