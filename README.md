# Introduction

Game Sdk is an sdk that integrates a large number of light games,
which can help developers integrate the game capabilities of light
games in a short time to realize traffic monetization.

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

dependencies {
    implementation 'bio.gamerplay.game:core:2.0.0'
    implementation 'bio.gamerplay.game:ui:2.0.0'
    implementation 'bio.gamerplay.game:cdn:2.0.0'
    implementation 'bio.gamerplay.game:ad-adapter-san:2.0.0'
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