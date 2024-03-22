package a.tpo_lab1.task3;

import a.tpo_lab1.task3.exceptions.HangingLegException;
import a.tpo_lab1.task3.exceptions.StandingLegException;
import a.tpo_lab1.task3.exceptions.WrongEmotionException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FredsState {
    static Human human;


    @BeforeAll
    public static void initFredState() {
        List<Emotion> facial_expressions = new ArrayList<>(
                Arrays.asList(Emotion.SHOCKED, Emotion.FLABBERGASTED, Emotion.OVERWHELMED,
                        Emotion.TO_STUNNED_TO_SPEAK, Emotion.ASTONISHED));
        int left_leg_angle = 90;
        int right_leg_angle = 0;

        human = new Human("Fred", facial_expressions, left_leg_angle, right_leg_angle);
    }


    @Test
    public void isFred(){
        assertEquals("Fred", human.getName());
    }

    @Test
    public void checkEmotions(){
        List<Emotion> emotions = Arrays.asList(Emotion.SCARED, Emotion.HAPPY, Emotion.TERRIFIED, Emotion.CONFIDENT, Emotion.ANGRY, Emotion.RELAXED, Emotion.PEACEFUL);
        emotions.forEach(emotion -> {
            WrongEmotionException exception = assertThrows(WrongEmotionException.class, () -> human.isRightEmotion(emotion));
            assertEquals("Испытвает не эту эмоцию.", exception.getMessage());
        });
    }

    @Test
    public void checkLeftLegPosition() throws StandingLegException {
        StandingLegException exception = assertThrows(StandingLegException.class, () -> human.isLegStanding(human.getLeftLegAngle()));
        assertEquals("Эта нога не стоит.", exception.getMessage());
    }

    @Test
    public void checkRightLegPosition() throws HangingLegException {
        HangingLegException exception = assertThrows(HangingLegException.class, () -> human.isLegHanging(human.getRightLegAngle()));
        assertEquals("Эта нога не висит.", exception.getMessage());
    }

}
