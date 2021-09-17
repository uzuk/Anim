package com.example.fidelitytest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.anim_item_view.view.*

class AnimAdapter( private val animList: List<AnimsResult>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.anim_item_view, parent, false)
        return AnimItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rows = animList
        val item = rows[position]
        (holder as AnimItemViewHolder).bindData(item)
    }

    override fun getItemCount(): Int {
        return animList.size
    }

    internal class AnimItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bindData(animData: AnimsResult) {
            Picasso.get()
                .load(animData.image_url)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_broken)
                .into(itemView.animImage)
            itemView.animName.text = animData.title
            itemView.animDesc.text = animData.synopsis

        }
    }
}