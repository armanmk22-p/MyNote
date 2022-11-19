package com.example.mynoteapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.mynoteapplication.databinding.FragmentAddNotBinding
import com.example.mynoteapplication.model.Note
import com.example.mynoteapplication.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class AddNotFragment : Fragment(R.layout.fragment_add_not) {

    private val viewModel :NoteViewModel by viewModels()

    /**
     * This property is only valid between onCreateView and onDestroyView
      */
    private var _binding: FragmentAddNotBinding? = null
    private val binding get() = _binding!!

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNotBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddTask.setOnClickListener {
            saveNote(it)
        }

        binding.btnCancel.setOnClickListener {
            findNavController().navigate(R.id.action_addNotFragment_to_listNoteFragment)
        }
    }

    private fun saveNote(it: View?) {

        val title = binding.etNoteName.text.toString()
        if (title.isNotEmpty()){
            val note = Note(0,title)
            viewModel.insertNote(note)
            findNavController().navigate(R.id.action_addNotFragment_to_listNoteFragment)
        }else{
            val toast = Toast.makeText(activity,
                "Note title can not be empty",
                Toast.LENGTH_SHORT
            )
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}