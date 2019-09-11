package services;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;


public class AnimationService {
    private static AnimationService animationService;
    public AnimationService() {
    }
    public static AnimationService getAnimationService(){
        if(animationService==null)
            animationService = new AnimationService();
        return animationService;
    }

    public void animationUpDown(ImageView image, double duration) {
        final double headWidth = 268;
        final double headHeight = 190;
        double positionY = image.getY();
        double positionX = image.getX();
        Path path = new Path();

        path.getElements().add(new MoveTo(positionX + headWidth / 2, positionY + headHeight / 2));
        path.getElements().add(new LineTo(positionX + headWidth / 2, positionY + headHeight / 2 + 10));
        path.getElements().add(new LineTo(positionX + headWidth / 2, positionY + headHeight / 2));
        path.getElements().add(new LineTo(positionX + headWidth / 2, positionY + headHeight / 2 + 10));
        path.getElements().add(new LineTo(positionX + headWidth / 2, positionY + headHeight / 2));

        PathTransition transition = new PathTransition(Duration.seconds(duration), path, image);
        transition.setCycleCount(Animation.INDEFINITE);
        transition.play();
    }

    public void imageRotation(ImageView image, double duration, double angle) {
        RotateTransition rt = new RotateTransition(Duration.seconds(duration), image);
        rt.setByAngle(angle);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();
    }

}
