package com.example.game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.st.entertainment.core.api.EntertainmentSDK

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