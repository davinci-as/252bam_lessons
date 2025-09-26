package ar.edu.davinci.a252b_am_lessons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        Intent intent = getIntent();
        if(!intent.hasExtra("text")) return;
        String text = intent.getStringExtra("text");
        Log.i("testing-intent", text);
    }
}