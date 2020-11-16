package com.codecool.applicationa.main_page

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GirdLayoutFitter : GridLayoutManager {

    private var columnWidth = -1
    private var columnWidthChanged = true

    constructor(context : Context) : super(context,2){
        setColumnWidth(columnWidth)
    }

    private fun setColumnWidth(width : Int){
        if ( width > 0 && width != columnWidth){
            columnWidth = width
            columnWidthChanged = true
        }
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        if ( columnWidthChanged && columnWidth > 0){
            var totalSpace = 0
            if ( orientation == LinearLayoutManager.VERTICAL){
                totalSpace = width - paddingRight - paddingLeft
            } else {
                totalSpace = height - paddingTop - paddingBottom
            }

            val spanCount = Math.max(1,totalSpace/columnWidth)
            setSpanCount(spanCount)
            columnWidthChanged = false
        }

        super.onLayoutChildren(recycler,state)
    }
}