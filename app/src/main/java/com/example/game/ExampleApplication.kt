package com.example.game

import android.app.Application
import android.util.Log
import com.san.api.SanAdSdk
import com.st.entertainment.core.api.EntertainmentConfig
import com.st.entertainment.core.api.EntertainmentSDK
import com.st.entertainment.core.api.OriginOptions
import com.st.entertainment.sdk.san.adapter.SanCdnAdAdapter

class ExampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initGame()
    }

    private fun initGame() {
        val config = EntertainmentConfig.Builder()
            .isLocal(BuildConfig.DEBUG)
            .channel("demo_test")
            .cdnAdAbility(SanCdnAdAdapter.Builder(this)
                .verticalNativeId("2318")
                .verticalBannerId("2319")
                .horizontalNativeId("2320")
                .horizontalBannerId("2321")
                .rewardVideoId("2322")
                .autoInitSan(false)
                .build())
            .build()
        EntertainmentSDK.init(this, config)
        Log.v("ExampleApplication", "EntertainmentSDK.init")
    }
}