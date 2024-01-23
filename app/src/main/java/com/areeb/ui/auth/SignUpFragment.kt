package com.areeb.ui.auth

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import com.areeb.data.models.UserEntitiy
import com.areeb.data.repository.UserRepository
import com.areeb.ui.base.BaseFragment
import com.areeb.workshopregister.R
import com.areeb.workshopregister.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpFragment : BaseFragment() {


    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private var firstName = ""
    private var lastName = ""
    private var age = 0
    private var email = ""
    private val viewModels by viewModels<AuthViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(layoutInflater)
        return _binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        observer()
    }

    private fun observer() {
        viewModels.errorMessage.observe(viewLifecycleOwner) {
            binding.tvValidation.text = it
        }
        viewModels.currentUser.observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(requireContext(), "get the response", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setViews() {
        mainActivity.activityBinding.toolBar.title = "SignUp"
        mainActivity.activityBinding.bottomNavigation.visibility = View.GONE
        onClickListener()

    }


    private fun onClickListener() {
        binding.btnSignUp.setOnClickListener {
            with(binding) {
                firstName = edFirstName.text.toString()
                lastName = edLastName.text.toString()
                age = if (edAge.text.toString().isNotEmpty()) edAge.text.toString().toInt() else 0
                email = edEmail.text.toString()
                if (shouldUserNavigate()) {
                    val userModel =
                        UserEntitiy(
                            firstName = firstName,
                            lastName = lastName,
                            age = age,
                            email = email
                        )
                    viewModels.storeUser(userModel)
                    btnSignUp.isClickable = false
                    btnSignUp.setBackgroundColor(Color.GRAY)
                    navigate(SignUpFragmentDirections.actionSignUpFragmentToHomeScreen())
                }
            }
        }
    }

    private fun shouldUserNavigate(): Boolean {
        binding.tvValidation.visibility = View.VISIBLE
        if (firstName.isEmpty()) {
            viewModels.setErrorMessage("first Name is empty")
            return false

        } else if (lastName.isEmpty()) {
            viewModels.setErrorMessage("last name is empty")
            return false

        } else if (age == 0) {
            viewModels.setErrorMessage("age is empty")
            return false
        } else if (email.isEmpty()) {
            viewModels.setErrorMessage("email is empty")
            return false
        } else {
            return true

        }

    }


}