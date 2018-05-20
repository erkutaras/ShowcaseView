package com.erkutaras.showcaseview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * Created by erkut.aras on 23.02.2018.
 */

public class ShowcaseActivity extends AppCompatActivity {

    protected static final String EXTRAS_SHOWCASES = "EXTRAS_SHOWCASES";
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
}
