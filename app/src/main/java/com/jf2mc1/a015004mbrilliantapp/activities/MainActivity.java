package com.jf2mc1.a015004mbrilliantapp.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jf2mc1.a015004mbrilliantapp.R;
import com.jf2mc1.a015004mbrilliantapp.adapter.GoalRecyclerAdapter;
import com.jf2mc1.a015004mbrilliantapp.adapter.MiniGoalRecyclerAdapter;
import com.jf2mc1.a015004mbrilliantapp.fragments.GoalFragment;
import com.jf2mc1.a015004mbrilliantapp.fragments.MiniFragment;
import com.jf2mc1.a015004mbrilliantapp.model.Goal;
import com.jf2mc1.a015004mbrilliantapp.model.MiniGoal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.FrameLayout;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements
        GoalFragment.GoalSecondListener {


    public static final String GOAL_OBJECT_KEY = "Goal_Key";
    public static final int MAIN_ACTIVITY_REQUEST_CODE = 500;

    private GoalFragment mGoalFragment;
    private boolean isTablet;
    private MiniFragment mMiniFragment;
    FloatingActionButton fab;
    private FrameLayout miniGoalsFragmentContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //*************************
        mGoalFragment = (GoalFragment) getSupportFragmentManager()
                .findFragmentById(R.id.goal_fragment);
        miniGoalsFragmentContainer = findViewById(
                R.id.minigoals_fragment_container);
        isTablet = miniGoalsFragmentContainer != null;
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCreateGoalDialog();
            }
        });
    }

    private void displayCreateGoalDialog() {

        String alertTitle = getString(R.string.create_goal_alert_title);
        String positiveButtonTitle = getString(
                R.string.create_goal_pos_btn_title);

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
                MainActivity.this);
        EditText editText = new EditText(getApplicationContext());
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        editText.setMaxEms(50);

        alertBuilder.setTitle(alertTitle);
        alertBuilder.setView(editText);
        alertBuilder.setPositiveButton(positiveButtonTitle,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // create a new goal and save
                        Goal goal = new Goal(editText.getText()
                                .toString().trim());

                        mGoalFragment.giveNewGoalToManager(goal);
                        dialog.dismiss();
                        displayMiniGoals(goal);
                    }
                });
        alertBuilder.create().show();

    }


    private void displayMiniGoals(Goal goal) {

        if (!isTablet) {
            Intent miniGoalsIntent = new Intent(this,
                    MiniGoalsActivity.class);
            miniGoalsIntent.putExtra(GOAL_OBJECT_KEY, goal);
            startActivityForResult(miniGoalsIntent,
                    MAIN_ACTIVITY_REQUEST_CODE);
        } else {
            // change title of actionbar
            setTitle(goal.getGoalName());
            // initialize mini fragment
            mMiniFragment = MiniFragment.newInstance(goal);
            // bind fragment to frame layout container
            if (mMiniFragment != null) {
                // remove existing mini fragment
                getSupportFragmentManager()
                        .beginTransaction()
                        .remove(mMiniFragment)
                        .commit();
                mMiniFragment = null;

                // reinitialize mMiniFragment
                mMiniFragment = MiniFragment.newInstance(goal);
                // add it to container, backstack
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.minigoals_fragment_container,
                                mMiniFragment)
                        .addToBackStack(null)
                        .commit();

                // change functionality of fab
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        displayCreateMiniGoalDialog();
                    }
                });
            }
        }
    }

    private void displayCreateMiniGoalDialog() {

        final EditText miniEditText = new EditText(this);
        miniEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        miniEditText.setEms(50);

        // dialog to add a minigoal to the goals arraylist of minigoals
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Enter Mini Goal Name")
                .setView(miniEditText)
                .setPositiveButton("Create",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MiniGoal miniGoal = new MiniGoal(
                                        miniEditText.getText().toString().trim());
                                mMiniFragment.addMiniGoalToCategory(miniGoal);

                                dialog.dismiss();
                            }
                        })
                .create().show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // reqCode == MainAct.Resuestcode, resultcode == RESULS_OK,
        // data holds the bundle
        if (requestCode == MAIN_ACTIVITY_REQUEST_CODE &&
                resultCode == Activity.RESULT_OK) {
            if (data != null) {
                // save already existing, updated goal to shared prefs
                mGoalFragment.saveGoal((Goal) data.getSerializableExtra(GOAL_OBJECT_KEY));

            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void goalSecondListenerMethod(Goal goal) {
        displayMiniGoals(goal);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setTitle(getString(R.string.app_name));
        if (mMiniFragment.getGoal() != null) {
            mGoalFragment.getGoalManager().saveGoalToSharedPreferences(
                    mMiniFragment.getGoal());
        }
        if (mMiniFragment != null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(mMiniFragment)
                    .commit();
            mMiniFragment = null;
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayCreateGoalDialog();
            }
        });


    }
}