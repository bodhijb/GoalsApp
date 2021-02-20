package com.jf2mc1.a015004mbrilliantapp.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jf2mc1.a015004mbrilliantapp.R;
import com.jf2mc1.a015004mbrilliantapp.adapter.MiniGoalRecyclerAdapter;
import com.jf2mc1.a015004mbrilliantapp.model.Goal;
import com.jf2mc1.a015004mbrilliantapp.model.MiniGoal;

public class MiniGoalsActivity extends AppCompatActivity {

    private RecyclerView miniGoalsRecyclerView;
    private FloatingActionButton addMiniGoalFloatingActionButton;
    private Goal mGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_goals);

        mGoal = (Goal) getIntent().getSerializableExtra(MainActivity.GOAL_OBJECT_KEY);

        setTitle(mGoal.getGoalName());

        miniGoalsRecyclerView = findViewById(R.id.minigoals_recyclerview);
        miniGoalsRecyclerView.setAdapter(new MiniGoalRecyclerAdapter(mGoal));
        miniGoalsRecyclerView.setLayoutManager(new
                LinearLayoutManager(getApplicationContext()));

        addMiniGoalFloatingActionButton = findViewById(R.id.add_minigoal_fabutton);
        addMiniGoalFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                displayAddMiniGoalDialog();
            }
        });
    }

    private void displayAddMiniGoalDialog() {

        //title, edittext, button title
        String title = getString(R.string.minigoal_dialog_title);
        String positiveBtnTitle = getString(R.string.minigoal_positive_btn_title);
        final EditText editText = new EditText(getApplicationContext());
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        editText.setEms(50);

        // dialog to add a minigoal to the goals arraylist of minigoals
        new AlertDialog.Builder(MiniGoalsActivity.this)
                .setTitle(title)
                .setView(editText)
                .setPositiveButton(positiveBtnTitle,
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MiniGoal miniGoal = new MiniGoal(
                             editText.getText().toString().trim());
                        mGoal.getMiniGoals().add(miniGoal);

                        // update the minigoal recyclerview
                        MiniGoalRecyclerAdapter miniGoalRecyclerAdapter =
                                (MiniGoalRecyclerAdapter) miniGoalsRecyclerView
                                        .getAdapter();
                        miniGoalRecyclerAdapter.notifyItemInserted(
                                mGoal.getMiniGoals().size() - 1);

                        dialog.dismiss();
                    }
                })
        .create().show();
    }

    @Override
    public void onBackPressed() {

        // send the updated goal back to mainactivity
        // create bundle
        Bundle bundle = new Bundle();
        bundle.putSerializable(MainActivity.GOAL_OBJECT_KEY, mGoal);
        // create intent
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(bundle);
        // setresult confirms process was successful
        setResult(RESULT_OK, intent);
        super.onBackPressed();

    }
}