package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class MyUtils {

    public static String getPrettyDate(LocalDate date){
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));

    }
}
