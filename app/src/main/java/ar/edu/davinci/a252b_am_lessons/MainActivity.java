package ar.edu.davinci.a252b_am_lessons;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            if(resultCode == RESULT_OK) {
                Log.i("result", "está llegando algo");
                if(data == null) return;
                if(!data.hasExtra("response")) return;
                String response = data.getStringExtra("response");
                Log.i("result", response);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            db
                .collection("users")
                .whereEqualTo("uid", "bjU4SdKriDMXRMPuOwyhPPPYFjb2")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Log.i("firebase-firestore", "se completó la lectura");
                        if(task.isSuccessful()) {
                            Log.i("firebase-firestore", "exitoso");
                        }
                    }
                });
            //TODO: tomar datos del usuario
        }

        final int SAVE_DATA = 1;
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
                intent.putExtra("text", "lo que quiera");
                //startActivity(intent);
                startActivityForResult(intent, SAVE_DATA);
                //myButton.setText("lo que quiera");
                //textView.setText("lo que quiera para el textview");
            }
        });

        setContentView(linearLayout);
        new ApiRequest().execute(getString(R.string.url_enpoint));
        //new ImageDownloader().execute("https://www.scripps.org/sparkle-assets/variants/hi_res_dogs_cats_rabbits_pets_1200x750-e017741227f7382c3ba7aaeb27b28297_desktop_x++-1200x1200.jpg");
    }
}