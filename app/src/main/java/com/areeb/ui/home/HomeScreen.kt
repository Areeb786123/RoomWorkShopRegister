package com.areeb.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.areeb.data.models.UserEntitiy
import com.areeb.data.models.WorkShopEntity
import com.areeb.ui.auth.AuthViewModel
import com.areeb.ui.base.BaseFragment
import com.areeb.ui.common.clickListener.ClickListener
import com.areeb.ui.home.adapter.HomeAdapter
import com.areeb.ui.home.viewModels.HomeViewModels
import com.areeb.workshopregister.databinding.FragmentHomeScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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

    @SuppressLint("SetTextI18n")
    private fun observer() {
        authViewModels.currentUser.observe(viewLifecycleOwner) {
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
        if (workShopList.isEmpty()) {
            binding.workShopRecyclerView.visibility = View.GONE
            binding.animSwipeDown.visibility = View.VISIBLE
            binding.noDataFound.visibility = View.VISIBLE
        } else {
            binding.workShopRecyclerView.visibility = View.VISIBLE
            binding.animSwipeDown.visibility = View.GONE
            binding.noDataFound.visibility = View.GONE
        }
        adapter = HomeAdapter(workShopList, currentUser, onAdapterClick)
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

    private val onAdapterClick = object : ClickListener<WorkShopEntity> {
        @SuppressLint("NotifyDataSetChanged")
        override fun onClick(t: WorkShopEntity) {

            val model = currentUser.copy(
                workShopAppliedFor = (currentUser.workShopAppliedFor.orEmpty() + t).distinct()
            )

            authViewModels.updateUser(model)

            updateUi()
        }
    }

    companion object {
        private const val TAG = "homeFragment"
    }

    private fun updateUi() {
        authViewModels.getUser()
        viewModels.getAllWorkshops()
        observer()

        binding.workShopRecyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainActivity.activityBinding.userName.text = ""
        mainActivity.activityBinding.profileImage.visibility = View.GONE
    }
}