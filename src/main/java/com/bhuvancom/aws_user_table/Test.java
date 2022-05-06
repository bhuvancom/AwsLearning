package com.bhuvancom.aws_user_table;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>(2);
        userList.add(new User("test1",1,"note1"));
        userList.add(new User("test3",1,"note12"));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(userList));
    }
}
