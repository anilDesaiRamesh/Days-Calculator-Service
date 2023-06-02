package com.qantas.insurance.util;

import org.springframework.stereotype.Component;

@Component
public class QantasDate {
    static final int SECOND_IN_MILLIS = 1000;
    static final int MINUTE_IN_MILLIS = SECOND_IN_MILLIS * 60;
    static final int HOUR_IN_MILLIS = MINUTE_IN_MILLIS * 60;
    static final int DAY_IN_MILLIS = HOUR_IN_MILLIS * 24;
    static final int EPOCH_OFFSET = 719163;
    static final int[] ACCUMULATED_DAYS_IN_MONTH
            //  12/1 1/1 2/1 3/1 4/1 5/1 6/1 7/1 8/1 9/1 10/1 11/1 12/1
            = {-30, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

    static final int[] ACCUMULATED_DAYS_IN_MONTH_LEAP
            //  12/1 1/1 2/1   3/1   4/1   5/1   6/1   7/1   8/1   9/1   10/1   11/1   12/1
            = {-30, 0, 31, 59 + 1, 90 + 1, 120 + 1, 151 + 1, 181 + 1, 212 + 1, 243 + 1, 273 + 1, 304 + 1, 334 + 1};

    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;

    private static final int BASE_YEAR = 1970;

    private static final int[] FIXED_DATES = {
            719163, // 1970
            719528, // 1971
            719893, // 1972
            720259, // 1973
            720624, // 1974
            720989, // 1975
            721354, // 1976
            721720, // 1977
            722085, // 1978
            722450, // 1979
            722815, // 1980
            723181, // 1981
            723546, // 1982
            723911, // 1983
            724276, // 1984
            724642, // 1985
            725007, // 1986
            725372, // 1987
            725737, // 1988
            726103, // 1989
            726468, // 1990
            726833, // 1991
            727198, // 1992
            727564, // 1993
            727929, // 1994
            728294, // 1995
            728659, // 1996
            729025, // 1997
            729390, // 1998
            729755, // 1999
            730120, // 2000
            730486, // 2001
            730851, // 2002
            731216, // 2003
            731581, // 2004
            731947, // 2005
            732312, // 2006
            732677, // 2007
            733042, // 2008
            733408, // 2009
            733773, // 2010
            734138, // 2011
            734503, // 2012
            734869, // 2013
            735234, // 2014
            735599, // 2015
            735964, // 2016
            736330, // 2017
            736695, // 2018
            737060, // 2019
            737425, // 2020
            737791, // 2021
            738156, // 2022
            738521, // 2023
            738886, // 2024
            739252, // 2025
            739617, // 2026
            739982, // 2027
            740347, // 2028
            740713, // 2029
            741078, // 2030
            741443, // 2031
            741808, // 2032
            742174, // 2033
            742539, // 2034
            742904, // 2035
            743269, // 2036
            743635, // 2037
            744000, // 2038
            744365, // 2039
    };

    public static final boolean isGregorianLeapYear(int gregorianYear) {
        return (((gregorianYear % 4) == 0)
                && (((gregorianYear % 100) != 0) || ((gregorianYear % 400) == 0)));
    }

    static final long getDayOfYear(int year, int month, int dayOfMonth) {
        return (long) dayOfMonth
                + (isGregorianLeapYear(year) ?
                ACCUMULATED_DAYS_IN_MONTH_LEAP[month] : ACCUMULATED_DAYS_IN_MONTH[month]);
    }

    public static final long floorDivide(long n, long d) {
        return ((n >= 0) ?
                (n / d) : (((n + 1L) / d) - 1L));
    }

    public static long getFixedDate(int year, int month, int dayOfMonth) {
        boolean isJan1 = month == JANUARY && dayOfMonth == 1;

        int n = year - BASE_YEAR;
        if (n >= 0 && n < FIXED_DATES.length) {
            long jan1 = FIXED_DATES[n];
            return isJan1 ? jan1 : jan1 + getDayOfYear(year, month, dayOfMonth) - 1;
        }

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
