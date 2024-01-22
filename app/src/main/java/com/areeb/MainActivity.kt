package com.areeb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.areeb.ui.base.BaseActivity
import com.areeb.workshopregister.R
import com.areeb.workshopregister.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private var _activityBinding: ActivityMainBinding? = null
    val activityBinding get() = _activityBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_activityBinding!!.root)
        setViews()
    }

    private fun setViews() {
        settingUpBottomToolBar()
    }

    private fun settingUpBottomToolBar() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        activityBinding.bottomNavigation.setupWithNavController(navController)
    }

}