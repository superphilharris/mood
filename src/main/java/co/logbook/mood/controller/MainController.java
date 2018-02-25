package co.logbook.mood.controller;

import co.logbook.mood.model.Feeling;
import co.logbook.mood.model.Mood;
import co.logbook.mood.repository.MoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
@RequestMapping(path="/mood")
public class MainController {
    @Autowired
    private MoodRepository moodRepository;

    @PostMapping(path="/add")
    public @ResponseBody Mood addNewMood (@RequestBody Mood mood) {
        return moodRepository.save(mood);
    }

    @GetMapping(path="/averageForToday")
    public @ResponseBody MoodAverage getAverageMoodForToday() {
        List<Mood> todaysMoods = moodRepository.findByTimestampBetween(getTodayAtMidnight(), new Timestamp(System.currentTimeMillis()));
        return new MoodAverage(todaysMoods);
    }

    /**
     * Gets the timestamp for today at midnight, by:
     * 1. Finding the current calendar time,
     * 2. Resetting the hours, minutes and seconds to 0 for the calendar time
     * @return the current time in a timestamp
     */
    private Timestamp getTodayAtMidnight() {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return new Timestamp(c.getTimeInMillis());
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Mood> getAllMoods() {
        return moodRepository.findAll();
    }

}