package com.erkutaras.showcaseview.sample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.erkutaras.showcaseview.ShowcaseManager;

/**
 * Created by erkut.aras on 23.02.2018.
 *
 * this is sample activity not used in prod
 */
public class ShowcaseSampleActivity extends AppCompatActivity {

    View v1;
    View v2;
    Switch switchStatusBarVisibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcase_sample);

        switchStatusBarVisibility = findViewById(R.id.switch1);
        initSwitch();
        findViewById(R.id.progressBar2).setOnClickListener(listener);
        findViewById(R.id.progressBar).setOnClickListener(listener1);
        findViewById(R.id.textView).setOnClickListener(listener2);
        findViewById(R.id.checkBox).setOnClickListener(listener3);
        findViewById(R.id.button).setOnClickListener(listener4);
        v1 = findViewById(R.id.checkedTextView);
        v1.setOnClickListener(listener5);
        v2 = findViewById(R.id.radioButton);
        v2.setOnClickListener(listener5);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == ShowcaseManager.REQUEST_CODE_SHOWCASE && resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "All showcases are closed", Toast.LENGTH_SHORT).show();
        }
    }

    // default display
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
                .marginFocusArea(0)
                .add().build()
                .show();
    };

    // different background color and alpha
    View.OnClickListener listener1 = v -> {
        ShowcaseManager.Builder builder = new ShowcaseManager.Builder();
        builder.context(ShowcaseSampleActivity.this)
                .view(v)
                .descriptionImageRes(R.mipmap.ic_launcher)
                .descriptionTitle("LOREM IPSUM DOLOR")
                .descriptionText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .buttonText("DONE")
                .key("TEST")
                .developerMode(true)
                .colorBackground(Color.GREEN)
                .alphaBackground(80)
                .marginFocusArea(10)
                .add().build()
                .show();
    };

    // different background and text color for button
    View.OnClickListener listener2 = v -> {
        ShowcaseManager.Builder builder = new ShowcaseManager.Builder();
        builder.context(ShowcaseSampleActivity.this)
                .view(v)
                .descriptionImageRes(R.mipmap.ic_launcher)
                .descriptionTitle("LOREM IPSUM DOLOR")
                .descriptionText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .buttonText("DONE")
                .key("TEST")
                .developerMode(true)
                .colorButtonBackground(Color.BLUE)
                .colorButtonText(Color.YELLOW)
                .marginFocusArea(20)
                .add().build()
                .show();
    };

    // different text color for title and description
    View.OnClickListener listener3 = v -> {
        ShowcaseManager.Builder builder = new ShowcaseManager.Builder();
        builder.context(ShowcaseSampleActivity.this)
                .view(v)
                .descriptionImageRes(R.mipmap.ic_launcher)
                .descriptionTitle("LOREM IPSUM DOLOR")
                .descriptionText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .buttonText("DONE")
                .key("TEST")
                .developerMode(true)
                .colorDescText(Color.GREEN)
                .colorDescTitle(Color.RED)
                .marginFocusArea(30)
                .add().build()
                .show();
    };

    // different focus area color
    View.OnClickListener listener4 = v -> {
        ShowcaseManager.Builder builder = new ShowcaseManager.Builder();
        builder.context(ShowcaseSampleActivity.this)
                .view(v)
                .descriptionImageRes(R.mipmap.ic_launcher)
                .descriptionTitle("LOREM IPSUM DOLOR")
                .descriptionText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .buttonText("DONE")
                .key("TEST")
                .developerMode(true)
                .colorFocusArea(Color.CYAN)
                .marginFocusArea(40)
                .add().build()
                .show();
    };

    // multiple display
    View.OnClickListener listener5 = v -> {
        ShowcaseManager.Builder builder = new ShowcaseManager.Builder();
        builder.context(ShowcaseSampleActivity.this)
                .key("TEST")
                .developerMode(true)
                .marginFocusArea(10)
                // first view
                .view(v2)
                .descriptionImageRes(R.mipmap.ic_launcher)
                .descriptionTitle("LOREM IPSUM DOLOR-1")
                .descriptionText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .buttonText("Done")
                .add()
                // second view
                .view(v1)
                .descriptionImageRes(R.mipmap.ic_launcher_round)
                .descriptionTitle("LOREM IPSUM DOLOR-2")
                .descriptionText("Consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .buttonText("Cancel")
                .marginFocusArea(20)
                .add()
                .build()
                .show();
    };

    private void setStatusBarVisibility(boolean statusBarVisibility) {
        View decorView = getWindow().getDecorView();
        int uiOptions;
        if (statusBarVisibility) {
            uiOptions = View.SYSTEM_UI_FLAG_VISIBLE;
        } else {
            uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        }
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void initSwitch() {
        switchStatusBarVisibility.setChecked(getWindow().getDecorView().getSystemUiVisibility() == View.VISIBLE);
        switchStatusBarVisibility.setOnCheckedChangeListener((buttonView, isChecked) -> setStatusBarVisibility(isChecked));
    }
}
