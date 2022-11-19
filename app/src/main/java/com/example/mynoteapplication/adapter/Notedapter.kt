package com.example.mynoteapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mynoteapplication.databinding.ItemNoteLayoutBinding
import com.example.mynoteapplication.model.Note

class NoteAdapter:RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


private val differCallBack = object :DiffUtil.ItemCallback<Note>(){
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
          return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

}

    val differ = AsyncListDiffer(this,differCallBack)

   inner class NoteViewHolder(val binding: ItemNoteLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(note: Note){
            binding.textView.text = note.Title
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        val view = ItemNoteLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]
        holder.bind(currentNote)

        holder.itemView.setOnClickListener {

            onItemClickListener?.let {
                it(currentNote)
            }
        }
    }

    override fun getItemCount()= differ.currentList.size

    private var onItemClickListener : ((Note) -> Unit)? = null

    fun setOnItemClickListener (listener :(Note) -> Unit){

        onItemClickListener = listener
    }

}