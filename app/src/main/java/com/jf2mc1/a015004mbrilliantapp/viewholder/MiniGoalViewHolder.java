package com.jf2mc1.a015004mbrilliantapp.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jf2mc1.a015004mbrilliantapp.R;

public class MiniGoalViewHolder extends RecyclerView.ViewHolder {

    private TextView miniGoalNameTextView;
    private TextView txtMiniTargetDate;
    private Button miniGoalAchievedButton;


    public MiniGoalViewHolder(@NonNull View itemView) {
        super(itemView);
        miniGoalNameTextView = itemView.findViewById(R.id.txt_minigoal_name);
        txtMiniTargetDate = itemView.findViewById(R.id.txt_mini_target_date);
        miniGoalAchievedButton = itemView.findViewById(R.id.btn_minigoal_achieved);

    }

    public TextView getMiniGoalNameTextView() {
        return miniGoalNameTextView;
    }

    public TextView getTxtMiniTargetDate() {
        return txtMiniTargetDate;
    }

    public Button getMiniGoalAchievedButton() {
        return miniGoalAchievedButton;
    }
}
