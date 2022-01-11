package com.toppr.sampletv

import android.content.Context
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

class RecyclerAdapter(val context: Context, private val dataSet: List<Int>,
private val onItemClick : () -> Unit) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView
        val parent: ConstraintLayout

        init {
            // Define click listener for the ViewHolder's View.
            imageView = view.findViewById(R.id.image)
            parent = view.findViewById(R.id.parent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_image_view, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageDrawable(ContextCompat.getDrawable(context, dataSet[position]))
        holder.parent.setOnKeyListener { view, index, keyEvent ->
            val handled = false
            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyEvent.keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
                Toast.makeText(context, "You selected position $position", Toast.LENGTH_SHORT).show()
                onItemClick()
            }
            handled
        }
    }

}