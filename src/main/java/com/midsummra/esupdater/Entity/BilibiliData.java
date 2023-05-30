package com.midsummra.esupdater.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "bilibili_data")
@Setting(
//        分区
        shards = 3,
//        副本
        replicas = 1
)
/**
 * @Author Midsummra
 * @Description B站数据实体类
 */
public class BilibiliData {

    @Id
    private long id;

    @Field(index = true, type = FieldType.Text, analyzer = "ik_max_word")
    private String data;

    @Field(type = FieldType.Keyword)
    private int year;

    @Field(type = FieldType.Keyword)
    private int month;

    @Field(type = FieldType.Keyword)
    private int day;
}
