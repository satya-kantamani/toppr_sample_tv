package com.toppr.sampletv

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference

class StoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)
    }

    companion object {
        fun startActivity(weakReference: WeakReference<Context>) {
            val intent = Intent(weakReference.get(), StoryActivity::class.java)
            weakReference.get()?.startActivity(intent)
        }
    }
}