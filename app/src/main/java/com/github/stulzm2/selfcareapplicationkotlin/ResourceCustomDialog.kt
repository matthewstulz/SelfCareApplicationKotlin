package com.github.stulzm2.selfcareapplicationkotlin

import android.app.Dialog
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.webkit.WebView
import android.widget.ImageButton
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment

class ResourceCustomDialog : DialogFragment(), View.OnClickListener {

    private lateinit var resourcesWebView: WebView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_resource, container, false)
        resourcesWebView = view.findViewById(R.id.web_view_resources)
        initWebView(getString(R.string.resources_data))
        val button = view.findViewById<ImageButton>(R.id.fullscreen_dialog_close)
        button.setOnClickListener(this)
        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogTheme)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onClick(v: View?) {
        val id = v?.id
        if (id == R.id.fullscreen_dialog_close) {
            dismiss()
        }
    }

    private fun initWebView(resource: String) {
        resourcesWebView.loadDataWithBaseURL(null, resource, "text/html", "utf-8", null)

        val currMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

        if (currMode == Configuration.UI_MODE_NIGHT_YES) {
            resourcesWebView.setBackgroundColor(
                ResourcesCompat.getColor(resources, R.color.colorNightBackground, null))
            val htmlData = "<font color='white'>$resource</font>"
            resourcesWebView.loadDataWithBaseURL(null,
                htmlData, "text/html", "utf-8", null)
        } else {
            resourcesWebView.loadDataWithBaseURL(
                null,
                resource, "text/html", "utf-8", null)
        }
    }
}