package com.example.homework1app

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class RvAdapter(
    private val rectangles: MutableList<Rectangle>
) : RecyclerView.Adapter<RvAdapter.RvViewHolder>() {

    // Describes an item view and its metadata inside the recycler view
    class RvViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView : TextView = itemView.findViewById(R.id.view_reactangle)

        private fun showRectangle(rectangle: Rectangle){
            val intent = Intent(itemView.context, LocalActivity::class.java) // abstract description of the operation to be performed
            intent.putExtra(extraTextKey, rectangle.text)
            intent.putExtra(extraColorKey, rectangle.color)
            itemView.context.startActivity(intent) // Start new activity
        }

        // Bind item view with object
        fun bind(rectangle : Rectangle) {
            textView.text = rectangle.text
            textView.setBackgroundColor(rectangle.color)

            // What to do when the item is clicked
            itemView.setOnClickListener {
                showRectangle(rectangle)
            }
        }
    }

    fun addRectangle(rectangle: Rectangle) {
        rectangles.add(rectangle)
        notifyItemInserted(rectangles.size - 1)
    }

    fun deleteLastRectangle() {
        if (rectangles.size > 0) {
            rectangles.removeLast()
            notifyItemRemoved(rectangles.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder { // Called when RV needs a new view holder
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_rectangle, null) // Connect view item with xml file
        return RvViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rectangles.size
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) { // Called by RV to display data on specific position
        holder.bind(rectangles[position])
    }

    companion object {
        const val extraTextKey = "RectText"
        const val extraColorKey = "RectColor"
    }

}