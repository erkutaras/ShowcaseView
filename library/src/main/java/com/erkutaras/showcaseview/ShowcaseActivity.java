package com.erkutaras.showcaseview;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

/**
 * Created by erkut.aras on 23.02.2018.
 */

public class ShowcaseActivity extends AppCompatActivity {

    protected static final String EXTRAS_SHOWCASES = "EXTRAS_SHOWCASES";
    protected static final String EXTRAS_SYSTEM_UI_VISIBILITY = "EXTRAS_SYSTEM_UI_VISIBILITY";
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (ShowcaseUtils.isNull(extras)) {
            throw new NullPointerException("Extras can not be null. To pass the extras, " +
                    "you need to pass mandatory parameters to ShowcaseManager.");
        }
        List<ShowcaseModel> showcaseModels = extras.getParcelableArrayList(EXTRAS_SHOWCASES);
        if (ShowcaseUtils.isNull(showcaseModels)){
            finish();
            return;
        }
        ShowcaseView layout = new ShowcaseView(this);
        layout.setOnClickListener(v -> {
            currentIndex += 1;
            if (currentIndex < showcaseModels.size()) {
                layout.updateView(showcaseModels.get(currentIndex));
            } else {
                finish();
            }
        });
        layout.updateView(showcaseModels.get(currentIndex));
        setContentView(layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!getExtras().getBoolean(EXTRAS_SYSTEM_UI_VISIBILITY, false)) {
            View decorView = getWindow().getDecorView();
            int uiOptions = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                decorView.setSystemUiVisibility(uiOptions);
            }
        }
    }

    private Bundle getExtras() {
        if (ShowcaseUtils.isNonNull(getIntent().getExtras())) {
            return getIntent().getExtras();
        } else {
            return new Bundle();
        }
    }
}
