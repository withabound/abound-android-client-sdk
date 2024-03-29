package com.withabound.abound.utils


interface AboundJavascriptInterface {
    @android.webkit.JavascriptInterface
    fun onSuccessEvent()

    @android.webkit.JavascriptInterface
    fun onErrorEvent(text: String)
}