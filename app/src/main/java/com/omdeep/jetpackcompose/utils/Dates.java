package com.omdeep.jetpackcompose.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Dates {
    private static final String DATE_PATTERN = "MM/yyyy";

    public int getLastDayOfMonth(String dateString) throws ParseException {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern(DATE_PATTERN);
            YearMonth yearMonth = YearMonth.parse(dateString, pattern);
            LocalDate date = yearMonth.atEndOfMonth();
            return date.lengthOfMonth();
        } else {
            DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
            Calendar calender = Calendar.getInstance();
            calender.setTime(dateFormat.parse(dateString));
            return calender.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
    }

}
