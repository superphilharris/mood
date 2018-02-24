package co.logbook.mood.controller;

import co.logbook.mood.model.Feeling;
import co.logbook.mood.model.Mood;
import co.logbook.mood.repository.MoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping(path="/mood")
public class MainController {
    @Autowired
    private MoodRepository moodRepository;

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
        Date today = new Date();
        return moodRepository.findByTimestampBetween(new Timestamp(today.getTime()), new Timestamp(System.currentTimeMillis()));
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Mood> getAllMoods() {
        return moodRepository.findAll();
    }

}