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

val rectangles = mutableListOf<Rectangle>()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var rectanglesPerRow : Int
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rectanglesPerRow = horizontalLayoutItemsNum
        } else {
            rectanglesPerRow = verticalLayoutItemsNum
        }

        val recyclerView : RecyclerView = findViewById(R.id.rv_rectangles_main) // main view
        val rvAdapter = RvAdapter(rectangles) // for creating rectangles
        recyclerView.adapter = rvAdapter
        recyclerView.layoutManager = GridLayoutManager(this, rectanglesPerRow) // items layout

        val buttonForAddition : Button = findViewById(R.id.button_for_addition) // button for addition
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

        val buttonForDeletion : Button = findViewById(R.id.button_for_deletion) // button fo deletion
        buttonForDeletion.setOnClickListener {
            rvAdapter.deleteLastRectangle()
        }
    }

    companion object {
        val oddColor = R.color.light_red
        val evenColor = R.color.light_blue
        const val horizontalLayoutItemsNum = 4
        const val verticalLayoutItemsNum = 3
    }
}