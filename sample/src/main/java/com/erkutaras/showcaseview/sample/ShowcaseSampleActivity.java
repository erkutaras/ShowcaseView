package com.erkutaras.showcaseview.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.erkutaras.showcaseview.ShowcaseManager;

/**
 * Created by erkut.aras on 23.02.2018.
 *
 * this is sample activity not used in prod
 */
public class ShowcaseSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcase_sample);

        findViewById(R.id.button).setOnClickListener(listener);
        findViewById(R.id.progressBar).setOnClickListener(listener);
        findViewById(R.id.textView).setOnClickListener(listener);
        findViewById(R.id.checkBox).setOnClickListener(listener);
        findViewById(R.id.progressBar2).setOnClickListener(listener);
        findViewById(R.id.checkedTextView).setOnClickListener(listener);
        findViewById(R.id.radioButton).setOnClickListener(listener);
    }

    View.OnClickListener listener = v -> {
        ShowcaseManager.Builder builder = new ShowcaseManager.Builder();
        builder.context(ShowcaseSampleActivity.this)
                .view(v)
                .descriptionImageRes(R.mipmap.ic_launcher_round)
                .descriptionTitle("LOREM IPSUM DOLOR")
                .descriptionText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
                .buttonText("DONE")
                .key("TEST")
                .developerMode(true)
                .add().build()
                .show();
    };
}