# MainActivity.kt
```kotlin
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
```


### Developed By Thai android developer.


<img src="./picture/profile2_circle.png" width="170">  ![alt text](./picture/thekhaeng_logo.png)


Follow [facebook.com/thekhaeng.io](https://www.facebook.com/thekhaeng.io) on Facebook page.
or [@nonthawit](https://medium.com/@nonthawit) at my Medium blog. :)

For contact, shoot me an email at nonthawit.thekhaeng@gmail.com