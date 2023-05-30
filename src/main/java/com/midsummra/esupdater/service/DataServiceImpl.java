package com.midsummra.esupdater.service;

import com.midsummra.esupdater.Entity.BilibiliData;
import com.midsummra.esupdater.common.utils.CSVDataUtils;
import com.midsummra.esupdater.common.utils.FileUtils;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.RFC4180Parser;
import com.opencsv.RFC4180ParserBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Component
@Slf4j
public class DataServiceImpl implements DataService {

    private BilibiliDataServiceImpl bilibiliData;

    private CSVDataUtils csvDataUtils;

    private FileUtils fileUtils;

    @Autowired
    public DataServiceImpl(BilibiliDataServiceImpl bilibiliData, CSVDataUtils csvDataUtils, FileUtils fileUtils) {
        this.bilibiliData = bilibiliData;
        this.csvDataUtils = csvDataUtils;
        this.fileUtils = fileUtils;
    }


    @Override
    public long saveBilibiliData() {
        long count = bilibiliData.getTotalCount();
        long dataCount = 0;
        ArrayList<String[]> storage = new ArrayList<>();

        try {
            ArrayList<File> dataList = fileUtils.getDataList();
            for (File file : dataList) {

                storage.add(new String[] {file.getName(), new Date().toString()});
                ArrayList<BilibiliData> dataToSave = new ArrayList<>();
                RFC4180Parser rfc4180Parser = new RFC4180ParserBuilder().build();
                try(CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream(file))).withCSVParser(rfc4180Parser).build()){

                    String[] line = null;
                    while ((line = csvReader.readNext()) != null){
                        dataCount++;
                        BilibiliData tempData = csvDataUtils.generateBilibiliData(line);
                        if (tempData == null){
                            continue;
                        }
                        tempData.setId(count+dataCount);
                        dataToSave.add(tempData);

                        if (dataCount != 0 && (dataCount % 10000 == 0)){
                            bilibiliData.saveData(dataToSave);
                            dataToSave.clear();
                        }
                    }
                }

                bilibiliData.saveData(dataToSave);
            }

            fileUtils.writeStorageList(storage);

        }catch (Exception e){
            log.error("发生未知错误，B站评论信息保存失败");
            e.printStackTrace();
        }

        log.debug("共存储[" + dataCount + "]条数据");

        return dataCount;
    }
}
