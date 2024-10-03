package com.example.cozycupscafe;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PlaylistActivity extends AppCompatActivity {

    private Button playButton1;
    private Button playButton2;
    private Button playButton3;
    private MediaPlayer mediaPlayer;
    private int currentSong = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        playButton1 = findViewById(R.id.playButton1);
        playButton2 = findViewById(R.id.playButton2);
        playButton3 = findViewById(R.id.playButton3);
        Button menuButton = findViewById(R.id.menuButton);

        playButton1.setOnClickListener(v -> toggleSong(R.raw.morning, playButton1));
        playButton2.setOnClickListener(v -> toggleSong(R.raw.afternoon, playButton2));
        playButton3.setOnClickListener(v -> toggleSong(R.raw.relaxing, playButton3));

        menuButton.setOnClickListener(v -> {
            Intent intent = new Intent(PlaylistActivity.this, GridActivity.class);
            startActivity(intent);
        });
    }

    private void toggleSong(int songResId, Button playButton) {
        if (currentSong == songResId && mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                playButton.setText(R.string.play);
                showAllButtons();
            } else {
                mediaPlayer.start();
                playButton.setText(R.string.pause);
                hideOtherButtons(playButton);
            }
        } else {

            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }

            mediaPlayer = MediaPlayer.create(this, songResId);
            mediaPlayer.start();
            currentSong = songResId;
            playButton.setText(R.string.pause);

            hideOtherButtons(playButton);
            resetButtonText(playButton);
        }

        mediaPlayer.setOnCompletionListener(mp -> {
            // Reset current song on completion
            currentSong = -1;
            showAllButtons(); // Show all buttons when a song finishes
            resetButtonText(playButton);
            mediaPlayer.release();
            mediaPlayer = null;
        });
    }

    private void hideOtherButtons(Button currentButton) {
        if (currentButton != playButton1) {
            playButton1.setVisibility(View.GONE);
        }
        if (currentButton != playButton2) {
            playButton2.setVisibility(View.GONE);
        }
        if (currentButton != playButton3) {
            playButton3.setVisibility(View.GONE);
        }
    }

    private void showAllButtons() {
        playButton1.setVisibility(View.VISIBLE);
        playButton2.setVisibility(View.VISIBLE);
        playButton3.setVisibility(View.VISIBLE);
    }

    private void resetButtonText(Button currentButton) {
        if (currentButton != playButton1) {
            playButton1.setText(R.string.play);
        }
        if (currentButton != playButton2) {
            playButton2.setText(R.string.play);
        }
        if (currentButton != playButton3) {
            playButton3.setText(R.string.play);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}