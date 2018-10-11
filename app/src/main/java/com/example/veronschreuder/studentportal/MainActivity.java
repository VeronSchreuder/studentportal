package com.example.veronschreuder.studentportal;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PortalRecyclerViewAdapter.ItemClickListener {
    public static final int NEW_PORTAL = 1;

    RecyclerView mRecyclerView;
    PortalRecyclerViewAdapter mAdapter;
    FloatingActionButton mFloatingActionButton;
    private List<Portal> mPortals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        afterViewsInitialized();
    }

    private void init() {
        mRecyclerView = findViewById(R.id.link_recycler);
        mFloatingActionButton = findViewById(R.id.plus_button);
        mPortals = new LinkedList<Portal>();
    }

    private void afterViewsInitialized() {
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddPortal();
            }
        });

        setUpRecyclerView(mPortals);
    }

    void setUpRecyclerView(List<Portal> portals) {
        // set up the RecyclerView
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new PortalRecyclerViewAdapter(this, portals);
        mAdapter.setClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    void goToAddPortal() {
        Intent intent = new Intent(this, AddPortalActivity.class);
        startActivityForResult(intent, NEW_PORTAL);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("url", mAdapter.getItem(position).getUrl());
        startActivityForResult(intent, NEW_PORTAL);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            assert data != null;
            Bundle bundle = data.getExtras();
            assert bundle != null;
            String title = bundle.getString("title");
            String url = bundle.getString("url");
            mPortals.add(new Portal(title, url));
            setUpRecyclerView(mPortals);
        }
    }
}