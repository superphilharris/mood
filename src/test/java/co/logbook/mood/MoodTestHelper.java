package co.logbook.mood;

import co.logbook.mood.model.Feeling;
import co.logbook.mood.model.Mood;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MoodTestHelper {


    public List<Mood> convertFeelingsToMoods(Feeling[] feelings) {
        return this.convertFeelingsToMoods(Arrays.asList(feelings));
    }

    public List<Mood> convertFeelingsToMoods(List<Feeling> feelings) {
        List<Mood> moods = new LinkedList<Mood>();
        for (int i = 0; i < feelings.size(); i++) {
            Feeling feeling = feelings.get(i);
            Mood mood = new Mood();
            mood.setFeeling(feeling);
            mood.setComment("Comment for mood: " + feeling.toString());
            mood.setId(i);
            mood.setTimestamp(new Timestamp(i));
            moods.add(mood);
        }
        return moods;
    }
}
