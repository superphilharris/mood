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

@Controller
@RequestMapping(path="/mood")
public class MainController {
    @Autowired
    private MoodRepository moodRepository;

    @PostMapping(path="/add")
    @GetMapping(path="/add")
    public @ResponseBody String addNewMood (@RequestParam Feeling feeling, @RequestParam String comment) {
        Mood mood = new Mood();
        mood.setFeeling(feeling);
        mood.setComment(comment);
        moodRepository.save(mood);
        return "Saved";
    }

    @GetMapping(path="/allForToday")
    public @ResponseBody Iterable<Mood> getAllMoodsForToday() {
        return moodRepository.findByTimestampBetween(getTodayAtMidnight(), new Timestamp(System.currentTimeMillis()));
    }

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