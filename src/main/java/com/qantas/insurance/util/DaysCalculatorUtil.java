package com.qantas.insurance.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.qantas.insurance.constants.ApplicationConstants.DATE_SPLIT;

@Component
@Slf4j
public class DaysCalculatorUtil {

    private QantasDate qantasDate;

    public DaysCalculatorUtil(QantasDate qantasDate) {
        this.qantasDate = qantasDate;
    }

    public long getEpochDays(String date) {
        String[] dates = date.split(DATE_SPLIT);
        long days = qantasDate.getFixedDate(Integer.parseInt(dates[0]),
                Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
        log.info("No of days - {}", days);
        long epochDays = qantasDate.getEpochDays(days);
        log.info("EPOCH days - {}", epochDays);
        return epochDays;
    }

    public int getDaysDiff(long startEpoch, long endEpoch) {
        long diff = endEpoch - startEpoch + 1;
        return (int) diff;
    }
}
