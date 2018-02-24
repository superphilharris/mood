package co.logbook.mood.model;

public enum Feeling {
    HAPPY("Happy"), NORMAL("Just normal really"), BIT_MEH("A bit \"meh\""), GRUMPY("Grumpy"), STRESSED("Stressed out - not a happy camper");


    private String description;

    Feeling(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
