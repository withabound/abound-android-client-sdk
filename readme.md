
# Abound Android Client SDK

The Abound Android Client SDK provides convenient access to embeddable UI components powered by [Abound's API](https://docs.withabound.com/) .

## Requirements

- Android 9+
- Java or Kotlin (This README.md uses kotlin for examples)

## Installation

The preferred way of installing the Abound Android Client SDK is via  [Maven Central]

You may also add from the this repository.

1. Clone this repo
2. Import this repo inside your project ( New -> Import Module -> Go to the module directory and Import)
3. Add the implementation line to the build.gradle for the app (app/build.gradle)

```gradle
implementation project(path: ':abound-android-client-sdk')
```

<br>

### Getting Started

You can start to integrate the Abound Client SDK library into your solution as soon as you [create an account with Abound][developer-dashboard-signup], [obtain your API keys][developer-dashboard-keys], and create an access token.

### Usage

To use the module you need to add the view inside the layout XML file

We can show the [Tax Profile](https://docs.withabound.com/docs/adding-a-tax-profile-drop-in-to-your-app)

```xml
<com.withabound.abound.TaxProfile
        android:id="@+id/taxProfile"
        android:layout_width="409dp"
        android:layout_height="729dp"
        android:layout_marginStart="1dp"
        android:layout_marginTop="1dp"
        android:layout_marginEnd="1dp"
        android:layout_marginBottom="1dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
```

or we can add a [Tax Document](https://docs.withabound.com/docs/adding-a-tax-documents-drop-in-to-your-app)

```xml
<com.withabound.abound.TaxDocument
        android:id="@+id/taxDocument"
        android:layout_width="409dp"
        android:layout_height="729dp"
        android:layout_marginStart="1dp"
        android:layout_marginTop="1dp"
        android:layout_marginEnd="1dp"
        android:layout_marginBottom="1dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
```


The Abound client must be configured with an `accessToken` requested from the [Abound API](https://docs.withabound.com/reference/createaccesstoken).


We can set the accessToken within the App

```kotlin
 val taxProfile = findViewById<TaxProfile>(R.id.taxDocument)
 taxProfile.setAccessToken("accessToken_testeyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcHBfaWQiOiJhcHBJZF90ZXN0NDhlN2VhYTMxNzVhNjYzNTRlMDA2MjY1NDJkMiIsImNyZWF0ZWRfdGltZXN0YW1wIjoxNjU1MDk2NDAwMDAwLCJlbnZpcm9ubWVudCI6Imh0dHBzOi8vc2FuZGJveC1hcGkud2l0aGFib3VuZC5jb20vdjIiLCJleHBpcmF0aW9uX3RpbWVzdGFtcCI6MzI1MDM3MDE2MDAwMDAsInN0YXR1cyI6IkFjdGl2ZSIsInVzZXJfaWQiOiJ1c2VySWRfdGVzdDI0YjA1ZDc2MWZmNThiNTkzMWJkMDc3NzhjNjdiNGU4MThlNCIsImlhdCI6MTY1NTEzMDMxM30.dOUIyxTRV0QDmrFiy-GoyhKc8qru3pymIcPS5cGTaNk")
```


We can set Custom Text Content for Tax Profile , for specific states.
Currently you can set the values for

submitButton (Defaults to 'Submit')
loadingButton (Defaults to 'Loading...')
loadingPrompt (Defaults to 'This should take less than 10 seconds.')
errorMessage (Defaults to 'Invalid')

using the app namespace

```xml
    <com.withabound.abound.TaxProfile
        android:id="@+id/taxDocument"
        android:layout_width="409dp"
        android:layout_height="729dp"
        android:layout_marginStart="1dp"
        android:layout_marginTop="1dp"
        android:layout_marginEnd="1dp"
        android:layout_marginBottom="1dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:submitButton="Submit Button"
        app:loadingButton="Loading Button"
        app:loadingPrompt="Loading Prompt"
        app:errorMessage="Something went wrong"/>
```


### Debug Mode

You can pass a parameter called app:debug inside the xml, that allows to test specific states when passing an EIN/TIN value

| Status     | Description                                                       | Testing Value |
|------------|-------------------------------------------------------------------|---------------|
| unverified | Unverified                                                        | 999999999     |
| mismatch   | TIN and name do not match.                                        | 333333333     |
| error      | This is a system-failure error and should rarely, if ever, occur. | 111111111     |
| lockedOut  | Verification for user locked for 24-hours.                        | 555555555     |


and handle the success and error callbacks inside the code

```kotlin
 val taxProfile = findViewById<TaxProfile>(R.id.taxDocument)
        taxProfile.onSuccess {
            android.util.Log.w("SuccessFullcallback", "onSuccess")
        }
        taxProfile.onError {
            android.util.Log.w("ErrorCallback", it)
        }
```

For TaxDocuments we can pass the year inside the xml with the app:namespace
```xml
 <com.withabound.abound.TaxProfile
app:year="2022"
/>
```

Some properties for the design can be changed used [custom themes](https://docs.withabound.com/docs/custom-theming-for-drop-in-components), that can be passed as optional parameters for the TaxDocument / TaxProfile within the app namespace

```xml
<com.withabound.abound.TaxProfile
app:textSize="16px"
app:colorBackground="@color/black"
app:shapeCornerRadius="16px"
app:colorActiveBackground="@color/black"
/>
```

finally we call
```kotlin
taxProfile.load()
```
to load the Tax Document o Tax Profile

[docs]: https://docs.withabound.com
[developer-dashboard]: https://dashboard.withabound.com
[developer-dashboard-keys]: https://dashboard.withabound.com/keys
[developer-dashboard-signup]: https://dashboard.withabound.com/signup
