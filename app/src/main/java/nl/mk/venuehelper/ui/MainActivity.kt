package nl.mk.venuehelper.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import nl.mk.venuehelper.R
import nl.mk.venuehelper.shared.hideKeyboard

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findNavController(R.id.nav_host).addOnDestinationChangedListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        findNavController(R.id.nav_host).removeOnDestinationChangedListener(this)
    }

    override fun onDestinationChanged(p0: NavController, p1: NavDestination, p2: Bundle?) {
        currentFocus?.hideKeyboard()
    }
}
