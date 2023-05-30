package com.midsummra.esupdater.common.utils;

import com.midsummra.esupdater.Entity.BilibiliData;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Component
@Slf4j
public class CSVDataUtils {

    /**
     * @Author Midsummra
     * @param CSVData 根据下标依次为 [评论]-[时间(yy-mm-dd-hh-mm-ss)]-[时间戳]
     * @return 字符串数组，根据下标依次为 [data]-[year]-[month]-[day]
     * @Description 用于解析B站评论
     */
    private @Nullable ArrayList<String> parseCSV(String[] CSVData){
        ArrayList<String> result = null;
        if (CSVData.length != 3){
            return null;
        }

        try {
            result = new ArrayList<>();
            Date date = new Date(Long.parseLong(CSVData[2]) * 1000);
            String[] tempDate = new SimpleDateFormat("yyyy-MM-dd").format(date).split("-");
            result.add(CSVData[0]);
            result.add(tempDate[0]);
            result.add(tempDate[1]);
            result.add(tempDate[2]);
        }catch (NumberFormatException e){
            log.error("发生long转换错误");
            return null;
        }

        return result;
    }


    public BilibiliData generateBilibiliData(@NotNull String[] CSVData){
        ArrayList<String> list = parseCSV(CSVData);
        if (list == null || list.size() != 4){
            return null;
        }

        BilibiliData bilibiliData = new BilibiliData();
        bilibiliData.setData(list.get(0));
        bilibiliData.setYear(Integer.parseInt(list.get(1)));
        bilibiliData.setMonth(Integer.parseInt(list.get(2)));
        bilibiliData.setDay(Integer.parseInt(list.get(3)));

        return bilibiliData;
    }
}
