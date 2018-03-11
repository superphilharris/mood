package co.logbook.mood.model;

import co.logbook.mood.model.Feeling;
import co.logbook.mood.model.Mood;

import java.util.LinkedList;
import java.util.List;

public class MoodAverage {
    private List<Mood> moods;

    private float average;

    private Feeling closestFeeling;

    /**
     * Determines the average mood, and rounds the value to get the closest feeling of the list of moods
     * @param moods
     */
    public MoodAverage(List<Mood> moods) {
        this.moods = (moods != null)? moods : new LinkedList<Mood>();
        this.average = (float) this.moods.stream().mapToInt(m -> m.getFeeling().getCode()).sum() / Math.max(1, this.moods.size());
        this.closestFeeling = (this.moods.size() > 0)? Feeling.valueOf(Math.round(this.average)) : null;
    }

    public List<Mood> getMoods() {
        return this.moods;
    }

    public float getAverage() {
        return average;
    }

    public Feeling getClosestFeeling() {
        return this.closestFeeling;
    }
}
