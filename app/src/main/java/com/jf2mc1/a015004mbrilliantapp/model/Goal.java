package com.jf2mc1.a015004mbrilliantapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Goal implements Serializable {

    private String goalName;
    private ArrayList<MiniGoal> mMiniGoals;
    private boolean isGoalAchieved;
    private LocalDate goalTargetDate;


    public Goal(String goalName) {

        this.goalName = goalName;
        mMiniGoals = new ArrayList<>();
        isGoalAchieved = false;
        goalTargetDate = LocalDate.of(2021, 06, 01);

    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public ArrayList<MiniGoal> getMiniGoals() {
        return mMiniGoals;
    }

    public void setMiniGoals(ArrayList<MiniGoal> miniGoals) {
        mMiniGoals = miniGoals;
    }

    public boolean isGoalAchieved() {
        return isGoalAchieved;
    }

    public void setGoalAchieved(boolean goalAchieved) {
        isGoalAchieved = goalAchieved;
    }

    public LocalDate getGoalTargetDate() {
        return goalTargetDate;
    }

    public void setGoalTargetDate(LocalDate goalTargetDate) {
        this.goalTargetDate = goalTargetDate;
    }
}
