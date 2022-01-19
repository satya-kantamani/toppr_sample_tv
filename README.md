# Sample TV App POC
This project is a simple POC app that was created to understand how TV apps are developed and the details that need to be taken care of while building a TV app.

I am listing all my findings/learnings while building this simple TV App so that this can be used to as a starting point for anyone in the future who wants to build TV apps.

## Build TV apps
We can use the same structure as those for phones and tablets for a TV app. So that means our existing apps can be enhanced to run on a TV or alternatively we can 
create new apps based on what we already know.

### Setting up a TV project
An application intended to run on TV devices must declare a launcher activity for TV in its manifest. It uses a `CATEGORY_LEANBACK_LAUNCHER` intent filter to do this. 
This filter identifies your app as being enabled for TV, and lets Google Play identify it as a TV app. When a user selects your app on their TV home screen, 
this intent identifies which activity to launch.

The following code snippet shows how to include this intent filter in your manifest:

```
<application
  android:banner="@drawable/banner" >
  ...
  <activity
    android:name="com.example.android.MainActivity"
    android:label="@string/app_name" >

    <intent-filter>
      <action android:name="android.intent.action.MAIN" />
      <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
  </activity>

  <activity
    android:name="com.example.android.TvActivity"
    android:label="@string/app_name"
    android:theme="@style/Theme.Leanback">

    <intent-filter>
      <action android:name="android.intent.action.MAIN" />
      <category android:name="android.intent.category.LEANBACK_LAUNCHER" />
    </intent-filter>

  </activity>
</application>
```
Note : The same Activity can also be used as Launcher activity for both app and TV if needed.

### Declare Leanback support
Declare that your app uses the Leanback user interface required by Android TV. If you are developing an app that runs on mobile (phones, wearables, tablets, etc.) 
as well as Android TV, set the `required` attribute value to `false`. If you set the `required` attribute value to `true`, your app will run only on devices that use the 
Leanback UI.

```
<manifest>
    <uses-feature android:name="android.software.leanback"
        android:required="false" />
    ...
</manifest>
```

### Declare touchscreen not required
Applications that are intended to run on TV devices do not rely on touch screens for input. To make this clear, your TV app's manifest must declare that the 
`android.hardware.touchscreen` feature is not required. This setting identifies your app as being able to work on a TV device, and is required for your app 
to be considered a TV app in Google Play. The following code example shows how to include this manifest declaration:

```
<manifest>
    <uses-feature android:name="android.hardware.touchscreen"
              android:required="false" />
    ...
</manifest>
```
Note : This does not effect your mobile/tablet support if any

### Provide a home screen banner
If an application includes a Leanback launcher intent filter, it must provide a home screen banner image for each localization. The banner is the app launch point 
that appears on the home screen in the apps and games rows. To add the banner to your app, describe the banner in the manifest as follows:

```
<application
    ...
    android:banner="@drawable/banner" >

    ...
</application>
```

## Manage TV controllers 
TV devices require a secondary hardware device for interacting with apps, in the form of a basic remote controller or game controller. So your app must
support D-pad key inputs. Your app may need to handle controllers going offline and input from more than one type of controller.

### TV UI events
Buttons that generate these KeyEvents should be handled by the app according to table below.

| KeyEvent    | Behavior    |
| ----------- | ----------- |
| BUTTON_B, BACK	      | Back       |
| BUTTON_SELECT, BUTTON_A, ENTER, DPAD_CENTER, KEYCODE_NUMPAD_ENTER   | Selection        |
| DPAD_UP, DPAD_DOWN, DPAD_LEFT, DPAD_RIGHT	      | Navigation       |

The following code example shows how to include this manifest declaration:
```
override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (event?.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
            ...
        } else if (event?.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.BUTTON_B) {
            ...
        } e
      
        return false
    }
```

## Build TV layouts 
Android Themes can provide a basis for layouts in your TV apps. You should use a theme to modify the display of your app activities that are meant to 
run on a TV device. The following are some recommened themes that can be used:

### Leanback theme
The Leanback androidx library includes Theme.Leanback, a theme for TV activities that provides a consistent visual style. The following code sample shows how to apply this theme to an activity:
```
<activity
  android:name="com.example.android.TvActivity"
  android:label="@string/app_name"
  android:theme="@style/Theme.Leanback">
```

### NoTitleBar theme
The title bar is a standard user interface element for Android apps on phones and tablets, but it is not appropriate for TV apps. If you are not using androidx leanback classes, you should apply 
this theme to your TV activities to suppress the display of a title bar. The following code example from a TV app manifest shows how to apply this theme to remove the display of a title bar:
``` 
<application>
  ...

  <activity
    android:name="com.example.android.TvActivity"
    android:label="@string/app_name"
    android:theme="@android:style/Theme.NoTitleBar">
    ...

  </activity>
</application>
```

### AppCompat themes
```
<style name="MyOnboarding" parent="Theme.AppCompat.Leanback.Onboarding">
    <item name="onboardingLogoStyle">@style/MyOnboardingLogoStyle</item>
    <item name="onboardingPageIndicatorStyle">@style/MyOnboardingPageIndicatorStyle</item>
</style>
```

### Build useable text and controls
The text and controls in a TV app layout should be easily visible and navigable from a distance. Follow these tips to make your user interface elements easier to see from a distance:
- Break text into small chunks that users can quickly scan.
- Use light text on a dark background. This style is easier to read on a TV.
- Avoid lightweight fonts or fonts that have both very narrow and very broad strokes. Use simple sans-serif fonts and anti-aliasing to increase readability.
- Use Android's standard font sizes:
- Ensure that all your view widgets are large enough to be clearly visible to someone sitting 10 feet away from the screen

For complete know-hows on building TV apps please follow the below links for better understanding:
- https://developer.android.com/training/tv
- https://medium.com/@Marcus_fNk/building-an-android-tv-app-part-1-7f59b3747446
