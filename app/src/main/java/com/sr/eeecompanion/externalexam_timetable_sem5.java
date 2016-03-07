package com.sr.eeecompanion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Shreyas R on 19-09-2015.
 */
public class externalexam_timetable_sem5 extends AppCompatActivity {
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.drawer_recyclerView)
    RecyclerView drawerRecyclerView;
    private DrawerLayout mDrawerLayout;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.externalexam_timetable_layout);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.externalexam_timetable_header, R.string.externalexam_timetable_header);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        List<String> rows = new ArrayList<>();
        rows.add("Home");
        rows.add("V Semester");

        DrawerAdapter drawerAdapter = new DrawerAdapter(rows);
        drawerRecyclerView.setAdapter(drawerAdapter);
        drawerRecyclerView.setHasFixedSize(true);
        drawerRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        final GestureDetector mGestureDetector = new GestureDetector(externalexam_timetable_sem5.this, new GestureDetector.SimpleOnGestureListener() {

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
                    if (recyclerView.getChildPosition(child) == 2) {
                        startActivity(new Intent(getApplicationContext(), sem5.class));
                        mDrawerLayout.closeDrawers();
                    }

                    return true;

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });
    }
}
