package com.setyo.similartytextapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.setyo.similartytextapp.R
import com.setyo.similartytextapp.data.remote.response.LoginResult
import com.setyo.similartytextapp.data.remote.response.UserData
import com.setyo.similartytextapp.data.remote.response.UserResponse
import com.setyo.similartytextapp.databinding.FragmentHomeBinding
import com.setyo.similartytextapp.ui.ViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

        private val homeViewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupUser()
        return binding.root
    }

    private fun setupUser() {
        homeViewModel.getUser().observe(viewLifecycleOwner) {
            setUserData(it.id_mhs)
        }
        homeViewModel.userResponse.observe(viewLifecycleOwner) {
            val userData = it.userData
            getUserData(userData)
        }
    }

    private fun getUserData(userData: UserData) {
        binding.apply {
            Glide.with(requireContext())
                .load(userData.photoMhs)
                .error(R.drawable.round_account_box_24)
                .into(imageViewAvatar)
            textViewInitial.text = userData.namaMhs
        }
    }

    private fun setUserData(id: String) {
        homeViewModel.getUserData(id)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}