package com.github.stulzm2.selfcareapplicationkotlin

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {

    private lateinit var categoryWebView : WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        setSupportActionBar(toolbar_category)

        if (intent.hasExtra(EXTRA_CATEGORY_TITLE)) {
            supportActionBar?.title = intent.getStringExtra(EXTRA_CATEGORY_TITLE)
            initWebView(intent.getStringExtra(EXTRA_CATEGORY_STRING))
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun initWebView(resource: String) {
        categoryWebView = findViewById(R.id.web_view_category)

        val currMode = resources.configuration.uiMode and
        Configuration.UI_MODE_NIGHT_MASK

        if (currMode == Configuration.UI_MODE_NIGHT_YES) {
            categoryWebView.setBackgroundColor(
                ResourcesCompat.getColor(resources, R.color.colorNightBackground, null))
            val htmlData = "<font color='white'>$resource</font>"
            categoryWebView.loadDataWithBaseURL(null,
                htmlData, "text/html", "utf-8", null)
        } else {
            categoryWebView.loadDataWithBaseURL(
                null,
                resource, "text/html", "utf-8", null)
        }

//        if (ThemeHelper.getDarkMode()) {
//            categoryWebView.setBackgroundColor(
//                ResourcesCompat.getColor(resources, R.color.colorNightBackground, null))
//            val htmlData = "<font color='white'>$resource</font>"
//            categoryWebView.loadDataWithBaseURL(null,
//                htmlData, "text/html", "utf-8", null)
//        } else {
//            categoryWebView.loadDataWithBaseURL(
//                null,
//                resource, "text/html", "utf-8", null)
//        }
    }

    companion object {
        private const val EXTRA_CATEGORY_TITLE = "com.github.stulzm2.selfcareapplicationkotlin.EXTRA_CATEGORY_TITLE"
        private const val EXTRA_CATEGORY_STRING = "com.github.stulzm2.selfcareapplicationkotlin.EXTRA_CATEGORY_STRING"

        fun getCategoryTitle(): String {
            return EXTRA_CATEGORY_TITLE
        }

        fun getCategoryString(): String {
            return EXTRA_CATEGORY_STRING
        }
    }

}
