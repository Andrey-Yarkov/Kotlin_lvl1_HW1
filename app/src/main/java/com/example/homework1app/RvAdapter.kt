package com.example.homework1app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RvAdapter(
    private val rectangles: MutableList<Rectangle>
) : RecyclerView.Adapter<RvAdapter.RvViewHolder>() {

    class RvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView : TextView = itemView.findViewById(R.id.view_reactangle)
        fun bind(rectangle : Rectangle) {
            textView.text = rectangle.text
            textView.background = rectangle.color
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_rectangle, null) // xml to view
        return RvViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rectangles.size
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        holder.bind(rectangles[position])
    }

}