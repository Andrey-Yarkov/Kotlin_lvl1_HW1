package com.example.homework1app

import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { // called every time the activity is created
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // which content to display

        // Define number of rectangles per row in case of different orientation
        var rectanglesPerRow : Int
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rectanglesPerRow = horizontalLayoutItemsNum
        } else {
            rectanglesPerRow = verticalLayoutItemsNum
        }

        // Recyclerview object for rectangles display in grid layout
        val recyclerView : RecyclerView = findViewById(R.id.rv_rectangles_main)
        val rvAdapter = RvAdapter(rectangles) // adapter for creating view objects from xml layout
        recyclerView.adapter = rvAdapter
        recyclerView.layoutManager = GridLayoutManager(this, rectanglesPerRow)

        // Button for rectangles addition
        val buttonForAddition : Button = findViewById(R.id.button_for_addition)
        buttonForAddition.setOnClickListener {
            val text = (rvAdapter.itemCount + 1).toString()
            var color : Int
            if (rvAdapter.itemCount % 2 == 0) {
                //color = ColorDrawable(Color.parseColor(evenColor))
                color = ContextCompat.getColor(baseContext, evenColor)
            } else {
                //color = ColorDrawable(Color.parseColor(oddColor))
                color = ContextCompat.getColor(baseContext, oddColor)
            }
            val rectangle = Rectangle(text, color)

            rvAdapter.addRectangle(rectangle)
        }

        // Button for rectangles deletion
        val buttonForDeletion : Button = findViewById(R.id.button_for_deletion)
        buttonForDeletion.setOnClickListener {
            rvAdapter.deleteLastRectangle()
        }
    }

    companion object {
        val rectangles = mutableListOf<Rectangle>()
        val oddColor = R.color.light_red
        val evenColor = R.color.light_blue
        const val horizontalLayoutItemsNum = 4
        const val verticalLayoutItemsNum = 3
    }
}