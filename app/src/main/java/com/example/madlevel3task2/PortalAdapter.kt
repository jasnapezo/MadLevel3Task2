package com.example.madlevel3task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_portal.view.title

class PortalAdapter(
    private val portals: List<Portal>,
    private val onClickListener: (Portal) -> Unit
) : RecyclerView.Adapter<PortalAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_portal, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(portals[position], onClickListener)
    }

    override fun getItemCount(): Int {
        return portals.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun databind(portal: Portal, onClickListener: (Portal) -> Unit) {
            itemView.title.text = portal.name
            itemView.title.text = portal.url
            itemView.setOnClickListener { onClickListener(portal) }
        }
    }
}