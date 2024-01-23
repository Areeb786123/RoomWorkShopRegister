package com.areeb.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.areeb.data.models.UserEntitiy
import com.areeb.data.models.WorkShopEntity
import com.areeb.ui.auth.AuthViewModel
import com.areeb.ui.base.BaseFragment
import com.areeb.ui.common.clickListener.ClickListener
import com.areeb.ui.home.adapter.HomeAdapter
import com.areeb.workshopregister.R
import com.areeb.workshopregister.databinding.FragmentHomeScreenBinding
import com.areeb.workshopregister.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ProfileFragment : BaseFragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val authViewModels by viewModels<AuthViewModel>()
    private var appliedList: List<WorkShopEntity> = emptyList()
    private var adapter: HomeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(layoutInflater)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModels.getUser()
        observer()
    }

    private fun observer() {
        authViewModels.currentUser.observe(viewLifecycleOwner) {
            binding.tvUserName.text = "${it.firstName} ${it.lastName}"
            binding.tvUserAge.text = "${it.age}"
            binding.tvEmail.text = it.email
            if (it.workShopAppliedFor?.isNotEmpty() == true) {
                appliedList = it.workShopAppliedFor
            }

            if (!it.workShopAppliedFor.isNullOrEmpty()) {
                binding.noDataFound.visibility = View.GONE
                adapter = HomeAdapter(it.workShopAppliedFor, it, onClick)
                binding.rvApplied.adapter = adapter
            }
        }
    }


    private val onClick = object : ClickListener<WorkShopEntity> {
        override fun onClick(t: WorkShopEntity) {
            Log.e("click", t.toString())
        }

    }

}