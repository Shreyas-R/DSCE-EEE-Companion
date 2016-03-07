package com.sr.eeecompanion;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Shreyas R on 19-09-2015.
 */
public class MEq extends AppCompatActivity {
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.drawer_recyclerView)
    RecyclerView drawerRecyclerView;
    private DrawerLayout mDrawerLayout;
    Button btn1, btn2, btn3, btn4, btn5, btn6;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    int i = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionbank_years_layout);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);

        init();

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.questionbank_me_name, R.string.questionbank_me_name);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        btn1 = (Button) findViewById(R.id.button_2010);
        btn2 = (Button) findViewById(R.id.button_2011);
        btn3 = (Button) findViewById(R.id.button_2012);
        btn4 = (Button) findViewById(R.id.button_2013);
        btn5 = (Button) findViewById(R.id.button_2014);
        btn6 = (Button) findViewById(R.id.button_2015);

        List<String> rows = new ArrayList<>();
        rows.add("Home");
        rows.add("V Semester");

        DrawerAdapter drawerAdapter = new DrawerAdapter(rows);
        drawerRecyclerView.setAdapter(drawerAdapter);
        drawerRecyclerView.setHasFixedSize(true);
        drawerRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        final GestureDetector mGestureDetector = new GestureDetector(MEq.this, new GestureDetector.SimpleOnGestureListener() {

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
        });


    }

    public void onClick(View v) {
        viewPager = (ViewPager) findViewById(R.id.viewpager_questionpaper);
        if (v.getId() == R.id.button_2010) {
            i = 1;
            setupViewPager(viewPager);
        } else if (v.getId() == R.id.button_2011) {
            i = 2;
            setupViewPager(viewPager);


        } else if (v.getId() == R.id.button_2012) {
            i = 3;
            setupViewPager(viewPager);


        } else if (v.getId() == R.id.button_2013) {
            i = 4;
            setupViewPager(viewPager);


        } else if (v.getId() == R.id.button_2014) {
            i = 5;
            setupViewPager(viewPager);


        } else if (v.getId() == R.id.button_2015) {
            i = 6;
            setupViewPager(viewPager);
        }
        tabLayout = (TabLayout) findViewById(R.id.tabs_questionpaper);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        if (i == 6| i == 0) {
            adapter.notifyDataSetChanged();
            adapter.addFragment(new mejun2015(), "JUNE");
            adapter.addFragment(new medec2015(), "DEC");

        }
        if (i == 5) {
            adapter.notifyDataSetChanged();
            adapter.addFragment(new mejun2014(), "JUNE");
            adapter.addFragment(new medec2014(), "DEC");
        }
        if (i == 4) {
            adapter.notifyDataSetChanged();
            adapter.addFragment(new mejun2013(), "JUNE");
            adapter.addFragment(new medec2013(), "DEC");
        }
        if (i == 3) {
            adapter.notifyDataSetChanged();
            adapter.addFragment(new mejun2012(), "JUNE");
            adapter.addFragment(new medec2012(), "DEC");
        }
        if (i == 2) {
            adapter.notifyDataSetChanged();
            adapter.addFragment(new mejun2011(), "JUNE");
            adapter.addFragment(new medec2011(), "DEC");
        }
        if (i == 1) {
            adapter.notifyDataSetChanged();
            adapter.addFragment(new mejun2010(), "JUNE");
            adapter.addFragment(new medec2010(), "DEC");
        }

        viewPager.setAdapter(adapter);
    }

    public void init() {
        if (i == 0) {
            viewPager = (ViewPager) findViewById(R.id.viewpager_questionpaper);
            setupViewPager(viewPager);
            tabLayout = (TabLayout) findViewById(R.id.tabs_questionpaper);
            tabLayout.setupWithViewPager(viewPager);
        }
    }

}

