package com.example.loveapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.loveapp.App
import com.example.loveapp.Prefs
import com.example.loveapp.R
import com.example.loveapp.databinding.FragmentHomeBinding
import com.example.loveapp.model.LoveModel
import com.example.loveapp.room.AppDataBase
import com.example.loveapp.viewmodel.LoveViewModel
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private val viewModel: LoveViewModel by viewModels()

    @Inject
    lateinit var prefs:Prefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBoard()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickers()
    }

    private fun initClickers() {
        with(binding) {
            btnCalculate.setOnClickListener {
                val firstName = etBoy.text.toString()
                val secondName = etGirl.text.toString()
                viewModel.getLiveLoveModel(firstName,secondName).observe(viewLifecycleOwner
                ) { loveModel ->
                    Log.e("ololo", "initClickers: $loveModel")
                    App.db.LoveDao().insert(loveModel)
                    val bundle = Bundle()
                    bundle.putSerializable("love", loveModel)
                    findNavController().navigate(R.id.resultFragment, bundle)
                    etBoy.text.clear()
                    etGirl.text.clear()
                }
            }
            btnHistory.setOnClickListener {
                findNavController().navigate(R.id.historyFragment)
            }
        }
    }

    private fun onBoard() {
        if (!prefs.isShown(requireContext())) {
            findNavController().navigate(R.id.boardFragment)
        }
    }
}