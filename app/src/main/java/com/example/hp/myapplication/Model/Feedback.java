package com.example.hp.myapplication.Model;

import java.io.Serializable;

public class Feedback implements Serializable {
    private static String feedbackIndex = "";
    private String feedbackId;
    private String description;
    private static int increment = 0;

    public Feedback(){
        increment += 1;
    }
    public Feedback(String description) {
        increment += 1;
        this.description = description;
    }
    private static String setFeedbackId() {
        return feedbackIndex+increment;
    }
    public String getFeedbackId(){
        feedbackId = setFeedbackId();
        return feedbackId;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
