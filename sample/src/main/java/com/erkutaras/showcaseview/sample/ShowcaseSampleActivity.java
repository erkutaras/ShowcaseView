package com.erkutaras.showcaseview.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;

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
                .add().build()
                .show();
    };

    // multiple display
    View.OnClickListener listener5 = v -> {
        ShowcaseManager.Builder builder = new ShowcaseManager.Builder();
        builder.context(ShowcaseSampleActivity.this)
                .key("TEST")
                .developerMode(true)
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
