package com.areeb.ui.splash

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.areeb.ui.auth.AuthViewModel
import com.areeb.ui.base.BaseFragment
import com.areeb.workshopregister.R
import com.areeb.workshopregister.databinding.FragmentSignUpBinding
import com.areeb.workshopregister.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private val viewModels by viewModels<AuthViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(layoutInflater)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        observer()
    }

    private fun observer() {
        viewModels.currentUser.observe(viewLifecycleOwner) {
            if (it != null) {
                navigate(SplashFragmentDirections.actionSplashFragmentToHomeScreen())
            } else {
                navigate(SplashFragmentDirections.actionSplashFragmentToSignUpFragment())
            }
        }
    }

    private fun setViews() {
        mainActivity.activityBinding.bottomNavigation.visibility = View.GONE
    }


}