package ru.cainiao.utils;

import org.postgresql.util.PGInterval;



public class IntervalFormatter {

    public static String toString(final PGInterval interval) {
        StringBuilder builder = new StringBuilder();
        if (interval.getDays() != 0) {
            builder.append(String.format("%sd ", interval.getDays()));
        }
        if (interval.getHours() != 0) {
            builder.append(String.format("%sh", interval.getHours()));
        }
        return builder.toString();
    }
}
