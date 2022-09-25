package com.abound.abound

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.webkit.WebView
import com.abound.abound.utils.AboundJavascriptInterface
import com.abound.abound.utils.Utils
import com.abound.abound.utils.Utils.Companion.toWeight
import com.abound.abound.utils.toHex
import java.util.*

class TaxProfile @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : WebView(context, attrs, defStyle), AboundJavascriptInterface {
    private val year: String
    private val componentLabelSize: String
    private val componentSize: String
    private val fontFamily: String
    private val size: String
    private val weight: String
    private val componentBorderWidth: String
    private val componentCornerRadius: String
    private val colorBackground: Int
    private val componentBackground: Int
    private val componentBorder: Int
    private val componentPlaceholderText: Int
    private val componentText: Int
    private val danger: Int
    private val primary: Int
    private val text: Int
    private val debug: Boolean
    private val submitButton: String
    private val loadingButton: String
    private val loadingPrompt: String
    private val errorMessage: String
    private val colorActiveBackground: Int
    private val colorActiveBorder: Int
    private val colorActiveText: Int
    private val colorDisabledBackground: Int
    private val colorDisabledBorder: Int
    private val colorDisabledText: Int
    private val colorLoadingBackground: Int
    private val colorLoadingBorder: Int
    private val colorLoadingText: Int
    private val shapeBorderWidth: String
    private val shapeCornerRadius: String
    private val textFontFamily: String
    private val textSize: String
    private val textWeight: String
    private var accessToken: String
    private var onError: ((text: String) -> Unit)? = null
    private var onSuccess: (() -> Unit)? = null

    init {
        setWebContentsDebuggingEnabled(true)
        isFocusable = true
        isFocusableInTouchMode = true
        addJavascriptInterface(this, "AboundJavascriptInterface")

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TaxProfile)
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        year = typedArray.getString(R.styleable.TaxProfile_year) ?: currentYear.toString()
        /* Text Properties */
        componentLabelSize =
            typedArray.getString(R.styleable.TaxProfile_componentLabelSize) ?: "14px"
        componentSize = typedArray.getString(R.styleable.TaxProfile_componentSize) ?: "14px"
        fontFamily = typedArray.getString(R.styleable.TaxProfile_fontFamily) ?: "Arial"
        size = typedArray.getString(R.styleable.TaxProfile_size) ?: "12px"
        weight = toWeight(typedArray.getInt(R.styleable.TaxProfile_weight, 1))
        /* Shape Properties */
        componentBorderWidth =
            typedArray.getString(R.styleable.TaxProfile_componentBorderWidth) ?: "1px"
        componentCornerRadius =
            typedArray.getString(R.styleable.TaxProfile_componentCornerRadius) ?: "8px"
        /* Color Properties */
        colorBackground = typedArray.getColor(
            R.styleable.TaxProfile_colorBackground,
            Color.parseColor("#FFFFFF")
        )
        componentBackground = typedArray.getColor(
            R.styleable.TaxProfile_componentBackground,
            Color.parseColor("#FFFFFF")
        )
        componentBorder = typedArray.getColor(
            R.styleable.TaxProfile_componentBorder,
            Color.parseColor("#C5C5C5")
        )
        componentPlaceholderText =
            typedArray.getColor(
                R.styleable.TaxProfile_componentPlaceholderText,
                Color.parseColor("#C5C5C5")
            )
        componentText =
            typedArray.getColor(R.styleable.TaxProfile_componentText, Color.parseColor("#000000"))
        danger = typedArray.getColor(R.styleable.TaxProfile_danger, Color.parseColor("#BC0101"))
        primary = typedArray.getColor(R.styleable.TaxProfile_primary, Color.parseColor("#655BEF"))
        text = typedArray.getColor(R.styleable.TaxProfile_text, Color.parseColor("#000000"))
        /*  Debug Mode */
        debug = typedArray.getBoolean(R.styleable.TaxProfile_debug, false)
        /* Custom texts */
        submitButton = typedArray.getString(R.styleable.TaxProfile_submitButton) ?: "Submit Button"
        loadingButton =
            typedArray.getString(R.styleable.TaxProfile_loadingButton) ?: "Loading Button"
        loadingPrompt =
            typedArray.getString(R.styleable.TaxProfile_loadingPrompt) ?: "Loading Prompt..."
        errorMessage =
            typedArray.getString(R.styleable.TaxProfile_errorMessage) ?: "Something went wrong"
        /* Button Styles */
        colorActiveBackground = typedArray.getColor(
            R.styleable.TaxProfile_colorActiveBackground,
            Color.parseColor("#000000")
        )
        colorActiveBorder = typedArray.getColor(
            R.styleable.TaxProfile_colorActiveBorder,
            Color.parseColor("#000000")
        )
        colorActiveText = typedArray.getColor(
            R.styleable.TaxProfile_colorActiveText,
            Color.parseColor("#FFFFFF")
        )
        colorDisabledBackground = typedArray.getColor(
            R.styleable.TaxProfile_colorDisabledBackground,
            Color.parseColor("#C5C5C5")
        )
        colorDisabledBorder = typedArray.getColor(
            R.styleable.TaxProfile_colorDisabledBorder,
            Color.parseColor("#C5C5C5")
        )
        colorDisabledText = typedArray.getColor(
            R.styleable.TaxProfile_colorDisabledText,
            Color.parseColor("#FFFFFF")
        )
        colorLoadingBackground = typedArray.getColor(
            R.styleable.TaxProfile_colorLoadingBackground,
            Color.parseColor("#C5C5C5")
        )
        colorLoadingBorder = typedArray.getColor(
            R.styleable.TaxProfile_colorLoadingBorder,
            Color.parseColor("#C5C5C5")
        )
        colorLoadingText = typedArray.getColor(
            R.styleable.TaxProfile_colorLoadingText,
            Color.parseColor("#FFFFFF")
        )

