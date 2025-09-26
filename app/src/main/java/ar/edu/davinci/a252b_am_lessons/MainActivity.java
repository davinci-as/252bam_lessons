package ar.edu.davinci.a252b_am_lessons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        Log.d("testing-app", getString(R.string.testing_log_message));
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView textView = new TextView(this);
        textView.setText(R.string.welcome_message);
        linearLayout.addView(textView);

        Button button = new Button(this);
        button.setText(R.string.button_message);
        linearLayout.addView(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button myButton = (Button) v;
                Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
                startActivity(intent);
                //myButton.setText("lo que quiera");
                //textView.setText("lo que quiera para el textview");
            }
        });

        setContentView(linearLayout);
    }
}