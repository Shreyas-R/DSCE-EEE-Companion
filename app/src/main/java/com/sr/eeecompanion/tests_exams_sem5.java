package com.sr.eeecompanion;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Shreyas R on 18-09-2015.
 */

public class tests_exams_sem5 extends AppCompatActivity {
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.drawer_recyclerView)
    RecyclerView drawerRecyclerView;
    private DrawerLayout mDrawerLayout;
    Button btn1,btn2,btn3,btn4;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tests_exams_layout);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.tests_exams_header, R.string.tests_exams_header);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        btn1 = (Button) findViewById(R.id.button_tests_exams_choice1);
        btn2 = (Button) findViewById(R.id.button_tests_exams_choice2);
        btn3 = (Button) findViewById(R.id.button_tests_exams_choice3);
        btn4 = (Button) findViewById(R.id.button_tests_exams_choice4);

        List<String> rows = new ArrayList<>();
        rows.add("Home");

        DrawerAdapter drawerAdapter = new DrawerAdapter(rows);
        drawerRecyclerView.setAdapter(drawerAdapter);
        drawerRecyclerView.setHasFixedSize(true);
        drawerRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        final GestureDetector mGestureDetector = new GestureDetector(tests_exams_sem5.this, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });

        drawerRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());


                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                    if (recyclerView.getChildPosition(child) == 0) {
                        startActivity(new Intent(getApplicationContext(), About.class));
                        mDrawerLayout.closeDrawers();
                    }
                    if (recyclerView.getChildPosition(child) == 1) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        mDrawerLayout.closeDrawers();
                    }

                    return true;

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }
        });




    }
    public void onClick(View v) {
        if (v.getId() == R.id.button_tests_exams_choice1) {
            Snackbar.make(drawerLayout, "Coming Soon", Snackbar.LENGTH_LONG).show();
        }
        else if (v.getId() == R.id.button_tests_exams_choice2) {
            Snackbar.make(drawerLayout, "Coming Soon", Snackbar.LENGTH_LONG).show();
        }
        else if (v.getId() == R.id.button_tests_exams_choice3) {
            Intent intent = new Intent(tests_exams_sem5.this, externalexam_timetable_sem5.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.button_tests_exams_choice4) {
            Snackbar.make(drawerLayout, "Coming Soon", Snackbar.LENGTH_LONG).show();
        }

    }

}
