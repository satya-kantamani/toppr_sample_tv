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
import com.rajat.pdfviewer.PdfViewerActivity
import java.lang.ref.WeakReference

class RecyclerAdapter(val context: Context, private val dataSet: List<Int>) :
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
                PlayerActivity.startActivity(WeakReference(context))
                /*context.startActivity(
                    PdfViewerActivity.launchPdfFromUrl(           //PdfViewerActivity.Companion.launchPdfFromUrl(..   :: incase of JAVA
                        context,
                        "http://www.africau.edu/images/default/sample.pdf",  // PDF URL in String format
                        "sample.pdf",                        // PDF Name/Title in String format
                        "",       // If nothing specific, Put "" it will save to Downloads
                        enableDownload = false                    // This param is true by defualt.
                    )
                )*/
            }
            handled
        }
    }

}