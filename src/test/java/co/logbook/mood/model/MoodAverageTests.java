package co.logbook.mood.model;

import co.logbook.mood.MoodTestHelper;
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
    private MoodTestHelper moodTestHelper = new MoodTestHelper();

    @Test
    public void moodAverageHappyAndNormal() {
        // Given I have 2 people - 1 happy and 1 normal
        Feeling[] feelings = {
                Feeling.HAPPY, // 0
                Feeling.NORMAL // 1
        };
        List<Mood> moods = moodTestHelper.convertFeelingsToMoods(feelings);


        // When I get the average of them
        MoodAverage moodAverage = new MoodAverage(moods);


        // Then the average should be 0.5 closest to the NORMAL feeling
        assertEquals(0.5f, moodAverage.getAverage(), 0.00000001);
        assertEquals(Feeling.NORMAL, moodAverage.getClosestFeeling());
    }

    @Test
    public void moodAverageHappyAndGrumpy() {
        // Given I have 2 people 1 happy and 1 grumpy
        Feeling[] feelings = {
                Feeling.HAPPY,
                Feeling.GRUMPY
        };
        List<Mood> moods = moodTestHelper.convertFeelingsToMoods(feelings);

        // When I get the average of them
        MoodAverage moodAverage = new MoodAverage(moods);

        // Then the average should be 1.5 closest to the BIT_MEH feeling
        assertEquals(1.5f, moodAverage.getAverage(), 0.0000001);
        assertEquals(Feeling.BIT_MEH, moodAverage.getClosestFeeling());
    }

    @Test
    public void moodAverageNoMoods() {
        // Given I have no moods
        List<Mood> moods = new LinkedList<Mood>();

        // When I get the average of them
        MoodAverage moodAverage = new MoodAverage(moods);

        // Then the average should be 0, with no closest feeling
        assertEquals(0f, moodAverage.getAverage(), 0.00001);
        assertEquals(null, moodAverage.getClosestFeeling());
    }

    public void moodAverageNullConstuctor() {
        // Given I have null moods
        List<Mood> moods = null;

        // When I get the average of them
        MoodAverage moodAverage = new MoodAverage(moods);

        // Then the average should be 0, with no closest feeling
        assertEquals(0f, moodAverage.getAverage(), 0.00001);
        assertEquals(null, moodAverage.getClosestFeeling());
    }

}
