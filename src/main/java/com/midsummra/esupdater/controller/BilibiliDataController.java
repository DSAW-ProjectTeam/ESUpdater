package com.midsummra.esupdater.controller;

import com.midsummra.esupdater.common.result.Result;
import com.midsummra.esupdater.service.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
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
}
