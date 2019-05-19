package com.caloriecounter.guitartabs.Adapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.caloriecounter.guitartabs.Activities.MainActivity
import com.caloriecounter.guitartabs.Models.Song
import com.caloriecounter.guitartabs.R
import kotlinx.android.synthetic.main.song_list_item.view.*

class SongAdapter(val items : ArrayList<Song>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int {
                return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.song_list_item, parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val song = items.get(position)
        val display = "${song.getTitle()} - ${song.getArtist()}"

        holder?.tvListItem?.text = display
        holder?.tvListItem.setOnClickListener {

        }
    }

}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val tvListItem: TextView = view.tv_list_item
}
