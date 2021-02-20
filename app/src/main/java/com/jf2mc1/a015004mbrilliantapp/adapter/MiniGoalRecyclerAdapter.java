package com.jf2mc1.a015004mbrilliantapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jf2mc1.a015004mbrilliantapp.R;
import com.jf2mc1.a015004mbrilliantapp.model.Goal;
import com.jf2mc1.a015004mbrilliantapp.model.MiniGoal;
import com.jf2mc1.a015004mbrilliantapp.viewholder.MiniGoalViewHolder;

import java.util.ArrayList;

public class MiniGoalRecyclerAdapter extends RecyclerView.Adapter<MiniGoalViewHolder> {

    private Goal mGoal;
    private boolean oddClick = true;

    public MiniGoalRecyclerAdapter(Goal goal) {
        mGoal = goal;
    }


    @NonNull
    @Override
    public MiniGoalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // layout inflater/context
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // view li inflate
        View view = layoutInflater.inflate(R.layout.minigoal_viewholder, parent,
                false);

        return new MiniGoalViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MiniGoalViewHolder holder, int position) {

        MiniGoal thisMini = mGoal.getMiniGoals().get(position);

        // bind viewholder views with minigoal data
        holder.getMiniGoalNameTextView().setText(thisMini.getMiniName());

        holder.getTxtMiniTargetDate().setText("By: " + thisMini.getMiniTargetDate().toString());

        holder.getMiniGoalAchievedButton().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.btn_minigoal_achieved) {
                    if(oddClick) {
                        view.setBackgroundResource(R.drawable.smile);
                        thisMini.setMiniAchieved(true);
                        oddClick = !oddClick;
                        // Log.i("MACHBTN", thisMini.isMiniAchieved()+" s/b achieved");
                    } else {
                        view.setBackgroundResource(R.drawable.meh);
                        thisMini.setMiniAchieved(false);
                        oddClick = !oddClick;
                        // Log.i("MACHBTN", thisMini.isMiniAchieved()+" s/b/ not achieved");
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mGoal.getMiniGoals().size();
    }

    public Goal getGoal() {
        return mGoal;
    }

    public void setGoal(Goal goal) {

        mGoal = goal;
    }

}
