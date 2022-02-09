package com.Yogify.kotlincoroutineswork.ViewModule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.Yogify.kotlincoroutineswork.MainActivity
import com.Yogify.kotlincoroutineswork.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ActivityWithViewModule : AppCompatActivity() {

    lateinit var viewmodule: MainViewModule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_view_module)

        viewmodule = ViewModelProvider(this).get(MainViewModule::class.java)
        lifecycleScope.launch {
            delay(2000)
            startActivity(Intent(this@ActivityWithViewModule, MainActivity::class.java))
            finish()
        }


    }
}