package com.jf2mc1.a015004mbrilliantapp.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jf2mc1.a015004mbrilliantapp.R;

public class GoalViewHolder extends RecyclerView.ViewHolder {

    private TextView txtGoalName;
    private Button btnGoalAchieved;
    private TextView txtGoalTargetDate;
    private TextView txtNumberMiniGoals;



    public GoalViewHolder(@NonNull View view) {
        super(view);
        txtGoalName = view.findViewById(R.id.txt_goal_name);
        btnGoalAchieved = view.findViewById(R.id.btn_goal_achieved);
        txtGoalTargetDate = view.findViewById(R.id.txt_goal_target_date);
        txtNumberMiniGoals = view.findViewById(R.id.txt_number_mini_goals);

    }

    public TextView getTxtGoalName() {
        return txtGoalName;
    }

    public TextView getBtnGoalAchieved() {
        return btnGoalAchieved;
    }

    public TextView getTxtGoalTargetDate() {
        return txtGoalTargetDate;
    }

    public TextView getTxtNumberMiniGoals() {
        return txtNumberMiniGoals;
    }
}
