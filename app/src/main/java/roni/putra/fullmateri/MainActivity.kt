package roni.putra.fullmateri

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Lifecycle","On Create")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle","On Start")

    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Lifecycle","On Restart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle","On Resume")

    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle","On Pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle","On Stop")
    }
}