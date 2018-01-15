package com.example.thekhaeng.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity
    : AppCompatActivity() {

    companion object {
        val WEBVIEW_JS = "WebViewJS"
    }


    private val webViewClient: WebViewClient = object : WebViewClient() {
        override
        fun onPageFinished(view: WebView, url: String?) {
            super.onPageFinished(view, url)
            webview.loadUrl("javascript:$WEBVIEW_JS.onHeight(document.body.getBoundingClientRect().height)")
            changeHintInput()
        }
    }


    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupWebView(webview)

        webview.loadUrl("https://www.google.co.th/")
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView(webView: WebView) {
        webView.webViewClient = webViewClient
        webview.addJavascriptInterface(this, WEBVIEW_JS)
        webView.settings.apply {
            javaScriptEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
            layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
            builtInZoomControls = true
            displayZoomControls = false
            javaScriptCanOpenWindowsAutomatically = true
            cacheMode = WebSettings.LOAD_NO_CACHE
            setSupportZoom(true)
            setAppCacheEnabled(false)
        }
    }

    /**
     * NOTE: add proguard
    -keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
    }
     **/
    @JavascriptInterface
    fun onHeight(height: Float) {
        runOnUiThread {
            printHeightOnWebView(height)
        }
    }

    private fun changeHintInput() {
        webview.loadUrl("javascript:" +
                                "var input = document.getElementById('lst-ib');" +
                                "var placeholder = document.createAttribute('placeholder');" +
                                "placeholder.value = 'The Khaeng placeholder';" +
                                "input.setAttributeNode(placeholder);")
    }

    private fun printHeightOnWebView(height: Float) {
        webview.loadUrl("javascript:" +
                                "var div = document.createElement('div');" +
                                "div.id = 'size-height';" +
                                "div.className = 'block';" +
                                "div.innerHTML='&nbsp;&nbsp;height = $height';" +
                                "document.getElementById('main').appendChild(div);")
    }

}
