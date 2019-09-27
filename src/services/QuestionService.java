package services;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import resources.Resources;
import resources.entities.ClosedQuestion;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class QuestionService {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private final String fileName = "Questions.json";

    private static QuestionService questionService;

    private QuestionService() {
    }

    public static QuestionService getQuestionServiceInstance() {
        if (questionService == null) {
            questionService = new QuestionService();
        }
        return questionService;
    }

    public ClosedQuestion[] getRandomQuestions(){
        List<ClosedQuestion> allQuestions = this.getClosedQuestions();
        ClosedQuestion[] chosenRandomQuestions = new ClosedQuestion[Resources.TOTAL_NUMBER_OF_QUESTIONS];

        int low = 0;
        int medium = 0;
        int high = 0;

        int counter = 0;
        Random random = new Random();
        while(counter < Resources.TOTAL_NUMBER_OF_QUESTIONS){
            int randomIndex = random.nextInt(allQuestions.size());
            ClosedQuestion question = allQuestions.get(randomIndex);

            if(question.getDifficulty() == Resources.QuestionDifficulty.LOW && low < Resources.QuestionDifficulty.LOW.getNumberOfQuestions()){
                chosenRandomQuestions[counter] = question;
                ++low;
                ++counter;
            }else if (question.getDifficulty() == Resources.QuestionDifficulty.MEDIUM && medium < Resources.QuestionDifficulty.MEDIUM.getNumberOfQuestions()){
                chosenRandomQuestions[counter] = question;
                ++medium;
                ++counter;
            }else if(question.getDifficulty() == Resources.QuestionDifficulty.HIGH && high < Resources.QuestionDifficulty.HIGH.getNumberOfQuestions()){
                chosenRandomQuestions[counter] = question;
                ++high;
                ++counter;
            }
            allQuestions.remove(question);
        }
        return chosenRandomQuestions;
    }

    private List<ClosedQuestion> getClosedQuestions() {
        JsonElement jsonQuestions = this.getJsonQuestions();
        // Convert JSON element to list
        Type listType = TypeToken.getParameterized(List.class, ClosedQuestion.class).getType();
        return this.gson.fromJson(jsonQuestions, listType);
    }

    private JsonElement getJsonQuestions() {
        JsonElement jsonQuestions = null;
        try (JsonReader reader = new JsonReader(new FileReader(Resources.DATA_LOCATION+this.fileName))) {
            JsonElement fileContent = gson.fromJson(reader, JsonObject.class);
                jsonQuestions = fileContent.getAsJsonObject().get("ClosedQuestions");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonQuestions;
    }

    private int[] getRandomNumbers(int howMany, int range){
        int[] array = new int[howMany];
        Random random = new Random();
        int counter = 0;
        while(counter < howMany){
            int randomNumber = random.nextInt(range);
            if(isNumberAlreadyChosen(array, randomNumber) == false)
                array[counter++] = randomNumber;
        }
        return array;
    }

    private boolean isNumberAlreadyChosen(int[] array, int number) {
        for (int i = 0; i < array.length; i++) {
            if(array[i] == number)
                return true;
        }
        return false;
    }
}
