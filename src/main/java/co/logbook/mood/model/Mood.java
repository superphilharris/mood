package co.logbook.mood.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(indexes = @Index(name="mood_timestamp", columnList = "timestamp"))
public class Mood {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private Feeling feeling;

    private String comment;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
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


    public Integer getId() {
        return id;
    }



}