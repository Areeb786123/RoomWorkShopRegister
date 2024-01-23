package com.areeb.ui.home

import android.graphics.ColorSpace.Adaptation
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.areeb.MainActivity
import com.areeb.data.models.UserEntitiy
import com.areeb.data.models.WorkShopEntity
import com.areeb.ui.auth.AuthViewModel
import com.areeb.ui.base.BaseFragment
import com.areeb.ui.home.adapter.HomeAdapter
import com.areeb.ui.home.viewModels.HomeViewModels

import com.areeb.workshopregister.R
import com.areeb.workshopregister.databinding.FragmentHomeScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class HomeScreen : BaseFragment() {


    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!
    private val viewModels by viewModels<HomeViewModels>()
    private val authViewModels by viewModels<AuthViewModel>()
    private lateinit var adapter: HomeAdapter
    private var workShopList: List<WorkShopEntity> = emptyList()
    private lateinit var currentUser: UserEntitiy


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeScreenBinding.inflate(layoutInflater)
        return _binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        setViews()


    }

    private fun observer() {
        authViewModels.currentUser.observe(viewLifecycleOwner) {
            Log.e("currUser", it.toString())
            currentUser = it
            mainActivity.activityBinding.userName.text = "hello ${currentUser.firstName} 👋🏻"
        }
        viewModels.workShopList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.swipeRefresh.isRefreshing = false
            }
            workShopList = it
            handleUi()

        }

    }

    private fun setViews() {
        mainActivity.activityBinding.let {
            it.bottomNavigation.visibility = View.VISIBLE
            it.profileImage.visibility = View.VISIBLE
        }
    }

    private fun handleUi() {

        adapter = HomeAdapter(workShopList)


        binding.workShopRecyclerView.adapter = adapter
        binding.swipeRefresh.setOnRefreshListener {
            Toast.makeText(requireContext(), "refreshing....", Toast.LENGTH_SHORT).show()
            lifecycleScope.launch {
                if (workShopList.isEmpty()) {
                    viewModels.addDummyData()
                }
                viewModels.getAllWorkshops()
            }


        }
    }

    companion object {
        private const val TAG = "homeFragment"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainActivity.activityBinding.userName.text = ""
        mainActivity.activityBinding.profileImage.visibility = View.GONE

    }

}