package com.toppr.sampletv

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class GridItemComponent(context: Context) : ConstraintLayout(context) {
    private val tvGrade: TextView

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_grade_item, null)
        tvGrade = view.findViewById(R.id.tv_grade)
    }

    fun setText(text: String) {
        tvGrade.text = text
    }
}