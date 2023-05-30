package com.midsummra.esupdater.common.utils;


import com.opencsv.*;
import com.opencsv.exceptions.CsvException;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Author Midsummra
 * @Description 文件IO工具类，用于读写需要入库的CSV文件以及已经保存的文件目录
 * @Version  1.0
 */
@Component
public class FileUtils {

    private File dataFile;

    private File list;

    private CSVReader listReader;

    private CSVWriter listWriter;

    public FileUtils(){
        try {
            String DATA_PATH = "./data";
            dataFile = new File(DATA_PATH);
            String STORAGE_LIST = "./storageList.csv";
            list = new File(STORAGE_LIST);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public File getDataFile(){
        return this.dataFile;
    }

    /**
     * @Author Midsummra
     * @throws IOException IO异常
     */
    public void writeStorageList(@NotNull ArrayList<String[]> listToStorage) throws IOException {
        if (listWriter == null){
            listWriter = new CSVWriter(new FileWriter(list, true));
        }
        listWriter.writeAll(listToStorage);
        listWriter.flush();
    }

    /**
     * @Author Midsummra
     * @return 已经读取的数据列表的Reader
     * @throws IOException IO异常
     */
    public List<String[]> getStorageList() throws CsvException, IOException {
        if (listReader == null){
            RFC4180Parser rfc4180Parser = new RFC4180ParserBuilder().build();
            listReader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream(list))).withCSVParser(rfc4180Parser).build();
        }

        return listReader.readAll();
    }

    private @Nullable File[] getAllData(){

        return dataFile.listFiles((file)->{
            String fileName = file.getName();
            String suffix = fileName.substring(fileName.length()-3);
            return "csv".equals(suffix);
        });
    }

    public @Nullable ArrayList<File> getDataList() throws IOException, CsvException{
        File[] allData = getAllData();
        List<String[]> storageList = getStorageList();
        HashSet<String> storageSet = new HashSet<>();

        for (String[] strings : storageList) {
            storageSet.add(strings[0]);
        }

        if (allData == null){
            return null;
        }

        ArrayList<File> fileList = new ArrayList<>();
        for (File file : allData) {
            if (!storageSet.contains(file.getName())){
                fileList.add(file);
            }
        }
        return fileList;
    }

}
