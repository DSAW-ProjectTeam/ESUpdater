package com.midsummra.esupdater.scheduled;

import com.midsummra.esupdater.service.DataServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


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
        log.debug("[定时保存]数据已保存到ES");
    }
}
