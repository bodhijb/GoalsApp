package com.jf2mc1.a015004mbrilliantapp.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jf2mc1.a015004mbrilliantapp.model.Goal;
import com.jf2mc1.a015004mbrilliantapp.model.MiniGoal;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class GoalManager {

    private Context mContext;
    SharedPreferences mSharedPreferences;

    public GoalManager(Context context) {
        mContext = context;
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    }


    public void saveGoalToSharedPreferences(Goal goal) {

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(goal);
        Log.i("SAVED", json);
        editor.putString(goal.getGoalName(), json);
        editor.apply();


    }

    public void deleteWorkout(Goal goal) {
        if (mSharedPreferences.contains(goal.getGoalName())) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.remove(goal.getGoalName())
                    .commit();
            Log.i("DELETED", "goal del");
        }
    }


    //******************************************

    public ArrayList<Goal> retrieveGoals() {

        Map<String, ?> data = mSharedPreferences.getAll();
        ArrayList<Goal> goalsList = new ArrayList<>();

        for (Map.Entry<String, ?> entry : data.entrySet()) {

            if (entry.getKey().matches("variations_seed_native_stored"))
                continue;

            String serializedObject = mSharedPreferences.getString(entry.getKey(), "");
            if (serializedObject != null) {
                Gson gson = new Gson();
                Type type = new TypeToken<Goal>(){}.getType();
                Goal goal = gson.fromJson(serializedObject, type);
                goalsList.add(goal);
            }
        }

        return goalsList;
    }


}
