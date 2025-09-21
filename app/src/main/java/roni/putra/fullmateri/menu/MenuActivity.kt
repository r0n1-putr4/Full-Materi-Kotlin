package roni.putra.fullmateri.menu


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import roni.putra.fullmateri.R

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomMenu)

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> {
                    Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.bottom_user -> {
                    Toast.makeText(this, "Home User", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.bottom_shop -> {
                    Toast.makeText(this, "Home Shop", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.top_logout -> {
                Toast.makeText(applicationContext, "click logout", Toast.LENGTH_LONG).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}

