package com.example.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.st.entertainment.core.api.EntertainmentSDK

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.game_activity).setOnClickListener {
            EntertainmentSDK.startEListActivity(this)
        }

        findViewById<View>(R.id.game_fragment).setOnClickListener {
            startActivity(Intent(this, GameFragmentActivity::class.java))
        }
    }
}