package co.logbook.mood.controller;

import co.logbook.mood.model.Feeling;
import co.logbook.mood.model.Mood;
import co.logbook.mood.repository.MoodRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoodControllerTests {
    @InjectMocks
    private MoodController moodController;

    @Mock
    private MoodRepository moodRepository = Mockito.mock(MoodRepository.class);

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(moodController)
                                .build();
    }

    @Test
    public void addEmptyMood() throws Exception {
        List<Feeling> feelings = new LinkedList<Feeling>();
        feelings.add(Feeling.NORMAL);
        feelings.add(Feeling.GRUMPY);
        List<Mood> moods = mockGetAllMoods(feelings);

        mockMvc.perform(MockMvcRequestBuilders.get("/mood/all"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


    private List<Mood> convertFeelingsToMoods(List<Feeling> feelings) {
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


    /**
     * Will convert a list of feelings to moods,
     * then mock the moodController.getAllMoods to return the moods
     * @param feelings
     * @return the list of moods that will be returned when retrieved from the database
     */
    private List<Mood> mockGetAllMoods(List<Feeling> feelings) {
        List<Mood> moods = convertFeelingsToMoods(feelings);
        Mockito.when(moodRepository.findAll()).thenReturn(moods);
        return moods;
    }
}
