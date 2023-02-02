# Introduction

Game Sdk is an sdk that integrates a large number of light games,
which can help developers integrate the game capabilities of light
games in a short time to realize traffic monetization.

# UI effects

<img src="https://github.com/InnovativeProducts/Game/blob/master/images/image1.jpg?raw=true" width=200/><img src="https://github.com/InnovativeProducts/Game/blob/master/images/image2.jpg?raw=true" width=200/><img src="https://github.com/InnovativeProducts/Game/blob/master/images/image3.jpg?raw=true" width=200/>

# How to use

## step1. add dependencies

**gradle.properties**

```properties
android.useAndroidX=true
android.enableJetifier=true
```

**build.gradle**

```groovy
repositories {
    google()
    mavenCentral()
}
ext.gameVersion = '2.0.0'
dependencies {
    implementation "bio.gamerplay.game:core:$gameVersion"
    implementation "bio.gamerplay.game:ui:$gameVersion"
    implementation "bio.gamerplay.game:cdn:$gameVersion"
    implementation "bio.gamerplay.game:ad-adapter-san:$gameVersion"
    implementation 'com.myadsget:san-sdk:3.13.0.9'
}
```  

**To support Java 8, add the language feature support:**

```groovy
android {
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = '1.8'
    }
}
```

## step2. configure Manifest

```xml

<manifest>
    <application android:networkSecurityConfig="@xml/network_security_config">
        <meta-data android:name="com.san.APP_KEY" android:value="YOUR_APP_KEY" />
    </application>
</manifest>
```  

**network_security_config.xml:**

```xml
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    ...
    <base-config cleartextTrafficPermitted="true">
        <trust-anchors>
            <certificates src="system" />
        </trust-anchors>
    </base-config>
    ...
</network-security-config>
```  

## step3. init sdk

**In the onCreate method of the application, initialize the sdk**

```kotlin
class ExampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // other code...
        val config = EntertainmentConfig.Builder()
            .isLocal(BuildConfig.DEBUG) // When it is set to true, there will be log output and other forms of "debug" functions inside the sdk. It is recommended to set it to true except for the official release package.
            .channel("channel")  // your channel
            .cdnAdAbility(SanCdnAdAdapter.Builder(this)
                .verticalNativeId("xxx")
                .verticalBannerId("xxx")
                .horizontalNativeId("xxx")
                .horizontalBannerId("xxx")
                .rewardVideoId("xxx")
                .autoInitSan(true)
                .build())
            .build()
        EntertainmentSDK.init(this, config)
        // other code...
    }
}
```

## step4. start the game activity

### use activity

```kotlin
EntertainmentSDK.startEListActivity(context)
```

### use fragment

When you use fragment, you need to set the background color for the container in order to fit the
style of the app. The setting in the demo is white

```kotlin
class GameFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        initGameContainer()
    }

    private fun initGameContainer() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.game_container, EntertainmentSDK.obtainFragment(null), "entertainment")
            .commitNowAllowingStateLoss()
    }
}
```

# R8 / Proguard

The specific rules are already bundled into the aar which can be interpreted by R8 automatically

# About San Sdk

[San](https://github.com/san-sdk/sample/wiki/Integrate-the-SAN-SDK)

# dark mode

If your app needs to adapt to dark mode, then you can use:

```groovy
implementation "bio.gamerplay.game:night-theme-adapt:$gameVersion"
```

and set sdkNightThemeAdaptSystem when sdk init,true means to follow the system, otherwise not

# custom ui

The sdk now supports partial UI customization. Through the principle of resource merging during  
compilation,the host can overwrite resources to achieve the purpose of customizing the UI.   
The relevant key values of the resources are given below:    
**drawable**

| key                        | meaning                                                                                       |
|----------------------------|-----------------------------------------------------------------------------------------------|
| e_big_pic_card_play_button | The background of the play button in the large list card                                      |
| e_error_retry_btn_bg       | Failed retry button background                                                                |
| e_play_button_selector     | The background of the play button in other types of cards except for the large list card type |
| e_no_net_btn_bg            | CONNECT button background in no-network state                                                 |

**drawable-xxhdpi**

| key          | meaning                                    |
|--------------|--------------------------------------------|
| e_score_icon | The play score icon in the large list card |

**values-colors**

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources xmlns:tools="http://schemas.android.com/tools">
    <!--    play button text color-->
    <color name="e_color_play_button">#247FFF</color>
    <!--    Large image card play button text color-->
    <color name="e_color_card_big_pic_play_button">#99FFFFFF</color>
</resources>
```
