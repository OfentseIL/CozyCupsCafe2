package com.example.cozycupscafe;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GridActivity extends AppCompatActivity {

    private ImageView fullImageView;
    private TextView detailTextView;


    private final int[] imageIds = {
            R.drawable.americano,
            R.drawable.cappucino,
            R.drawable.espresso,
            R.drawable.flat_white,
            R.drawable.irish,
            R.drawable.macchiato,
            R.drawable.latte,
            R.drawable.mocha,
            R.drawable.iced_coffee,
    };


    private final String[] offeringDetails = {
            "Americano: Price R25",
            "Cappuccino: Price R38",
            "Espresso: Price R40",
            "Flat White: Price R42",
            "Irish Coffee: Price R36",
            "Macchiato: Price R80",
            "Latte: Price R55",
            "Mocha: Price R60",
            "Iced Coffee: Price R35",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_grid);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        GridView gridView = findViewById(R.id.gridView);
        fullImageView = findViewById(R.id.fullImageView);
        detailTextView = findViewById(R.id.detailTextView);


        ImageAdapter adapter = new ImageAdapter(this, imageIds, offeringDetails);
        gridView.setAdapter(adapter);


        gridView.setOnItemClickListener((parent, view, position, id) -> {

            fullImageView.setImageResource(imageIds[position]);
            fullImageView.setVisibility(View.VISIBLE);
            detailTextView.setVisibility(View.VISIBLE);
            detailTextView.setText(offeringDetails[position]);


            Toast.makeText(GridActivity.this, offeringDetails[position], Toast.LENGTH_SHORT).show();
        });
    }
}