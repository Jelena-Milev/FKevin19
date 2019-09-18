package services;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import resources.Resources;
import resources.entities.ClosedQuestion;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class QuestionService {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private final String fileName = "Questions.json";

    private final int numberOfQuestions = 20;

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
        ClosedQuestion[] allQuestions = this.getClosedQuestions();

        int[] randomIndexes = this.getRandomNumbers(this.numberOfQuestions, allQuestions.length);

        ClosedQuestion[] randomQuestions = new ClosedQuestion[randomIndexes.length];

        for (int i = 0; i < randomIndexes.length; i++) {
            randomQuestions[i] = allQuestions[randomIndexes[i]];
        }
        return randomQuestions;
    }

    private ClosedQuestion[] getClosedQuestions() {
        JsonArray jsonQuestions = this.getJsonQuestions();
        ClosedQuestion[] questions;
        if (jsonQuestions == null) {
            questions = new ClosedQuestion[0];
        } else {
            questions = gson.fromJson(jsonQuestions, ClosedQuestion[].class);
        }

        return questions;
    }

    private JsonArray getJsonQuestions() {
        JsonArray jsonQuestions = null;
        try (JsonReader reader = new JsonReader(new FileReader(Resources.DATA_LOCATION+this.fileName))) {
            JsonElement fileContent = gson.fromJson(reader, JsonObject.class);
                jsonQuestions = fileContent.getAsJsonObject().get("ClosedQuestions").getAsJsonArray();
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
