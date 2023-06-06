package com.midsummra.esupdater.repository;

import com.midsummra.esupdater.entity.BilibiliData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ESRepo extends ElasticsearchRepository<BilibiliData,Long> {

    List<BilibiliData> queryAllByData(String pattern);

    List<BilibiliData> queryAllByDataAndYear(String pattern, int year);

    List<BilibiliData> queryAllByDataAndYearAndMonth(String pattern, int year, int month);

    List<BilibiliData> queryAllByDataAndYearAndMonthAndDay(String pattern, int year, int month, int day);

    long countAllByData(String pattern);

    long countAllByDataAndYear(String pattern, int year);

    long countAllByDataAndYearAndMonth(String pattern, int year, int month);

    long countAllByDataAndYearAndMonthAndDay(String pattern, int year, int month, int day);

}
