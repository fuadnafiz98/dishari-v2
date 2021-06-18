package org.tensorflow.lite.examples.detection;

public class Data {
    private double distance;

//    private String title;
//    private String message;

    public Data(double distance) {
        this.distance = distance;
//        this.message = message;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
/*
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    */
}
