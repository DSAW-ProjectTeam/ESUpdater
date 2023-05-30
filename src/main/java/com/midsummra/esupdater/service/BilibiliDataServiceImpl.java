package com.midsummra.esupdater.service;

import com.midsummra.esupdater.Entity.BilibiliData;
import com.midsummra.esupdater.repository.ESRepo;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @Author Midsummra
 * @Version 1.0
 */
@Component
public class BilibiliDataServiceImpl implements BilbiliDataService{

    @Autowired
    private ESRepo repository;

    @Override
    public void saveData(Collection<BilibiliData> dataToSave) {
        repository.saveAll(dataToSave);
    }

    @Override
    public void saveData(BilibiliData data) {
        repository.save(data);
    }

    @Override
    public @Nullable List<BilibiliData> getData(String pattern) {
        return repository.queryAllByData(pattern);
    }

    @Override
    public @Nullable List<BilibiliData> getData(String pattern, int year) {
        return repository.queryAllByDataAndYear(pattern, year);
    }

    @Override
    public @Nullable List<BilibiliData> getData(String pattern, int year, int month) {
        return repository.queryAllByDataAndYearAndMonth(pattern, year, month);
    }

    @Override
    public List<BilibiliData> getData(String pattern, int year, int month, int day) {
        return repository.queryAllByDataAndYearAndMonthAndDay(pattern, year, month, day);
    }

    @Override
    public long getTotalCount() {
        return repository.count();
    }

    @Override
    public long getTotalCount(String pattern) {
        return repository.countAllByData(pattern);
    }

    @Override
    public long getCount(String pattern, int year) {
        return repository.countAllByDataAndYear(pattern, year);
    }

    @Override
    public long getCount(String pattern, int year, int month) {
        return repository.countAllByDataAndYearAndMonth(pattern, year, month);
    }

    @Override
    public long getCount(String pattern, int year, int month, int day) {
        return repository.countAllByDataAndYearAndMonthAndDay(pattern, year, month, day);
    }
}
