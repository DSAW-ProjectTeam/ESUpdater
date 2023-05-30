package com.midsummra.esupdater.initial;


import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@Slf4j
public class CreateApplication {

    @PostConstruct
    public void createApplication() {
        InputStream resource = null;
        try {
            resource = this.getClass().getClassLoader().getResourceAsStream("application.yaml");
        } catch (NullPointerException nullPointerException) {
            nullPointerException.printStackTrace();
        }

        if (resource == null) {
            log.error("创建配置文件失败");
            return;
        }


        File file = new File("./application.yaml");

        try {
            if (!file.exists()) {
                file.createNewFile();
                try (OutputStream writer = new FileOutputStream(file)){
                    writer.write(resource.readAllBytes());
                }

            }
        }catch(Exception e){
            log.error("创建配置文件失败");
            e.printStackTrace();
        }finally {
            try {
                resource.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