        shapeBorderWidth =
            typedArray.getString(R.styleable.TaxProfile_shapeBorderWidth) ?: "1px"
        shapeCornerRadius =
            typedArray.getString(R.styleable.TaxProfile_shapeCornerRadius) ?: "8px"
        textFontFamily =
            typedArray.getString(R.styleable.TaxProfile_textFontFamily) ?: "Arial"
        textSize =
            typedArray.getString(R.styleable.TaxProfile_textSize) ?: "16px"
        textWeight = toWeight(typedArray.getInt(R.styleable.TaxProfile_textWeight, 1))
        accessToken =
            typedArray.getString(R.styleable.TaxProfile_accessToken)
                ?: "accessToken_testeyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcHBfaWQiOiJhcHBJZF90ZXN0NDhlN2VhYTMxNzVhNjYzNTRlMDA2MjY1NDJkMiIsImNyZWF0ZWRfdGltZXN0YW1wIjoxNjU1MDk2NDAwMDAwLCJlbnZpcm9ubWVudCI6Imh0dHBzOi8vc2FuZGJveC1hcGkud2l0aGFib3VuZC5jb20vdjIiLCJleHBpcmF0aW9uX3RpbWVzdGFtcCI6MzI1MDM3MDE2MDAwMDAsInN0YXR1cyI6IkFjdGl2ZSIsInVzZXJfaWQiOiJ1c2VySWRfdGVzdDI0YjA1ZDc2MWZmNThiNTkzMWJkMDc3NzhjNjdiNGU4MThlNCIsImlhdCI6MTY1NTEzMDMxM30.dOUIyxTRV0QDmrFiy-GoyhKc8qru3pymIcPS5cGTaNk"
        typedArray.recycle()
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun load() {
        settings.javaScriptEnabled = true
        loadDataWithBaseURL(
            "https://js.withabound.com",
            loadAndParseHtml(),
            "text/html",
            "UTF-8",
            "https://js.withabound.com"
        )
    }

    private fun loadAndParseHtml(): String {
        val textTheme =
            Utils.getTextTheme(componentLabelSize, componentSize, fontFamily, size, weight)
        val colorTheme = Utils.getColorTheme(
            colorBackground.toHex(),
            componentBackground.toHex(),
            componentBorder.toHex(),
            componentPlaceholderText.toHex(),
            componentText.toHex(),
            danger.toHex(),
            primary.toHex(),
            text.toHex(),
        )
        val shapeTheme = Utils.getShapeTheme(componentBorderWidth, componentCornerRadius)

        val buttonTheme = Utils.getButtonTheme(
            colorActiveBackground.toHex(),
            colorActiveBorder.toHex(),
            colorActiveText.toHex(),
            colorDisabledBackground.toHex(),
            colorDisabledBorder.toHex(),
            colorDisabledText.toHex(),
            colorLoadingBackground.toHex(),
            colorLoadingBorder.toHex(),
            colorLoadingText.toHex(),
            shapeBorderWidth,
            shapeCornerRadius,
            textFontFamily,
            textSize,
            textWeight,
        )
        return resources.assets.open("tax_profile.html").bufferedReader().use { it.readText() }
            .replace("{debugMode}", Utils.getDebugMode(debug))
            .replace("{accessToken}", accessToken)
            .replace(
                "{customContent}", Utils.getCustomContent(
                    submitButton, loadingButton, loadingPrompt,
                    errorMessage
                )
            )
            .replace(
                "{theme}",
                Utils.getCustomTheme(textTheme, colorTheme, shapeTheme, buttonTheme)
            )
            .replace("{year}", year)
    }

    @android.webkit.JavascriptInterface
    override fun onSuccessEvent() {
        onSuccess?.invoke()
    }

    @android.webkit.JavascriptInterface
    override fun onErrorEvent(text: String) {
        onError?.invoke(text)
    }

    fun onError(onError: (input: String) -> Unit) {
        this.onError = onError
    }

    fun onSuccess(onSuccess: () -> Unit) {
        this.onSuccess = onSuccess
    }

    fun setAccessToken(accessToken: String) {
        this.accessToken = accessToken
    }
}

