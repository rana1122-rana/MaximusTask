package com.offneo.maximustask.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.offneo.maximustask.databinding.ListFasctsBinding
import com.offneo.maximustask.responseModel.ResponseModel

class FactsAdapter(private val list: List<ResponseModel>, val context: Context) :
    RecyclerView.Adapter<FactsAdapter.ViewHolder>() {

    //object of listener
    private lateinit var mListener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    //my listener interface
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    inner class ViewHolder(private val binding: ListFasctsBinding, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: ResponseModel) {
            binding.textFacts.text = item.getFact()
            binding.textLength.text = item.getLength().toString()
        }

        init {
            //retuning click
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListFasctsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item: ResponseModel = list[position]
        holder.bind(item)

    }

    override fun getItemCount(): Int {
        return list.size
    }

}