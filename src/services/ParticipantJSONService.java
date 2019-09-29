package services;

import com.google.gson.*;
import resources.Resources;
import resources.entities.Participant;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ParticipantJSONService {

    private final String fileName = "Participants.json";

    private final Gson gson;
    
    private static ParticipantJSONService jsonService;

    private ParticipantJSONService() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public static ParticipantJSONService getJsonService() {
        if (jsonService == null)
            jsonService = new ParticipantJSONService();
        return jsonService;
    }


    public synchronized void saveParticipant(Participant participant){
        JsonArray participants;
        try {
            FileReader reader = new FileReader(Resources.DATA_LOCATION+fileName);
            JsonObject fileContent = gson.fromJson(reader, JsonObject.class);
            reader.close();

            if(fileContent == null){
                fileContent = new JsonObject();
                participants = new JsonArray();
                participants.add(gson.toJsonTree(participant));
                fileContent.add("Participants", participants);
            }else{
                participants = fileContent.get("Participants").getAsJsonArray();
                participants.add(gson.toJsonTree(participant));
                fileContent = new JsonObject();
                fileContent.add("Participants", participants);
            }
            FileWriter writer = new FileWriter(Resources.DATA_LOCATION+fileName);
            gson.toJson(fileContent, writer);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
