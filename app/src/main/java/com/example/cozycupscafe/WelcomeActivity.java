package com.example.cozycupscafe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    public ImageView logo;
    public TextView textView1;
    public TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);


        //Views initialization
        logo = findViewById(R.id.logo);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(WelcomeActivity.this, MusicActivity.class);
            startActivity(intent);
            finish(); // Close this activity
        }, 2000);

    }
}