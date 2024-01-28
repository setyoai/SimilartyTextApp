package com.setyo.similartytextapp.ui.home

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.setyo.similartytextapp.R
import com.setyo.similartytextapp.data.remote.response.UserData
import com.setyo.similartytextapp.data.remote.response.UserDetails
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabScheduleSeminar.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_jadwalSeminar)
        }
    }

    private fun setupUser() {
        var userRole: String?
        homeViewModel.getDosen().observe(viewLifecycleOwner) { userData ->
            userData?.let { dosen ->
                userRole = dosen.role
                when (userRole) {
                    "mahasiswa" -> {
                        binding.textViewInitial.setTextColor(Color.BLACK)
                        binding.textViewDosen.visibility = View.GONE
                        binding.fabMyStudent.visibility = View.GONE
                        binding.textViewMyStudent.visibility = View.GONE
                        binding.fabListRegissempro.visibility = View.GONE
                        binding.textViewRegisSeminar.visibility = View.GONE
                        binding.fabScheduleSeminar.visibility = View.GONE
                        binding.textViewScheduleSeminar.visibility = View.GONE
                        binding.fabResultSeminar.visibility = View.GONE
                        binding.textViewResultSeminar.visibility = View.GONE
                        homeViewModel.getUser().observe(viewLifecycleOwner) {
                            setUserData(it.id_mhs)
                        }
                        homeViewModel.userResponse.observe(viewLifecycleOwner) {
                            val userData = it.userData
                            getUserData(userData)
                        }

                    } else -> {
                        binding.textViewDosen.setTextColor(Color.BLACK)
                        homeViewModel.getDosen().observe(viewLifecycleOwner) {
                            setUserDosen(it.id_user)
                        }
                        homeViewModel.getDosenResponse.observe(viewLifecycleOwner) {
                            val userData = it.userDetails
                            getUserDosen(userData)
                        }
                    }
                }
            }
        }
    }


    private fun getUserData(userData: UserData) {
        binding.apply {
            Glide.with(requireContext())
                .load(userData.photoMhs)
                .error(R.drawable.outline_account_circle)
                .into(imageViewAvatar)
            textViewInitial.text = userData.namaMhs
        }
    }

    private fun getUserDosen(userDosen: UserDetails) {
        binding.apply {
            textViewDosen.text = userDosen.namaDosen
        }
    }

    private fun setUserData(id: String) {
        homeViewModel.getUserData(id)
    }

    private fun setUserDosen(id: String) {
        homeViewModel.getUserDosen(id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}