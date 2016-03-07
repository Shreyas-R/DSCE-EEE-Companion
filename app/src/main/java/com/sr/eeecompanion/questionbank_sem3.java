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
 * Created by Shreyas R on 19-09-2015.
 */
public class questionbank_sem3 extends AppCompatActivity{
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.drawer_recyclerView)
    RecyclerView drawerRecyclerView;
    private DrawerLayout mDrawerLayout;
    Button btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subjects_6n2_layout);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.questionbank_sem3, R.string.questionbank_sem3);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        btn3 = (Button) findViewById(R.id.button_subject1);
        btn4 = (Button) findViewById(R.id.button_subject2);
        btn5 = (Button) findViewById(R.id.button_subject3);
        btn6 = (Button) findViewById(R.id.button_subject4);
        btn7 = (Button) findViewById(R.id.button_subject5);
        btn8 = (Button) findViewById(R.id.button_subject6);
        btn9 = (Button) findViewById(R.id.button_lab1);
        btn10 = (Button) findViewById(R.id.button_lab2);

        List<String> rows = new ArrayList<>();
        rows.add("Home");

        DrawerAdapter drawerAdapter = new DrawerAdapter(rows);
        drawerRecyclerView.setAdapter(drawerAdapter);
        drawerRecyclerView.setHasFixedSize(true);
        drawerRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        final GestureDetector mGestureDetector = new GestureDetector(questionbank_sem3.this, new GestureDetector.SimpleOnGestureListener() {

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
        if (v.getId() == R.id.button_subject1) {
            Snackbar.make(drawerLayout, "Coming Soon", Snackbar.LENGTH_LONG).show();

        }
        else if (v.getId() == R.id.button_subject2) {
            Snackbar.make(drawerLayout, "Coming Soon", Snackbar.LENGTH_LONG).show();
        }
        else if (v.getId() == R.id.button_subject3) {
            Snackbar.make(drawerLayout, "Coming Soon", Snackbar.LENGTH_LONG).show();
        }
        else if (v.getId() == R.id.button_subject4) {
            Snackbar.make(drawerLayout, "Coming Soon", Snackbar.LENGTH_LONG).show();
        }
        else if (v.getId() == R.id.button_subject5) {
            Snackbar.make(drawerLayout, "Coming Soon", Snackbar.LENGTH_LONG).show();
        }
        else if (v.getId() == R.id.button_subject6) {
            Snackbar.make(drawerLayout, "Coming Soon", Snackbar.LENGTH_LONG).show();
        }
        else if (v.getId() == R.id.button_lab1) {
            Snackbar.make(drawerLayout, "Coming Soon", Snackbar.LENGTH_LONG).show();
        }
        else if (v.getId() == R.id.button_lab2) {
            Snackbar.make(drawerLayout, "Coming Soon", Snackbar.LENGTH_LONG).show();
        }


    }
}
