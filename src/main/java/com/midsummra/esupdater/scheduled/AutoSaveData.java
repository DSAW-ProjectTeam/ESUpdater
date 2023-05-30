package com.midsummra.esupdater.scheduled;

import com.midsummra.esupdater.Entity.BilibiliData;
import com.midsummra.esupdater.common.utils.CSVDataUtils;
import com.midsummra.esupdater.common.utils.FileUtils;
import com.midsummra.esupdater.service.BilibiliDataServiceImpl;
import com.midsummra.esupdater.service.DataServiceImpl;
import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;


/**
 * @Author Midsummra
 * @Description ES服务器存储定时任务
 */
@Component
@Slf4j
public class AutoSaveData {

    private DataServiceImpl dataService;

    @Autowired
    public AutoSaveData(DataServiceImpl dataService) {
        this.dataService = dataService;
    }

    /**
     * @Author Midsummra
     * @Description Bilibili评论存储定时任务，每天4:00存储
     */
    @Scheduled(cron = "0 0 4 * * ?")
    public void saveBilibiliData(){
        dataService.saveBilibiliData();

    }
}
