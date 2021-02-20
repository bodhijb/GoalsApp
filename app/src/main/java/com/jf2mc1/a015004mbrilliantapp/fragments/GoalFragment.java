package com.jf2mc1.a015004mbrilliantapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jf2mc1.a015004mbrilliantapp.R;
import com.jf2mc1.a015004mbrilliantapp.adapter.GoalRecyclerAdapter;
import com.jf2mc1.a015004mbrilliantapp.model.Goal;
import com.jf2mc1.a015004mbrilliantapp.adapter.GoalManager;

import java.util.ArrayList;
import java.util.List;


public class GoalFragment extends Fragment
implements GoalRecyclerAdapter.GoalFirstListener {

    /* the fragment will hold and manage all the UIs that were in MainActiv
    */
    private RecyclerView mGoalRecyclerView;
    private GoalRecyclerAdapter mGoalRecyclerAdapter;
    private GoalManager mGoalManager;
    private ArrayList<Goal> mGoalList = new ArrayList<>();



    public interface GoalSecondListener {
        void goalSecondListenerMethod(Goal goal);
    }

    private GoalSecondListener mGoalSecondListener;







    public GoalFragment() {
        // Required empty public constructor
    }

    public static GoalFragment newInstance() {
        return new GoalFragment();
    }

    // called when fragment is in the process of being created


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    // attached frag to its activity. onCreate called after
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // do stuff before the fragment is created
        mGoalManager = new GoalManager(context);
        mGoalList.clear();
        if(mGoalManager.retrieveGoals()
        .size() > 0) {
            for(Goal goal: mGoalManager.retrieveGoals()) {
                mGoalList.add(goal);
            }
        }
        // assign context to SGL
        if(context instanceof GoalSecondListener) {
            mGoalSecondListener = (GoalSecondListener) context;

        } else {
            throw new RuntimeException("Hey Developer, the context must" +
                    "implement the OnGoalInteractionListener interface");
        }
    }




    // called when fragment wants to show the layout on the activity
    // good place to initialize UIs
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        view with UIs attached
        return inflater.inflate(R.layout.fragment_goal, container, false);
    }

    // when frags views have been instantiated
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        initialize UIs as soon as view is inflated
        // check view has been instantiated
        if(getView() != null) {
            // getView == root view for the fragment's layout
            // getActivity == the fragments supporting activity
            mGoalRecyclerView = getView().findViewById(R.id.goal_recyclerview);
            mGoalRecyclerAdapter = new GoalRecyclerAdapter(mGoalList, this);
            mGoalRecyclerView.setAdapter(mGoalRecyclerAdapter);
            mGoalRecyclerView.setLayoutManager(new LinearLayoutManager(
                    getActivity()));
        }

    }

    // called when frag is from acivt or activ is destroyed
    @Override
    public void onDetach() {
        super.onDetach();
        // dont need this anymore,
        mGoalSecondListener = null;
    }

    @Override
    public void goalFirstListenerMethod(Goal goal) {
        mGoalSecondListener.goalSecondListenerMethod(goal);

    }

    // HELPFUL METHODS

// new goal from create goal dialog
    public void giveNewGoalToManager(Goal goal) {
        mGoalManager.saveGoalToSharedPreferences(goal);

        // update recycler adapter
        GoalRecyclerAdapter goalRecyclerAdapter = (GoalRecyclerAdapter)
                mGoalRecyclerView
                .getAdapter();

        goalRecyclerAdapter.addGoalToRecyclerview(goal);

    }

    // update an existing goal with updated minigoals
    public void saveGoal(Goal goal) {
        mGoalManager.saveGoalToSharedPreferences(goal);

        // update recycler view
        updateRecyclerViewFromSharedPreferences();


    }

    private void updateRecyclerViewFromSharedPreferences() {

        //retrieve updated goals from sp
        ArrayList<Goal> goals = mGoalManager.retrieveGoals();
        mGoalRecyclerView.setAdapter(new GoalRecyclerAdapter(goals,
               this));

    }

    public GoalManager getGoalManager() {
        return mGoalManager;
    }
}