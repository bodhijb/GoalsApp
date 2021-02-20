package com.jf2mc1.a015004mbrilliantapp.model;

import java.io.Serializable;
import java.time.LocalDate;


public class MiniGoal implements Serializable {

    private String miniName;
    private boolean isMiniAchieved;
    private LocalDate miniTargetDate;

    public MiniGoal(String miniName) {

        this.miniName = miniName;
        isMiniAchieved = false;
        miniTargetDate = LocalDate.of(2021, 06, 01);

    }

    public String getMiniName() {
        return miniName;
    }

    public void setMiniName(String miniName) {
        this.miniName = miniName;
    }

    public boolean isMiniAchieved() {
        return isMiniAchieved;
    }

    public void setMiniAchieved(boolean miniAchieved) {
        isMiniAchieved = miniAchieved;
    }

    public LocalDate getMiniTargetDate() {
        return miniTargetDate;
    }

    public void setMiniTargetDate(LocalDate miniTargetDate) {
        this.miniTargetDate = miniTargetDate;
    }
}
