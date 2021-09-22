package com.faccaogames.a1010.model;

import com.faccaogames.a1010.model.enums.CategoryEnum;
import com.faccaogames.a1010.model.enums.StatusEnum;

import java.util.List;

public class Experiment {
    String userId;
    StatusEnum status;
    CategoryEnum category;
    int valuations;
    String contentPath;
    List<Criteria> criteria;
    List<String> comments;
    List<String> alreadyRatedBy;
    List<Report> reports;
    boolean commentsOn;
    Long createdAt;
}
