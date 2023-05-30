package com.midsummra.esupdater;

import com.midsummra.esupdater.common.utils.FileUtils;
import com.midsummra.esupdater.service.DataServiceImpl;
import com.opencsv.CSVWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class EsUpdaterApplicationTests {

    @Autowired
    private FileUtils fileUtils;

    @Autowired
    private DataServiceImpl dataService;

    @Test
    void contextLoads() throws Exception{
    dataService.saveBilibiliData();
    }

}
