package com.example.notecloud.navigation.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notecloud.R
import com.example.notecloud.databinding.ItemCloudBinding
import com.example.notecloud.db.entity.ModelCloud
import com.example.notecloud.navigation.listener.ItemListenerCloud
import com.example.notecloud.utils.priorityView

class AdapterCloud(private var item: List<ModelCloud>,
                   private var itemClickListener :ItemListenerCloud,
                   private var onClickDelete : (ModelCloud) -> Unit
                   ):RecyclerView.Adapter<AdapterCloud.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCloudBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, item, itemClickListener, onClickDelete)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = item[position]
        holder.bind(current)
    }

    override fun getItemCount(): Int = item.size

    @SuppressLint("NotifyDataSetChanged")
    fun getData(note : List<ModelCloud>){
        item = note as ArrayList<ModelCloud>
        notifyDataSetChanged()
    }

    class ViewHolder (private val binding : ItemCloudBinding,
                      private var item: List<ModelCloud>,
                      private val itemClickListener: ItemListenerCloud,private var onClickDelete : (ModelCloud) -> Unit ):
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(modelCloud: ModelCloud){
            with(binding) {
                txtTitle.text = modelCloud.cloudTitle
                txtDesc.text = modelCloud.cloudDescription
                dateView.text = modelCloud.dateCloud
                priorityView.setCardBackgroundColor(itemView.context.priorityView(modelCloud.priorityCloud))
                binding.cvNote.setOnClickListener(this@ViewHolder)
                binding.deleteButton.setOnClickListener(this@ViewHolder)

            }
        }

        override fun onClick(view: View?) {
            val cloud = item[bindingAdapterPosition]
            when(view?.id) {
                R.id.cv_Note -> {
                    itemClickListener.onCloudClick(cloud.id)
                }
                R.id.delete_button -> {
                    onClickDelete(cloud)
                }
            }
        }
    }
}