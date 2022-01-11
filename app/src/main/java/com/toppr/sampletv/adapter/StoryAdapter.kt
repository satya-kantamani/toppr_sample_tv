package com.toppr.sampletv.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.toppr.sampletv.R
import com.toppr.sampletv.data.StoryData

class StoryAdapter(
    val context: Context,
    private val onItemClick: () -> Unit
) :
    RecyclerView.Adapter<StoryAdapter.ViewHolder>() {

    var listOfData: ArrayList<StoryData> = arrayListOf()

    fun setData(list: List<StoryData>) {
        listOfData.clear()
        listOfData.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun setData(data : StoryData){
            itemView?.apply{
                val imageView = findViewById<AppCompatImageView>(R.id.iv_story)
                val blurview = findViewById<View>(R.id.view_shadow)

                imageView.setImageResource(data.image)
                if(data.isSelected){
                    blurview.visibility = View.GONE
                }else{
                    blurview.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_story, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = listOfData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(listOfData[position])
    }

}