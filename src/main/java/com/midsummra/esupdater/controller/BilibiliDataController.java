package com.midsummra.esupdater.controller;

import com.midsummra.esupdater.common.result.Result;
import com.midsummra.esupdater.service.DataServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Slf4j
public class BilibiliDataController {

    @Autowired
    private DataServiceImpl dataService;

    @PostMapping("/bapi/saveData")
    public Result saveBilibiliData(){

        long startTime = System.currentTimeMillis();
        long dataCount = dataService.saveBilibiliData();
        long endTime = System.currentTimeMillis();
        long costTime = (endTime - startTime)/1000;

        HashMap<String, Long> returnVal = new HashMap<>();
        returnVal.put("耗时",costTime);
        returnVal.put("存储数据",dataCount);

        return Result.suc(returnVal);
    }

    @GetMapping("/bapi/count")
    public Result getCount(){
        long count = -1;
        try {
            count = dataService.getCount();
        }catch (Exception e){
            log.error("发生未知错误");
            return Result.err("发生未知错误，获取总数目失败",-1);
        }

        return Result.suc(count);
    }
}
