package co.logbook.mood.utils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeUtils {

    /**
     * Gets the timestamp for today at midnight, by:
     * 1. Finding the current calendar time,
     * 2. Resetting the hours, minutes and seconds to 0 for the calendar time
     * @return the current time in a timestamp
     */
    public static Timestamp getTodayAtMidnight() {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return new Timestamp(c.getTimeInMillis());
    }

}
