package com.midsummra.esupdater.service;

import com.midsummra.esupdater.Entity.BilibiliData;

import java.util.Collection;
import java.util.List;

public interface BilbiliDataService {

    void saveData(Collection<BilibiliData> dataToSave);

    void saveData(BilibiliData data);

    List<BilibiliData> getData(String pattern);

    List<BilibiliData> getData(String pattern, int year);

    List<BilibiliData> getData(String pattern, int year, int month);

    List<BilibiliData> getData(String pattern, int year, int month, int day);

    long getTotalCount();

    long getTotalCount(String pattern);

    long getCount(String pattern, int year);

    long getCount(String pattern, int year, int month);

    long getCount(String pattern, int year, int month, int day);

}
