package com.jf2mc1.a015004mbrilliantapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jf2mc1.a015004mbrilliantapp.R;
import com.jf2mc1.a015004mbrilliantapp.model.Goal;
import com.jf2mc1.a015004mbrilliantapp.viewholder.GoalViewHolder;
import java.util.ArrayList;


public class GoalRecyclerAdapter extends RecyclerView.Adapter<GoalViewHolder> {

    public ArrayList<Goal> goals;
    private GoalFirstListener mGoalFirstListener;
    private boolean oddClick = true;

    public GoalRecyclerAdapter(ArrayList<Goal> goals,
                               GoalFirstListener goalFirstListener) {
        this.goals = goals;
        this.mGoalFirstListener = goalFirstListener;
    }

    public interface GoalFirstListener {
        void goalFirstListenerMethod(Goal goal);
    }

    // parent specifies the activity where the recyclerview will be shown
    @NonNull
    @Override
    public GoalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(
                parent.getContext());
        View view = layoutInflater.inflate(R.layout.goal_viewholder,
                parent, false);
        return new GoalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalViewHolder holder, int position) {

        holder.getTxtGoalName().setText((position + 1) + ": " +
                goals.get(position).getGoalName());

        holder.getTxtGoalTargetDate().setText("By: " + goals.get(position)
        .getGoalTargetDate().toString());

        Goal thisGoal = goals.get(position);

        holder.getTxtNumberMiniGoals().setText(Integer.toString(goals.get(position)
        .getMiniGoals().size()) + " Mini Goals");


        holder.getBtnGoalAchieved().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.btn_goal_achieved) {
                    if(oddClick) {
                        view.setBackgroundResource(R.drawable.smile);
                        thisGoal.setGoalAchieved(true);
                        oddClick = !oddClick;
                        // Log.i("ACHBTN", thisGoal.isGoalAchieved()+" s/b achieved");
                    } else {
                        view.setBackgroundResource(R.drawable.meh);
                        thisGoal.setGoalAchieved(false);
                        oddClick = !oddClick;
                        // Log.i("ACHBTN", thisGoal.isGoalAchieved()+" s/b/ not achieved");
                    }
                }
            }
        });

//        when an itemView is clicked, the GFL interface lets all implementers know which goal
//        has been clicked
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGoalFirstListener.goalFirstListenerMethod(goals.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return goals.size();
    }

    public void addGoalToRecyclerview(Goal goal) {
        goals.add(goal);
        notifyItemInserted(goals.size() - 1);

    }




}
