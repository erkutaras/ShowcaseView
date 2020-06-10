package com.erkutaras.showcaseview.sample

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Switch
import android.widget.Toast

import com.erkutaras.showcaseview.ShowcaseManager
import com.erkutaras.showcaseview.ShowcaseUtils

/**
 * Created by erkut.aras on 23.02.2018.
 *
 * this is sample activity not used in prod
 */
class ShowcaseSampleActivity : AppCompatActivity() {

    lateinit var v1: View
    lateinit var v2: View
    lateinit var switchStatusBarVisibility: Switch

    // default display
    private var listener = { v: View ->
        val builder = ShowcaseManager.Builder()
        builder.context(this@ShowcaseSampleActivity)
            .view(v)
            .descriptionImageRes(R.mipmap.ic_launcher_round)
            .descriptionTitle("LOREM IPSUM DOLOR")
            .descriptionText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
            .buttonText("DONE")
            .key("TEST")
            .developerMode(true)
            .marginFocusArea(0)
            .gradientFocusEnabled(true)
            .add().build()
            .show()
    }

    // different background color and alpha
    private var listener1 = {  v: View ->
        val builder = ShowcaseManager.Builder()
        builder.context(this@ShowcaseSampleActivity)
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
            .show()
    }

    // different background and text color for button
    private var listener2 = {  v: View ->
        val builder = ShowcaseManager.Builder()
        builder.context(this@ShowcaseSampleActivity)
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
            .circle()
            .add().build()
            .show()
    }

    // different text color for title and description
    private var listener3 = {  v: View ->
        val builder = ShowcaseManager.Builder()
        builder.context(this@ShowcaseSampleActivity)
            .view(v)
            .descriptionImageRes(R.mipmap.ic_launcher)
            .descriptionTitle("LOREM IPSUM DOLOR")
            .descriptionText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
            .buttonText("DONE")
            .key("TEST")
            .developerMode(true)
            .colorDescText(Color.GREEN)
            .colorDescTitle(Color.RED)
            .marginFocusArea(10)
            .roundedRectangle()
            .add().build()
            .show()
    }

    // different focus area color
    private var listener4 = {  v: View ->
        val builder = ShowcaseManager.Builder()
        builder.context(this@ShowcaseSampleActivity)
            .view(v)
            .descriptionImageRes(R.mipmap.ic_launcher)
            .descriptionTitle("LOREM IPSUM DOLOR")
            .descriptionText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
            .buttonText("DONE")
            .key("TEST")
            .developerMode(true)
            .colorFocusArea(Color.CYAN)
            .marginFocusArea(10)
            .rectangle()
            .gradientFocusEnabled(true)
            .add().build()
            .show()
    }

    // multiple display
    private var listener5 = {  v: View ->
        val builder = ShowcaseManager.Builder()
        builder.context(this@ShowcaseSampleActivity)
            .key("TEST")
            .developerMode(true)
            .marginFocusArea(10)
            // first view
            .view(v2)
            .descriptionImageRes(R.mipmap.ic_launcher)
            .descriptionTitle("LOREM IPSUM DOLOR-1")
            .descriptionText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
            .buttonText("Done")
            .buttonVisibility(false)//To hide button
            .add()
            // second view
            .view(v1)
            .descriptionImageRes(R.mipmap.ic_launcher_round)
            .descriptionTitle("LOREM IPSUM DOLOR-2")
            .descriptionText("Consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
            .buttonText("Cancel")
            .marginFocusArea(20)
            .buttonVisibility(true)//To show button
            .add()
            .build()
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showcase_sample)

        switchStatusBarVisibility = findViewById(R.id.switch1)
        initSwitch()
        findViewById<View>(R.id.progressBar2).setOnClickListener(listener)
        findViewById<View>(R.id.progressBar).setOnClickListener(listener1)
        findViewById<View>(R.id.textView).setOnClickListener(listener2)
        findViewById<View>(R.id.checkBox).setOnClickListener(listener3)
        findViewById<View>(R.id.button).setOnClickListener(listener4)
        v1 = findViewById(R.id.checkedTextView)
        v1.setOnClickListener(listener5)
        v2 = findViewById(R.id.radioButton)
        v2.setOnClickListener(listener5)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ShowcaseManager.REQUEST_CODE_SHOWCASE && resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "All showcases are closed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setStatusBarVisibility(statusBarVisibility: Boolean) {
        val decorView = window.decorView
        val uiOptions: Int = if (statusBarVisibility) {
            View.SYSTEM_UI_FLAG_VISIBLE
        } else {
            View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        decorView.systemUiVisibility = uiOptions
    }

    private fun initSwitch() {
        switchStatusBarVisibility.isChecked = window.decorView.systemUiVisibility == View.VISIBLE
        switchStatusBarVisibility.setOnCheckedChangeListener { buttonView, isChecked -> setStatusBarVisibility(isChecked) }
    }
}
