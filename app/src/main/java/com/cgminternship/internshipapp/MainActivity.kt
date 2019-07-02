package com.cgminternship.internshipapp

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.cgminternship.internshipapp.MyBroadcastReceiver.Companion.CUSTOM_ACTION
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val receiver = MyBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity", "onCreate called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        btnStartService.setOnClickListener {
            val serviceIntent = createServiceIntent()
            startService(serviceIntent)
        }

        btnStopService.setOnClickListener {
            val serviceIntent = createServiceIntent()
            stopService(serviceIntent)
        }

        btnFireReceiverAction.setOnClickListener {
            val receiverIntent = Intent(this@MainActivity, MyBroadcastReceiver::class.java).apply {
                action = CUSTOM_ACTION
            }

            this@MainActivity.sendBroadcast(receiverIntent)
        }

        btnStartForResult.setOnClickListener {
            this@MainActivity.startActivityForResult(
                Intent(this@MainActivity, SecondActivity::class.java),
                101
            )
        }
    }

    private fun createServiceIntent() = Intent(this@MainActivity, MyService::class.java)

    override fun onResume() {
        super.onResume()

        registerReceiver(receiver, IntentFilter().apply {
            addAction(CUSTOM_ACTION)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("MainActivity", "resultCode = $resultCode, requestCode = $requestCode")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy called")
    }

    override fun onPause() {
        super.onPause()

        unregisterReceiver(receiver)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
