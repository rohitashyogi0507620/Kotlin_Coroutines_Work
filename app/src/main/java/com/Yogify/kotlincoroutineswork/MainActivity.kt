package com.Yogify.kotlincoroutineswork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var txxview: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txxview = findViewById<TextView>(R.id.txt_counter)
        Log.e("ThredOnWorking", "${Thread.currentThread().name}")


    }

    fun fun_updateCounter(view: View) {
        Log.e("ThredOnWorking", "${Thread.currentThread().name}")
        txxview.text = "${txxview.text.toString().toInt() + 1}"
    }

    fun fun_LogRunningTask(view: View) {
        // Run On New Thread
        // Lecture 1
        thread(start = true) {
            ExecuteLongTask()
            Log.e("ThredOnWorking", "${Thread.currentThread().name}")
        }

        // Lecture 2
        CoroutineScope(Dispatchers.IO).launch {
            Log.e("ThredOnWorking", "${Thread.currentThread().name}")
        }
        GlobalScope.launch(Dispatchers.Main) {
            Log.e("ThredOnWorking", "${Thread.currentThread().name}")

        }
        MainScope().launch(Dispatchers.Default) {
            Log.e("ThredOnWorking", "${Thread.currentThread().name}")

        }

        // Suspending Function Used to Suspend Thread for next Task

    }

    private fun ExecuteLongTask() {
        for (i in 1..1000000000) {

        }
    }

    suspend fun Task() {
        Log.d("SuspendFunction", "Task Start 1")
        yield()
        delay(5000)
        Log.d("SuspendFunction", "Task Ended 1")
    }

    suspend fun Task2() {
        Log.d("SuspendFunction", "Task Start 2")
        yield()
        delay(5000)
        Log.d("SuspendFunction", "Task Ended 2")
    }

    fun fun_CallSuspendFunction(view: View) {
        // Lecture 4
        CoroutineScope(Dispatchers.Main).launch {
            Task()
        }
        CoroutineScope(Dispatchers.Main).launch {
            Task2()
        }
    }

    //

    suspend fun CallAPIFollowers(): Int {
        delay(1000)
        return 1024
    }

    // Normal Launch Function
    suspend fun fun_CallCoroutines() {
        // Job Object to Check Coroutines State
        // We Used Launch where we don't need result for further operation
        var totalfolower = 0
        var job = CoroutineScope(Dispatchers.Main).launch {
            totalfolower = CallAPIFollowers()
        }
        // if you used join then next line will be execute after comletion of coroutines
        job.join()
        Log.d("Folloers", totalfolower.toString())
    }

    suspend fun fun_CallCoroutinesAsynk() {
        // We Used Asynk where we need result for other operations
        var totalfolower = 0
        var defferobject = CoroutineScope(Dispatchers.Main).async {
            CallAPIFollowers()
        }
        defferobject.join()

        Log.d("Folloers", defferobject.await().toString())
    }

    fun fun_CallJobObject(view: View) {
        CoroutineScope(Dispatchers.Main).launch {
            fun_CallCoroutinesAsynk()
        }
    }


}