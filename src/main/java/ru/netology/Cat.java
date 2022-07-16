package ru.netology;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cat {
    private final String catId;
    private final String text;
    private final String type;
    private final String user;
    private final Integer upvotes;

    public Cat(
            @JsonProperty("id") String catId,
            @JsonProperty("text") String text,
            @JsonProperty("type") String type,
            @JsonProperty("user") String user,
            @JsonProperty("upvotes") Integer upvotes
    ) {
        this.catId = catId;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
    }

    public Integer getUpvotes(){
        return this.upvotes;
    }

    @Override
    public String toString (){
        return "catId = " + catId + "\n" +
                "text = " + text + "\n" +
                "type = " + type + "\n" +
                "user = " + user + "\n" +
                "upvotes = " + upvotes+ "\n";
    }
}

