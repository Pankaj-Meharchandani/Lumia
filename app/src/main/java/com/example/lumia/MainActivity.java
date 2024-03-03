package com.example.lumia;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView logoImageView = findViewById(R.id.logoImageView);
        TextView infoTextView = findViewById(R.id.hello_);

        // Fade out the logo while moving it to the top
        ObjectAnimator fadeOutLogo = ObjectAnimator.ofFloat(logoImageView, "alpha", 1.0f, 0.0f);
        ObjectAnimator moveUpLogo = ObjectAnimator.ofFloat(logoImageView, "translationY", 0f, -logoImageView.getHeight());
        fadeOutLogo.setDuration(1000);
        moveUpLogo.setDuration(1000);

        // Delay the appearance of the text view by 2000ms
        infoTextView.postDelayed(() -> {
            // Fade in the text view while moving it from the bottom to center
            ObjectAnimator fadeInText = ObjectAnimator.ofFloat(infoTextView, "alpha", 0.0f, 1.0f);
            ObjectAnimator moveUpText = ObjectAnimator.ofFloat(infoTextView, "translationY", infoTextView.getHeight(), 0f);
            fadeInText.setDuration(1000);
            moveUpText.setDuration(1000);

            // Create an AnimatorSet to play both animations together
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(fadeOutLogo, moveUpLogo, fadeInText, moveUpText);
            animatorSet.start();

            // Delay opening the new activity by 2000ms
            new Handler().postDelayed(() -> {
                // Open the new activity named Home
                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent);
                finish();  // Optional, if you want to finish the current activity
            }, 2000);
        }, 1500);
    }
}
