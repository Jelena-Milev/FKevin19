package resources;

import main.Main;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.CodeSource;

public class Resources {

    // #TODO set this to the location the application is running from, dynamically
    public static String APP_LOCATION = "";

    public static final String DATA_LOCATION = APP_LOCATION + "data/";

    private static int lowDifficultyQuestionPoints = 1;
    private static int medDifficultyQuestionPoints = 3;
    private static int higDifficultyQuestionPoints = 5;

    private static int numberOfLowQuestions = 5;
    private static int numberOfMediumQuestions = 3;
    private static int numberOfHighQuestions = 2;

    public static final int TOTAL_NUMBER_OF_QUESTIONS = numberOfLowQuestions + numberOfMediumQuestions + numberOfHighQuestions;

    public static final int maxPoints = (lowDifficultyQuestionPoints * numberOfLowQuestions) + (medDifficultyQuestionPoints * numberOfMediumQuestions) + (higDifficultyQuestionPoints * numberOfHighQuestions);

    public static int gameDurationSeconds = 90;



    public static String participantPath;

    public enum QuestionDifficulty {
        LOW(lowDifficultyQuestionPoints, numberOfLowQuestions),
        MEDIUM(medDifficultyQuestionPoints, numberOfMediumQuestions),
        HIGH(higDifficultyQuestionPoints, numberOfHighQuestions);

        public final int points;
        private int numberOfQuestions;

        QuestionDifficulty(int points, int numberOfQuestions) {
            this.points = points;
            this.numberOfQuestions = numberOfQuestions;
        }

        public int getPoints() {
            return this.points;
        }

        public int getNumberOfQuestions() {
            return numberOfQuestions;
        }
    }

    public static String getAppLocation(){
        CodeSource codeSource = Main.class.getProtectionDomain().getCodeSource();
        String path = codeSource.getLocation().getPath();
        File launcher = null;
        try {
            String decodedPath = URLDecoder.decode(path, "UTF-8");
            launcher = new File(decodedPath);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return launcher.getParentFile().getPath();
    }


}
