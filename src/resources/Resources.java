package resources;

public class Resources {

    // #TODO set this to the location the application is running from, dynamically
    public static final String APP_LOCATION = "";

    public static final String DATA_LOCATION = APP_LOCATION + "data/";

    public static int lowDifficultyQuestionPoints = 1;
    public static int medDifficultyQuestionPoints = 3;
    public static int higDifficultyQuestionPoints = 5;

    public static String participantPath;

    public enum QuestionDifficulty {
        LOW (lowDifficultyQuestionPoints),
        MEDIUM (medDifficultyQuestionPoints),
        HIGH (higDifficultyQuestionPoints);

        public final int points;

        QuestionDifficulty(int points) {
            this.points = points;
        }

        public int getPoints() {
            return this.points;
        }
    }
}
