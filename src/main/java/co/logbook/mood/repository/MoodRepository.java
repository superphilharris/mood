package co.logbook.mood.repository;


import co.logbook.mood.model.Mood;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface MoodRepository extends CrudRepository<Mood, Long> {

    List<Mood> findByTimestampBetween(Timestamp startTimestamp, Timestamp endTimestamp);
}
