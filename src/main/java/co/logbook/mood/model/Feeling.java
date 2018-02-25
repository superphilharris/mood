package co.logbook.mood.model;

import java.util.HashMap;
import java.util.Map;

public enum Feeling {
    HAPPY(0, "Happy"), NORMAL(1, "Just normal really"), BIT_MEH(2, "A bit \"meh\""), GRUMPY(3, "Grumpy"), STRESSED(4, "Stressed out - not a happy camper");

    private int code;
    private String description;
    private static Map<Integer, Feeling> map = new HashMap<Integer, Feeling>();

    private Feeling(int code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Initialize the map, so that we can get all the feelings from the code later
     */
    static {
        for (Feeling feeling : Feeling.values()) {
            map.put(feeling.getCode(), feeling);
        }
    }

    /**
     * Gets the feeling that is associated with the numerical value of the feeling
     * @param code
     * @return
     */
    public static Feeling valueOf(int code) {
        return map.get(code);
    }


    public int getCode() { return this.code; }
    public String getDescription() {
        return this.description;
    }
}
