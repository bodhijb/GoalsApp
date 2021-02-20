package com.jf2mc1.a015004mbrilliantapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jf2mc1.a015004mbrilliantapp.R;
import com.jf2mc1.a015004mbrilliantapp.adapter.MiniGoalRecyclerAdapter;
import com.jf2mc1.a015004mbrilliantapp.model.Goal;
import com.jf2mc1.a015004mbrilliantapp.model.MiniGoal;
import com.jf2mc1.a015004mbrilliantapp.viewholder.MiniGoalViewHolder;

public class MiniFragment extends Fragment {

    private RecyclerView miniGoalsRecyclerView;
    private Goal mGoal;
    private static final String GOAL_ARGS_KEY = "Goal Args";


    public MiniFragment() {
        // Required empty public constructor
    }


    // how activities send objects to fragments (in args)
    // obj saved in fragment's bundle
    public static MiniFragment newInstance(Goal goal) {
        MiniFragment miniFragment = new MiniFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(GOAL_ARGS_KEY, goal);
        miniFragment.setArguments(bundle);
        return miniFragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // access the fragment bundle/arguments
        // assign the bundle obj to the goal
        if(getArguments() != null) {
            mGoal = (Goal) getArguments().getSerializable(GOAL_ARGS_KEY);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mini, container,
                false);
        if(view != null) {
            miniGoalsRecyclerView = view.findViewById(R.id.minigoals_recyclerview);
            miniGoalsRecyclerView.setAdapter(new MiniGoalRecyclerAdapter(mGoal));
            miniGoalsRecyclerView.setLayoutManager(
                    new LinearLayoutManager(getContext()));

        }
        // Inflate the layout for this fragment
        return view;
    }

    public void addMiniGoalToCategory(MiniGoal miniGoal) {
        // add mini to goal's arraylist
        mGoal.getMiniGoals().add(miniGoal);
        // update mini recyclerview
        MiniGoalRecyclerAdapter miniGoalRecyclerAdapter =
                (MiniGoalRecyclerAdapter) miniGoalsRecyclerView
                        .getAdapter();

        // update the mini recyclerview data with a new goal
        miniGoalRecyclerAdapter.setGoal(mGoal);
        miniGoalRecyclerAdapter.notifyDataSetChanged();

    }

    public Goal getGoal() {
        return mGoal;
    }
}