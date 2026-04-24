package com.example.pharmaconnect

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaconnect.databinding.ActivityNotificationAdapterBinding


class NotificationAdapter(private val list: List<Notification>) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    class NotificationViewHolder(val binding: ActivityNotificationAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {

        val binding = ActivityNotificationAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return NotificationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {

        val item = list[position]

        holder.binding.tvTitle.text = item.title
        holder.binding.tvMessage.text = item.message
    }

    override fun getItemCount() = list.size
}