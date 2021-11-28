package com.example.requestcontent

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.requestcontent.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onStart() {
        super.onStart()
        requireActivity().contentResolver.apply {
            val uri = Uri.parse("content://com.example.contentprovider/memberbook")
            val cursor = query(uri, null, null, null, null)
            cursor?.apply {
                var info = ""
                while (moveToNext()) {
                    val tmp = "row0:" + getString(0) + " row1:" + getString(1) + " row2:" + getString(2)
                    info += tmp + "\n"
                    Log.d("FirstFragment", tmp)
                }
                close()
                binding.textviewFirst.text = info
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}