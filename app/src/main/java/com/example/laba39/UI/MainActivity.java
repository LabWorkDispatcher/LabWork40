package com.example.laba39.UI;

import static com.example.laba39.data.Constants.ANIM_CYCLE_LENGTH;
import static com.example.laba39.utils.Utils.brightIt;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.example.laba39.R;
import com.example.laba39.databinding.ActivityMainBinding;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private boolean day = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.common_animation);
        animation.setDuration(ANIM_CYCLE_LENGTH);
        binding.animationView.startAnimation(animation);

        switchBackgroundRoutine(ANIM_CYCLE_LENGTH);
    }

    private void switchBackgroundRoutine(long time) {
        if (day) {
            binding.animationView.setImageResource(R.drawable.sun_icon_foreground);
            binding.skyBox.setImageResource(R.drawable.day_foreground);
            binding.backgroundView.setColorFilter(brightIt(-50));
        } else {
            binding.animationView.setImageResource(R.drawable.moon_icon_foreground);
            binding.skyBox.setImageResource(R.drawable.night_foreground);
            binding.backgroundView.setColorFilter(brightIt(-150));
        }

        Timer event_timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    day = !day;
                    switchBackgroundRoutine(time);
                });
            }
        };
        event_timer.schedule(timerTask, time);
    }
}
