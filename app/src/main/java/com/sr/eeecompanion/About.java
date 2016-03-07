package com.sr.eeecompanion;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
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

@SuppressWarnings("ALL")
public class About extends AppCompatActivity {
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.drawer_recyclerView)
    RecyclerView drawerRecyclerView;
    FloatingActionButton fab1,fab2,fab3,fab4,fab5,fab6,fab7;
    Button btn1,btn2;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        ButterKnife.inject(this);


        fab1 = (FloatingActionButton) findViewById(R.id.changelog_button);
        fab2 = (FloatingActionButton) findViewById(R.id.about11);
        fab3 = (FloatingActionButton) findViewById(R.id.about12);
        fab4 = (FloatingActionButton) findViewById(R.id.about13);
        fab5 = (FloatingActionButton) findViewById(R.id.about17);
        fab6 = (FloatingActionButton) findViewById(R.id.about18);
        fab7 = (FloatingActionButton) findViewById(R.id.about19);
        fab1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showWhatsNewDialog();
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.facebook.com/Shreyas.rames"));
                startActivity(intent);
            }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://plus.google.com/u/0/106526814486721136910"));
                startActivity(intent);
            }
        });
        fab4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://twitter.com/SRCOMM"));
                startActivity(intent);
            }
        });
        fab5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://forum.xda-developers.com/member.php?u=5492766"));
                startActivity(intent);
            }
        });
        fab6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://stackoverflow.com/users/5618926/shreyas-r"));
                startActivity(intent);
            }
        });
        fab7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","shreyasramesh81@gmail.com", null));
                startActivity(Intent.createChooser(intent, "Choose an E-mail client :"));
            }
        });

        btn1 = (Button) findViewById(R.id.disclaimer_policy);
        btn2 = (Button) findViewById(R.id.eula);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.about, R.string.about);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("About App");

        List<String> rows = new ArrayList<>();
        rows.add("Login");
        rows.add("MATLAB Basics");
        rows.add("Help");


        DrawerAdapter drawerAdapter = new DrawerAdapter(rows);
        drawerRecyclerView.setAdapter(drawerAdapter);
        drawerRecyclerView.setHasFixedSize(true);
        drawerRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        final GestureDetector mGestureDetector = new GestureDetector(About.this, new GestureDetector.SimpleOnGestureListener() {

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

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }

        });




    }
    private void showWhatsNewDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);

        View view               = inflater.inflate(R.layout.dialog_changelog, null);

        AlertDialog.Builder builder         = new AlertDialog.Builder(this);

        builder.setView(view).setTitle("Changelog:")
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();
    }
    public void onClick(View v) {
        if (v.getId() == R.id.disclaimer_policy) {
            disclaimer_policy();

        }
        else if (v.getId() == R.id.eula) {
            eula();
        }
    }
    private void disclaimer_policy() {
        LayoutInflater inflater = LayoutInflater.from(this);

        View view               = inflater.inflate(R.layout.disclaimer_policy, null);

        AlertDialog.Builder builder         = new AlertDialog.Builder(this);

        builder.setView(view).setTitle("Disclaimer:")
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();
    }

    private void eula() {
        LayoutInflater inflater = LayoutInflater.from(this);

        View view               = inflater.inflate(R.layout.eula, null);

        AlertDialog.Builder builder         = new AlertDialog.Builder(this);

        builder.setView(view).setTitle("EULA:")
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();
    }

}