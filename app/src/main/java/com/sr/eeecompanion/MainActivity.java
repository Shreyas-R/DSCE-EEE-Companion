package com.sr.eeecompanion;



import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends AppCompatActivity {
    private static final String PRIVATE_PREF = "EEE Companion";
    private static final String VERSION_KEY = "version_number";
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.drawer_recyclerView)
    RecyclerView drawerRecyclerView;
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    private DrawerLayout mDrawerLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Button btn3,btn4,btn5,btn6,btn7,btn8,btn9;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager_activity_main_home);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs_activity_main_home);
        tabLayout.setupWithViewPager(viewPager);

        btn3 = (Button) findViewById(R.id.buttonIIIsem);
        btn4 = (Button) findViewById(R.id.buttonIVsem);
        btn5 = (Button) findViewById(R.id.buttonVsem);
        btn6 = (Button) findViewById(R.id.buttonVIsem);
        btn7 = (Button) findViewById(R.id.buttonVIIsem);
        btn8 = (Button) findViewById(R.id.buttonVIIIsem);
        btn9 = (Button) findViewById(R.id.button_calenderofevents);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        init();

        Snackbar.make(drawerLayout, "Welcome to EEE Companion App!", Snackbar.LENGTH_LONG).show();


        List<String> rows = new ArrayList<>();
        rows.add("Login");
        rows.add("MATLAB Basics");
        rows.add("Help");

        DrawerAdapter drawerAdapter = new DrawerAdapter(rows);
        drawerRecyclerView.setAdapter(drawerAdapter);
        drawerRecyclerView.setHasFixedSize(true);
        drawerRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

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
                        Snackbar.make(drawerLayout, "Coming Soon", Snackbar.LENGTH_LONG).show();
                    }
                    if (recyclerView.getChildPosition(child) == 2) {
                        Snackbar.make(drawerLayout, "Coming Soon", Snackbar.LENGTH_LONG).show();
                    }
                    if (recyclerView.getChildPosition(child) == 3) {
                        Snackbar.make(drawerLayout, "Coming Soon", Snackbar.LENGTH_LONG).show();
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
    private void init() {
        SharedPreferences sharedPref    = getSharedPreferences(PRIVATE_PREF, Context.MODE_PRIVATE);
        int currentVersionNumber        = 0;

        int savedVersionNumber          = sharedPref.getInt(VERSION_KEY, 0);

        try {
            PackageInfo pi          = getPackageManager().getPackageInfo(getPackageName(), 0);
            currentVersionNumber    = pi.versionCode;
        } catch (Exception e) {}

        if (currentVersionNumber > savedVersionNumber) {
            showWhatsNewDialog();

            SharedPreferences.Editor editor   = sharedPref.edit();

            editor.putInt(VERSION_KEY, currentVersionNumber);
            editor.commit();
        }
    }

    private void showWhatsNewDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);

        View view               = inflater.inflate(R.layout.dialog_changelog, null);

        AlertDialog.Builder builder         = new AlertDialog.Builder(this);

        builder.setView(view).setTitle("Changelog - Whats New ?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();
    }
    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            moveTaskToBack(true);
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Press Back again to exit!",
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new fragment_mainactivity_newsfeed(), "Feeds");
        adapter.addFragment(new fragment_mainactivity_semesters(), "Semesters");
        adapter.addFragment(new fragment_mainactivity_events(), "Events");
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    public void onClick(View v) {
        if (v.getId() == R.id.buttonIIIsem) {
            Intent intent = new Intent(MainActivity.this, sem3.class);
            startActivity(intent);

        }
        else if (v.getId() == R.id.buttonIVsem) {
            Intent intent = new Intent(MainActivity.this, sem4.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.buttonVsem) {
            Intent intent = new Intent(MainActivity.this, sem5.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.buttonVIsem) {
            Intent intent = new Intent(MainActivity.this, sem6.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.buttonVIIsem) {
            Intent intent = new Intent(MainActivity.this, sem7.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.buttonVIIIsem) {
            Intent intent = new Intent(MainActivity.this, sem8.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.button_calenderofevents) {
            Snackbar.make(drawerLayout, "Coming Soon", Snackbar.LENGTH_LONG).show();
        }

    }
}



