package com.sh.sosoopic.survey

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sh.sosoopic.R
import com.sh.sosoopic.common.ScriptManager
import kotlinx.android.synthetic.main.activity_survey.*

class SurveyActivity : AppCompatActivity() {
    private var subjectList = mutableListOf<String>()
    private var typeList = mutableListOf<String>()

    private val subjectListener = View.OnClickListener { item ->
        if (item is SurveyItem)
            onClickSubjectDeleteBtn(item)
    }

    private val typeListener = View.OnClickListener { item ->
        if (item is SurveyItem)
            onClickTypeDeleteBtn(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        initData()
        initUI()
    }


    private fun initData() {
        subjectList = ScriptManager.getSubjectList(this)
        typeList = ScriptManager.getTypeList(this)
    }

    private fun initUI() {
        for (itemText in subjectList) {
            val itemView = SurveyItem(this, itemText, subjectListener)
            subjectLinearLayout.addView(itemView)
        }

        for (itemText in typeList) {
            val itemView = SurveyItem(this, itemText, typeListener)
            typeLinearLayout.addView(itemView)
        }
    }

    fun onClickSubjectAddBtn(view: View) {
        var newSubject = subjectEditText.text.toString()
        if (!newSubject.isNullOrEmpty()) {
            subjectList.add(newSubject)
            val itemView = SurveyItem(this, newSubject, subjectListener)
            subjectLinearLayout.addView(itemView)
            subjectEditText.text.clear()
        } else {
            Toast.makeText(
                this,
                "주제를 입력해주세요.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun onClickSubjectDeleteBtn(item: SurveyItem) {
        subjectList.remove(item.getTitle())
        subjectLinearLayout.removeView(item)
    }

    fun onClickTypeAddBtn(view: View) {
        var newType = typeEditText.text.toString()
        if (!newType.isNullOrEmpty()) {
            typeList.add(newType)
            val itemView = SurveyItem(this, newType, typeListener)
            typeLinearLayout.addView(itemView)
            typeEditText.text.clear()
        } else {
            Toast.makeText(
                this,
                "유형을 입력해주세요.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun onClickTypeDeleteBtn(item: SurveyItem) {
        typeList.remove(item.getTitle())
        typeLinearLayout.removeView(item)
    }

    fun onClickCancelBtn(view: View) {
        finish()
    }

    fun onClickSaveBtn(view: View) {
        ScriptManager.saveSubjectAndTypeList(this, subjectList, typeList)
        Toast.makeText(
            this,
            "저장되었습니다.",
            Toast.LENGTH_SHORT
        ).show()
        finish()
    }
}