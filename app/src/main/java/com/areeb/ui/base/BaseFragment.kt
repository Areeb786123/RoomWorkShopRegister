package com.areeb.ui.base

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.areeb.MainActivity

open class BaseFragment : Fragment() {
    val mainActivity: MainActivity by lazy {
        activity as MainActivity
    }


    fun navigate(navDirection: NavDirections) {
        try {
            findNavController().navigate(navDirection)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}