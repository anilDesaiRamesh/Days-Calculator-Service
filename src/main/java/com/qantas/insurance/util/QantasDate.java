package com.qantas.insurance.util;

import org.springframework.stereotype.Component;

@Component
public class QantasDate {
    static final int EPOCH_OFFSET = 719163;
    public static final int FEBRUARY = 2;

    public static final boolean isGregorianLeapYear(int gregorianYear) {
        return (((gregorianYear % 4) == 0)
                && (((gregorianYear % 100) != 0) || ((gregorianYear % 400) == 0)));
    }

    public static final long floorDivide(long n, long d) {
        return ((n >= 0) ?
                (n / d) : (((n + 1L) / d) - 1L));
    }

    public static long getFixedDate(int year, int month, int dayOfMonth) {
        long prevyear = (long) year - 1;
        long days = dayOfMonth;

        if (prevyear >= 0) {
            days += (365 * prevyear)
                    + (prevyear / 4)
                    - (prevyear / 100)
                    + (prevyear / 400)
                    + ((367 * month - 362) / 12);
        } else {
            days += (365 * prevyear)
                    + floorDivide(prevyear, 4)
                    - floorDivide(prevyear, 100)
                    + floorDivide(prevyear, 400)
                    + floorDivide((367 * month - 362), 12);
        }

        if (month > FEBRUARY) {
            days -= isGregorianLeapYear(year) ? 1 : 2;
        }
        return days;
    }

    public long getEpochDays(long days) {
        long epochDays = (days - EPOCH_OFFSET);
        return epochDays;
    }
}
