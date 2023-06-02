package com.qantas.insurance.service;

import com.qantas.insurance.util.DaysCalculatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.qantas.insurance.constants.ApplicationConstants.IGNORE_DAYS;

@Service
@Slf4j
public class InsuranceServiceImpl implements IInsuranceService {

    DaysCalculatorUtil daysCalculatorUtil;

    public InsuranceServiceImpl(DaysCalculatorUtil daysCalculatorUtil) {
        this.daysCalculatorUtil = daysCalculatorUtil;
    }

    @Override
    public Integer process(String startDate, String endDate) {

        long epochStartDate = daysCalculatorUtil.getEpochDays(startDate);
        log.info("Epoch start date - {}", epochStartDate);

        long epochEndDate = daysCalculatorUtil.getEpochDays(endDate);
        log.info("Epoch end date - {}", epochEndDate);

        int daysDiff = daysCalculatorUtil.getDaysDiff(epochStartDate, epochEndDate);
        log.info("Day difference - {}", daysDiff);

        return daysDiff - IGNORE_DAYS;
    }
}
