package co.logbook.mood.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(indexes = @Index(name = "mood_timestamp", columnList = "timestamp"))
public class Mood {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Feeling feeling;

    @Size(max = 350, message = "Comment must be less than 350 characters")
    private String comment;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp timestamp;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Feeling getFeeling() {
        return feeling;
    }

    public void setFeeling(Feeling feeling) {
        this.feeling = feeling;
    }


    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) { this.id = id; }

}