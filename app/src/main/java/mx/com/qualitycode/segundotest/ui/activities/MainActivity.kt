package mx.com.qualitycode.segundotest.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import mx.com.qualitycode.segundotest.R
import mx.com.qualitycode.segundotest.ui.fragments.FirtsFragment
import mx.com.qualitycode.segundotest.viewmodel.SampleViewModel

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragmentWith(FirtsFragment())


    }

    fun replaceFragmentWith(fragment: Fragment) {
        supportFragmentManager?.beginTransaction()
            ?.replace(my_nav_host_fragment.id, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }
}