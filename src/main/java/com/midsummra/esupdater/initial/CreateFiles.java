package com.midsummra.esupdater.initial;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;

@Slf4j
@Component
public class CreateFiles {

    /**
     * @Author Midsummra
     * 创建data文件夹用于存储待入库的数据
     */
    @PostConstruct
    public void createFiles(){
        File dataFile = new File("./data");
        boolean dataFileIsExist = false;

        if (!dataFile.exists()){
            try {
                dataFileIsExist = dataFile.mkdir();
            }catch (Exception e){
                e.printStackTrace();
                log.error("创建data文件夹失败");
            }
        }else {
            dataFileIsExist = true;
        }

        if (dataFileIsExist){
            log.debug("data文件夹已创建");
        }else {
            log.debug("data文件夹创建失败，发生未知错误");
        }


        File storageList = new File("./storageList.csv");
        boolean storageListIsExist = false;

        if (!storageList.exists()){
            try {
                storageListIsExist = storageList.createNewFile();
            }catch (Exception e){
                e.printStackTrace();
                log.error("创建storageList文件失败");
            }
        }else {
            storageListIsExist = true;
        }
        if (storageListIsExist){
            log.debug("storageList文件已创建");
        }else {
            log.debug("storageList文件创建失败，发生未知错误");
        }
    }
}
