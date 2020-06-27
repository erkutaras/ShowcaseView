package com.erkutaras.showcaseview

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout


/**
 * Created by erkut.aras on 23.02.2018.
 *
 * Edited by Ahmed on 10.06.2020.
 */

class ShowcaseActivity : AppCompatActivity(), OnIndexChangedListener {

    private var currentIndex = 0
    private lateinit var showcaseModels: ArrayList<ShowcaseModel>

    //MainView
    private lateinit var layout: ShowcaseView


    //OnIndexChangedListener
    private lateinit var onIndexChangedListener: OnIndexChangedListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extras = intent.extras ?: throw NullPointerException("Extras can not be null. " +
            "To pass the extras, you need to pass mandatory parameters to ShowcaseManager.")
        showcaseModels = extras.getParcelableArrayList<ShowcaseModel>(EXTRAS_SHOWCASES) as ArrayList<ShowcaseModel>

        if (showcaseModels.isEmpty()) {
            finish()
            return
        }

        //To initialize OnIndexChangedListener
        initOnIndexChangedListener()

        //To initialize the main layout view
        layout = ShowcaseView(this)

        //To add the view
        setContentView(layout)

        //To handle on layout clicked
        onLayoutClicked()

        //To send a call back of the currentIndex
        updateCurrentIndex()
    }

    /**
     * To handle on layout clicked
     * */
    private fun onLayoutClicked() {
        with(layout) {
            setOnCancelClickListener(View.OnClickListener { finishActivity() })
            setOnNextClickListener(View.OnClickListener { showNextLayout() })
            setOnPreviousClickListener(View.OnClickListener { showPreviousLayout() })
            setOnCustomButtonClickListener(View.OnClickListener { showNextLayout() })
        }
    }


    /**
     * To initialize OnIndexChangedListener
     * */
    private fun initOnIndexChangedListener() {
        onIndexChangedListener = this
    }

    /**
     * To update the ui with the next view
     * */
    private fun updateView(isBntNextSelected: Boolean, isBntPreviousSelected: Boolean) {
        val currentShowModel = showcaseModels[currentIndex];
        currentShowModel.isBtnNextSelected = isBntNextSelected
        currentShowModel.isBtnPreviousSelected = isBntPreviousSelected
        layout.updateView(currentShowModel)
    }

    /**
     * To send a call of the index
     * */
    private fun updateCurrentIndex() {
        onIndexChangedListener.onChanged(currentIndex)
    }

    /**
     * To show next layout
     * */
    private fun showNextLayout() {
        currentIndex += 1
        if (isIndexInRange(currentIndex)) {
            updateCurrentIndex()//send the call back
        } else {
            finishActivity()
        }
    }

    /**
     * To show previous layout
     * */
    private fun showPreviousLayout() {
        if (isNotFirstItem(currentIndex)) {
            currentIndex -= 1
            updateCurrentIndex()//send the call back
        } else {
            finishActivity()
        }
    }

    /**
     * @return true if index is in of range showcaseModels list
     * */
    private fun isIndexInRange(index: Int): Boolean {
        return index < showcaseModels.size
    }

    /**
     * @return true if index is not and greater than zero
     * */
    private fun isNotFirstItem(index: Int): Boolean {
        return index != 0 && index > 0
    }

    /**
     * To finish this activity
     * */
    private fun finishActivity() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    /**
     * call back of {@OnIndexChangedListener}
     * */
    override fun onChanged(index: Int) {
        val isBtnPreviousSelected = (index != 0) //At the first item imgPrevious.isSelected = false
        val isBtnNextSelected = (index + 1) < showcaseModels.size//At the last item imgNext.isSelected = false
        updateView(isBtnNextSelected, isBtnPreviousSelected)
    }

    override fun onResume() {
        super.onResume()
        val extras = intent.extras
        if (isSystemUIVisibilityFalse(extras)) {
            val decorView = window.decorView
            var uiOptions = 0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
            }
            decorView.systemUiVisibility = uiOptions
        }
    }

    private fun isSystemUIVisibilityFalse(extras: Bundle?) =
        extras == null || extras.getBoolean(EXTRAS_SYSTEM_UI_VISIBILITY, false).not()

    companion object {
        const val EXTRAS_SHOWCASES = "EXTRAS_SHOWCASES"
        const val EXTRAS_SYSTEM_UI_VISIBILITY = "EXTRAS_SYSTEM_UI_VISIBILITY"
    }
}
