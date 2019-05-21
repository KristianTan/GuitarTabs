package com.caloriecounter.guitartabs.Adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.caloriecounter.guitartabs.Activities.SongSheetActivity
import com.caloriecounter.guitartabs.Models.Song
import com.caloriecounter.guitartabs.R
import kotlinx.android.synthetic.main.song_list_item.view.*

class SongAdapter(val items: ArrayList<Song>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.song_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val song = items.get(position)

        holder.tvArtist.text = song.artist
        holder.tvTitle.text = song.title

        holder.container.setOnClickListener {
            val intent = Intent(context, SongSheetActivity::class.java)
            intent.putExtra("song", song)
            context.startActivity(intent)
        }
    }

}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvTitle: TextView = view.song_title
    val tvArtist: TextView = view.song_artist
    val container: LinearLayout = view.container
}
