package com.sh.sosoopic.survey

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.sh.sosoopic.R
import kotlinx.android.synthetic.main.item_survey.view.*

class SurveyItem : LinearLayout {
    private var title: String = ""

    constructor(context: Context, title: String, listener: View.OnClickListener) : super(context, null) {
        val view = LayoutInflater.from(context).inflate(R.layout.item_survey, this, true)

        val titleTextView = view.titleTextView
        titleTextView.text = title
        this.title = title

        val deleteBtn = view.deleteBtn
        deleteBtn.setOnClickListener {
            listener.onClick(this)
        }
    }

    public fun getTitle(): String {
        return title
    }
}
