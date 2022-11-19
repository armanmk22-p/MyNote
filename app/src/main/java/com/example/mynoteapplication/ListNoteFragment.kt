package com.example.mynoteapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynoteapplication.adapter.NoteAdapter
import com.example.mynoteapplication.databinding.FragmentListNoteBinding
import com.example.mynoteapplication.model.Note
import com.example.mynoteapplication.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListNoteFragment : Fragment(R.layout.fragment_list_note) {

    private lateinit var adapter: NoteAdapter

    private val viewModel: NoteViewModel by viewModels()

    private var _binding: FragmentListNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listNoteFragment_to_addNotFragment)
        }
    }

    private fun setUpRecyclerView() {
        val recycler = binding.recycler
        adapter = NoteAdapter()
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getAllNotes.observe(requireActivity()) { note ->
            updateUi(note)
            adapter.differ.submitList(note)
        }

        adapter.setOnItemClickListener {
            // Toast.makeText(requireContext(), it.Title, Toast.LENGTH_SHORT).show()
            viewModel.deleteNote(it)

        }
    }

    private fun updateUi(list: List<Note>) {
        if (list.isNotEmpty()) {
            binding.recycler.visibility = View.VISIBLE
            binding.cardView.visibility = View.GONE
        } else {
            binding.recycler.visibility = View.GONE
            binding.cardView.visibility = View.VISIBLE
        }
    }

    /** Fragments outlive their views.
    Make sure you clean up any references to the binding class instance
    in the fragment's onDestroyView() method.*/
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}