package co.logbook.mood.controller;

import co.logbook.mood.model.Mood;
import co.logbook.mood.model.MoodAverage;
import co.logbook.mood.repository.MoodRepository;
import co.logbook.mood.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping(path="/mood")
public class MoodController {
    @Autowired
    private MoodRepository moodRepository;

    @PostMapping(path="/add")
    public @ResponseBody Mood addNewMood (@RequestBody @Validated Mood mood) {
        return moodRepository.save(mood);
    }

    @GetMapping(path="/averageForToday")
    public @ResponseBody
    MoodAverage getAverageMoodForToday() {
        List<Mood> todaysMoods = moodRepository.findByTimestampBetween(TimeUtils.getTodayAtMidnight(), new Timestamp(System.currentTimeMillis()));
        return new MoodAverage(todaysMoods);
    }
}