package sample.animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;


/**
 * Created by mariabenetgarcia on 21.01.2018.
 */
public class Shaker {
    private TranslateTransition translateTransition;

    public Shaker(Node node) {
        translateTransition =
                new TranslateTransition(Duration.millis(50),node);
        translateTransition.setFromX(0f);
        translateTransition.setByX(10f);
        translateTransition.setCycleCount(2);
        translateTransition.setAutoReverse(true);
    }
    public void shake(){
        translateTransition.playFromStart();
    }
}
