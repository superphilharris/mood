package co.logbook.mood.controller;

import co.logbook.mood.model.Mood;
import co.logbook.mood.repository.MoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/demo")
public class MainController {
    @Autowired
    private MoodRepository moodRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewMood (@RequestParam String name, @RequestParam String email) {
        Mood mood = new Mood();
        mood.setName(name);
        mood.setEmail(email);
        moodRepository.save(mood);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Mood> getAllMoods() {
        return moodRepository.findAll();
    }

}