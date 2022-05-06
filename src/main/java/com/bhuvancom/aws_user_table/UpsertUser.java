package com.bhuvancom.aws_user_table;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

public class UpsertUser implements RequestHandler<Map<String, String>, String> {
    private static final AmazonDynamoDB dbClient = AmazonDynamoDBClientBuilder.standard().build();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public String handleRequest(Map<String, String> event, Context context) {
        LambdaLogger logger = context.getLogger();
        User u = new User();
        u.setNote(event.get("note"));
        int noteId = -1;
        try {
            noteId = Integer.parseInt(event.get("noteId"));
        } catch (NumberFormatException e) {
            logger.log("Error converting noteId to integer : actual value " + event.get("noteId"));
        }
        u.setNoteId(noteId);
        u.setUserId(event.get("userId"));
        DynamoDBMapper mapper = new DynamoDBMapper(dbClient);
        mapper.save(u);
        return gson.toJson(u);
    }
}
