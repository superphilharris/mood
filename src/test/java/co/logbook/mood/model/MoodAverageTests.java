package co.logbook.mood.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoodAverageTests {


    @Test
    public void moodAverageHappyAndNormal() {
        // Given I have 2 people - 1 happy and 1 normal
        List<Mood> moods = new LinkedList<Mood>();
        moods.add(aMood(Feeling.HAPPY)); // 0
        moods.add(aMood(Feeling.NORMAL)); // 1


        // When I get the average of them
        MoodAverage moodAverage = new MoodAverage(moods);


        // Then ...
        assertEquals(0.5f, moodAverage.getAverage(), 0.00000001);
        assertEquals(Feeling.NORMAL, moodAverage.getClosestFeeling());
    }

    @Test
    public void moodAverageHappyAndGrumpy() {
        // Given I have 2 people 1 happy and 1 grumpy
        List<Mood> moods = new LinkedList<Mood>();
        moods.add(aMood(Feeling.HAPPY));
        moods.add(aMood(Feeling.GRUMPY));

        // When I get the average of them
        MoodAverage moodAverage = new MoodAverage(moods);

        // Then ...
        assertEquals(1.5f, moodAverage.getAverage(), 0.0000001);
        assertEquals(Feeling.BIT_MEH, moodAverage.getClosestFeeling());
    }

    @Test
    public void moodAverageNoMoods() {
        // Given I have no moods
        List<Mood> moods = new LinkedList<Mood>();

        // When I get the average of them
        MoodAverage moodAverage = new MoodAverage(moods);

        // Then ...
        assertEquals(0f, moodAverage.getAverage(), 0.00001);
        assertEquals(null, moodAverage.getClosestFeeling());
    }

    public void moodAverageNullConstuctor() {
        // Given I have null moods
        List<Mood> moods = null;

        // When I get the average of them
        MoodAverage moodAverage = new MoodAverage(moods);

        // Then ...
        assertEquals(0f, moodAverage.getAverage(), 0.00001);
        assertEquals(null, moodAverage.getClosestFeeling());
    }

    private Mood aMood(Feeling feeling) {
        Mood mood = new Mood();
        mood.setFeeling(feeling);
        return mood;
    }
}
