package com.setyo.similartytextapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.setyo.similartytextapp.data.remote.response.LoginResult
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

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.recyclerViewArticle.adapter = ArticleListAdapter(ArticleData.article)
//        showRecyclerView()
//    }

    private fun setupUser() {
//        homeViewModel.getUser().observe(viewLifecycleOwner) {
//            setProfileData(it.token)
//        }
        homeViewModel.userResponse.observe(viewLifecycleOwner) {
            val userData = it.loginResult
            getUserData(userData)
        }
    }

    private fun getUserData(userData: LoginResult) {
        binding.apply {
//            Glide.with(requireContext())
//                .load(userData.avatarUrl)
//                .error(R.drawable.round_account_box_24)
//                .into(imageViewAvatar)
            textViewInitial.text = userData.namaMhs
        }
    }

//    private fun setProfileData(token: String) {
//        homeViewModel.getUserData(token)
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}