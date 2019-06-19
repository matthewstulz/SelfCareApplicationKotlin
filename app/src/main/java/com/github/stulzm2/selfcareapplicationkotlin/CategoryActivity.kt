package com.github.stulzm2.selfcareapplicationkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class CategoryActivity : AppCompatActivity() {

    private lateinit var categoryWebView : WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        setSupportActionBar(findViewById(R.id.toolbar_category))

        if (intent.hasExtra(EXTRA_CATEGORY_TITLE)) {
            supportActionBar?.title = intent.getStringExtra(EXTRA_CATEGORY_TITLE)
            initWebView(intent.getStringExtra(EXTRA_CATEGORY_STRING))
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun initWebView(resource: String) {
        categoryWebView = findViewById(R.id.web_view_category)
        categoryWebView.loadDataWithBaseURL(null, resource, "text/html", "utf-8", null)
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
