package com.qantas.insurance.util;

import com.qantas.insurance.exception.InvalidInputFormatException;
import com.qantas.insurance.exception.InvalidInputValException;
import org.springframework.stereotype.Component;

import static com.qantas.insurance.constants.ApplicationConstants.DATE_FORMAT;
import static com.qantas.insurance.constants.ApplicationConstants.DATE_SPLIT;
import static com.qantas.insurance.constants.ErrorMessages.INVALID_INPUT_FORMAT;
import static com.qantas.insurance.constants.ErrorMessages.START_DATE_SHOULD_BE_LESSER;

@Component
public class InputValidatorUtil {

    public void validateDateStructure(String date) {
        boolean isValidFormat = date.matches(DATE_FORMAT);
        if (!isValidFormat)
            throw new InvalidInputFormatException(INVALID_INPUT_FORMAT);
    }

    public void validateInputs(String startDate, String endDate) {
        String[] startDates = startDate.split(DATE_SPLIT);
        String[] endDates = endDate.split(DATE_SPLIT);
        int startDateVal = Integer.parseInt(startDates[0]);
        int endDateVal = Integer.parseInt(endDates[0]);
        if (endDateVal < startDateVal)
            throw new InvalidInputValException(START_DATE_SHOULD_BE_LESSER);
    }

}
