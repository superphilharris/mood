package co.logbook.mood.repository;


import co.logbook.mood.model.Mood;
import org.springframework.data.repository.CrudRepository;

public interface MoodRepository extends CrudRepository<Mood, Long> {

}
