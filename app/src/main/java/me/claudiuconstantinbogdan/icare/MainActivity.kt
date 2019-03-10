package me.claudiuconstantinbogdan.icare

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var alarmManager : AlarmManager;
    var REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "This will be my own action.", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        alarmManager = baseContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        activateAlarm()
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

    fun activateAlarm(){
        var intent = Intent(this,AlarmReceiver::class.java)
        PendingIntent.getBroadcast(this,REQUEST_CODE,intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val broadcast = PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val cal = Calendar.getInstance()
        cal.add(Calendar.SECOND, 5)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast)

    }
}
