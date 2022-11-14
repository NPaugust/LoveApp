package com.example.loveapp.board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.loveapp.Prefs
import com.example.loveapp.R
import com.example.loveapp.databinding.FragmentBoardBinding
import com.example.loveapp.model.Board
import com.example.loveapp.board.BoardAdapter

class BoardFragment : Fragment() {

    private lateinit var binding: FragmentBoardBinding
    private lateinit var adapter: BoardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            FragmentBoardBinding.inflate(LayoutInflater.from(requireContext()), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = BoardAdapter(requireContext(),findNavController())
        binding.viewPager.adapter = adapter
        binding.wormDotsIndicator.attachTo(binding.viewPager)
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner) {
            activity?.finish()
        }
        adapter.addItem(Board(R.raw.board_love1, "Welcome!", "Here you will find out with whom you have chances!"))
        adapter.addItem(Board(R.raw.board_love2, "Find your mommy,daddy", "Pick anyone and compare them with anyone!\n"))
        adapter.addItem(Board(R.raw.board_love3, "Learn, Save, Share, Love", "Everyone with whom you have a good connection will be saved in the data"))
        adapter.addItem(Board(R.raw.board_love4, "", "Chance of love 99.69%"))

        binding.textSkip.setOnClickListener {
            Prefs().saveState(requireContext())
            findNavController().navigateUp()
        }
    }
}