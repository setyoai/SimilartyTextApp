package com.setyo.similartytextapp.ui.home

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.setyo.similartytextapp.R
import com.setyo.similartytextapp.data.remote.response.DafskripsiData
import com.setyo.similartytextapp.data.remote.response.UserData
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
        setDafSkripsi()
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

    private fun setDafSkripsi(){
        homeViewModel.getUserResultSkripsi().observe(viewLifecycleOwner) {
            setDafSkripsi(it.id_dafskripsi)
        }
        homeViewModel.resultDafSkripsiResponse.observe(viewLifecycleOwner) {
            val dafskripsiData = it.dafskripsiData
            getResult(dafskripsiData)
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

    private fun getResult(resultData: DafskripsiData) {
        binding.apply {
            textViewResultSkripsi.text = resultData.statusDafskripsi
            when (resultData.statusDafskripsi) {
                "1" -> {
                    textViewResultSkripsi.text = "Berhasil"
                    textViewResultSkripsi.setTextColor(Color.GREEN) // Set the color to green for success
                }
                "2" -> {
                    textViewResultSkripsi.text = "Ditolak"
                    textViewResultSkripsi.setTextColor(Color.RED) // Set the color to red for rejection
                }
                else -> {
                    // Set a default value or handle other cases if needed
                    textViewResultSkripsi.text = getString(R.string.initial_status_waiting)
                    textViewResultSkripsi.setTextColor(Color.BLACK) // Set a default color if needed
                }
            }
            textViewResultKet.text = resultData.keteranganDafskripsi
        }
    }

    private fun setUserData(id: String) {
        homeViewModel.getUserData(id)
    }

    private fun setDafSkripsi(id: String) {
        homeViewModel.getDafSkripsi(id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}