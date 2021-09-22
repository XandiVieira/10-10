package com.faccaogames.a1010.model;

public class Experiment {
String userId;
    StatusEnum status;
    CategoryEnum category;
    int valuations;
    String contentPath;
    List<Criteria>criteria;
    List<String>comments;
    List<String>alreadyRatedby;
    List<Report>reports;
    boolean commentsOn;
    Long createdAt;
}
