package com.areeb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.areeb.ui.base.BaseActivity
import com.areeb.workshopregister.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private var _activityBinding: ActivityMainBinding? = null
    val activityBinding get() = _activityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_activityBinding!!.root)
        setViews()
    }

    private fun setViews() {
        activityBinding?.toolBar?.title = "Hai User  \uD83D\uDC4B\uD83C\uDFFB "
    }
}