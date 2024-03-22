package a.tpo_lab1.task3;

import a.tpo_lab1.task3.exceptions.HangingLegException;
import a.tpo_lab1.task3.exceptions.StandingLegException;
import a.tpo_lab1.task3.exceptions.WrongEmotionException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Human {
    private String name;
    private List<Emotion> facial_expressions;
    private float leftLegAngle;
    private float rightLegAngle;


    boolean isRightEmotion(Emotion facial_expressions) throws WrongEmotionException {
        if (this.facial_expressions.contains(facial_expressions)) return true;
        else throw new WrongEmotionException();
    }

    boolean isLegHanging(float angle) throws HangingLegException {
        if (angle > 30 && angle < 90) return true;
        else throw new HangingLegException();
    }

    boolean isLegStanding(float angle) throws StandingLegException {
        if (angle < 30 && angle >= 0) return true;
        else throw new StandingLegException();
    }
}
