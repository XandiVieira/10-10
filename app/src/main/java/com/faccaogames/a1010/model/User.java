package com.faccaogames.a1010.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;
    private String token;
    private String userName;
    private String profilePhoto;
    private boolean premium;
    private int power;
    private List<Experiment> experiments;
}
