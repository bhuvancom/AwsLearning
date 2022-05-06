package com.bhuvancom.aws_user_table;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.codec.binary.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListUsers implements RequestHandler<Map<String, String>, String> {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final AmazonDynamoDB dbClient = AmazonDynamoDBClientBuilder.standard().build();

    public String handleRequest(Map<String, String> event, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Env Variables : " + gson.toJson(System.getenv()));
        logger.log("CONTEXT: " + gson.toJson(context));
        // process event
        logger.log("EVENT: " + gson.toJson(event));
        logger.log("EVENT TYPE: " + event.getClass().toString());
        List<User> users = new ArrayList<>();
        String tblName = System.getenv("TABLE_NAME");
        DynamoDBMapper mapper = new DynamoDBMapper(dbClient);
        if (event.get("userId") == null || event.get("userId").isBlank()) {
            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
            users.addAll(mapper.scan(User.class, scanExpression));
        } else {
            users.add(mapper.load(User.class, event.get("userId")));
        }
        return gson.toJson(users);
    }
}
