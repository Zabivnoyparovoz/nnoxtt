package com.wavechat.messenger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        // Создаём splash экран программно
        RelativeLayout layout = new RelativeLayout(this);
        layout.setBackgroundColor(0xFF0d0d14);

        // Иконка (текстовая)
        RelativeLayout centerBox = new RelativeLayout(this);
        RelativeLayout.LayoutParams centerParams = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        centerParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        centerBox.setLayoutParams(centerParams);

        TextView logo = new TextView(this);
        logo.setText("🌊");
        logo.setTextSize(72);
        logo.setPadding(0, 0, 0, 20);

        TextView title = new TextView(this);
        title.setText("WaveChat");
        title.setTextColor(0xFFf0f0fa);
        title.setTextSize(28);
        title.setTypeface(android.graphics.Typeface.DEFAULT_BOLD);
        title.setPadding(0, 80, 0, 0);

        RelativeLayout.LayoutParams logoParams = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        logoParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        logo.setLayoutParams(logoParams);

        RelativeLayout.LayoutParams titleParams = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        titleParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        titleParams.addRule(RelativeLayout.BELOW, logo.getId());
        title.setLayoutParams(titleParams);

        layout.addView(logo, logoParams);
        layout.addView(title, titleParams);

        setContentView(layout);

        // Переход к MainActivity через 1.5 секунды
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }, 1500);
    }
}
