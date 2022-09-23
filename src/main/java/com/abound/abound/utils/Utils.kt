package com.abound.abound.utils

class Utils {

    companion object {
        fun toWeight(id: Int): String {
            return when (id) {
                0 -> "light"
                2 -> "bold"
                else -> { // Note the block
                    return "normal"
                }
            }
        }

        fun getDebugMode(debugMode: Boolean): String {
            return if (debugMode) "const debugMode  = true" else "const debugMode  = false"
        }

        fun getCustomContent(
            submitButton: String,
            loadingButton: String,
            loadingPrompt: String,
            errorMessage: String
        ): String {
            return """
        const customContent = {
            submitButton: "{submitButton}",
            loadingButton: "{loadingButton}",
            loadingPrompt: "{loadingPrompt}",
            errorMessage: "{errorMessage}",
        };
        """.replace("{submitButton}", submitButton)
                .replace("{loadingButton}", loadingButton)
                .replace("{loadingPrompt}", loadingPrompt)
                .replace("{errorMessage}", errorMessage)
        }

        fun getCustomTheme(
            textTheme: String,
            colorTheme: String,
            shapeTheme: String,
            buttonTheme: String
        ): String {
            return """const customTheme = {
            {textTheme},
            {colorTheme},
            {shapeTheme},
            {buttonTheme},
        };
            """.replace("{textTheme}", textTheme)
                .replace("{colorTheme}", colorTheme)
                .replace("{shapeTheme}", shapeTheme)
                .replace("{buttonTheme}", buttonTheme)
        }

        fun getTextTheme(
            componentLabelSize: String,
            componentSize: String,
            fontFamily: String,
            size: String,
            weight: String
        ): String {
            return """
                text: {
                    componentLabelSize: "{componentLabelSize}",
                    componentSize: "{componentSize}",
                    fontFamily: "{fontFamily}",
                    size: "{size}",
                    weight: "{weight}",
            }
            """.replace("{componentLabelSize}", componentLabelSize)
                .replace("{componentSize}", componentSize)
                .replace("{fontFamily}", fontFamily)
                .replace("{size}", size)
                .replace("{weight}", weight)
        }

        fun getColorTheme(
            background: String,
            componentBackground: String,
            componentBorder: String,
            componentPlaceholderText: String,
            componentText: String,
            danger: String,
            primary: String,
            text: String
        ): String {
            return """ color: {
                    background: "{background}",
                    componentBackground: "{componentBackground}",
                    componentBorder: "{componentBorder}",
                    componentPlaceholderText: "{componentPlaceholderText}",
                    componentText: "{componentText}",
                    danger: "{danger}",
                    primary: "{primary}",
                    text: "{text}",
            }""".replace("{background}", background)
                .replace("{componentBackground}", componentBackground)
                .replace("{componentBorder}", componentBorder)
                .replace("{componentPlaceholderText}", componentPlaceholderText)
                .replace("{componentText}", componentText)
                .replace("{danger}", danger)
                .replace("{primary}", primary)
                .replace("{text}", text)
        }

        fun getShapeTheme(componentBorderWidth: String, componentCornerRadius: String): String {
            return """  shape: {
                componentBorderWidth: "{componentBorderWidth}",
                componentCornerRadius: "{componentCornerRadius}",
            }""".replace("{componentBorderWidth}", componentBorderWidth)
                .replace("{componentCornerRadius}", componentCornerRadius)
        }

        fun getButtonTheme(
            activeBackground: String,
            activeBorder: String,
            activeText: String,
            disabledBackground: String,
            disabledBorder: String,
            disabledText: String,
            loadingBackground: String,
            loadingBorder: String,
            loadingText: String,
            borderWidth: String,
            cornerRadius: String,
            fontFamily: String,
            size: String,
            weight: String,
        ): String {
            return """    button: {
                    color: {
                        activeBackground: "{activeBackground}",
                        activeBorder: "{activeBorder}",
                        activeText: "{activeText}",
                        disabledBackground: "{disabledBackground}",
                        disabledBorder: "{disabledBorder}",
                        disabledText: "{disabledText",
                        loadingBackground: "{loadingBackground}",
                        loadingBorder: "{loadingBorder}",
                        loadingText: "{loadingText}",
                    },
                    shape: {
                        borderWidth: "{borderWidth}",
                        cornerRadius: "{cornerRadius}",
                    },
                    text: {
                        fontFamily: "{fontFamily}",
                        size: "{size}",
                        weight: "{weight}",
                    },
            }""".replace("{activeBackground}", activeBackground)
                .replace("{activeBorder}", activeBorder)
                .replace("{activeText}", activeText)
                .replace("{disabledBackground}", disabledBackground)
                .replace("{disabledBorder}", disabledBorder)
                .replace("{disabledText}", disabledText)
                .replace("{loadingBackground}", loadingBackground)
                .replace("{loadingBorder}", loadingBorder)
                .replace("{loadingText}", loadingText)
                .replace("{borderWidth}", borderWidth)
                .replace("{cornerRadius}", cornerRadius)
                .replace("{fontFamily}", fontFamily)
                .replace("{size}", size)
                .replace("{weight}", weight)
        }
    }
}

fun Int.toHex() =
    java.lang.String.format("#%06X", 0xFFFFFF and this)
