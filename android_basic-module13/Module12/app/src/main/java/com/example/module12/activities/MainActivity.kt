package com.example.module12.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.module12.R
import com.example.module12.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, MainFragment(), TAG_MAIN_FRAGMENT)
                .commit()
        }
    }

    override fun onBackPressed() {
        val fragmentMain = supportFragmentManager.findFragmentByTag(TAG_MAIN_FRAGMENT)

        if (fragmentMain != null && fragmentMain.childFragmentManager.backStackEntryCount > 0) {
            fragmentMain.childFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }


    companion object {
        const val TAG_MAIN_FRAGMENT = "TAG_MAIN_FRAGMENT"
    }
}