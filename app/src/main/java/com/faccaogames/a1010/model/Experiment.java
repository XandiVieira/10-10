package com.faccaogames.a1010.model;

import com.faccaogames.a1010.model.enums.CategoryEnum;
import com.faccaogames.a1010.model.enums.StatusEnum;

import java.util.List;

public class Experiment {

    private String userId;
    private StatusEnum status;
    private CategoryEnum category;
    private String contentPath;
    private int valuations;
    private List<Criteria> criteria;
    private List<String> comments;
    private List<String> alreadyRatedBy;
    private List<Report> reports;
    private boolean commentsOn;
    private Long createdAt;
}
