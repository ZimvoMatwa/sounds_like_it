package com.example.digitinnoise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.digitinnoise.databinding.FragmentTestScreenBinding
import com.example.digitinnoise.hearingTest.HearingTestViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestScreen : Fragment() {
    private lateinit var binding: FragmentTestScreenBinding
    val viewModel: HearingTestViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test_screen, container, false)
    }
}