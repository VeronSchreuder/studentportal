package com.example.veronschreuder.studentportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPortalActivity extends AppCompatActivity {

    EditText mAddUrl;
    EditText mAddTitle;
    Button mFinishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_portal);

        mAddTitle = findViewById(R.id.et_title);
        mAddUrl = findViewById(R.id.et_url);
        mFinishButton = findViewById(R.id.button_add_portal);
        init();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();  return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void init() {
        mFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("title",mAddTitle.getText().toString());
                intent.putExtra("url",mAddUrl.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
