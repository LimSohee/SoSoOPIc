package com.sh.sosoopic.writing

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.sh.sosoopic.R
import com.sh.sosoopic.common.Script
import com.sh.sosoopic.common.ScriptManager
import com.sh.sosoopic.common.ScriptViewModel
import kotlinx.android.synthetic.main.activity_writing.*

class WritingActivity : AppCompatActivity() {

    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writing)
        setSupportActionBar(findViewById(R.id.writing_toolbar))

        init()
    }

    private fun init() {
        var intent = getIntent()
        id = intent.getIntExtra("KEY_ID", 0)
        var subject = intent.getStringExtra("KEY_SUBJECT") ?: ""
        var type = intent.getStringExtra("KEY_TYPE") ?: ""
        var question = intent.getStringExtra("KEY_QUESTION") ?: ""
        var answer = intent.getStringExtra("KEY_ANSWER") ?: ""

        subjectEditText.setText(subject)
        typeEditText.setText(type)
        questionEditText.setText(question)
        answerEditText.setText(answer)
    }

    private fun checkValidation(): Boolean {
        if (subjectEditText.text.isNullOrEmpty()) {
            Toast.makeText(
                this,
                "주제을 작성하세요.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        } else if (typeEditText.text.isNullOrEmpty()) {
            Toast.makeText(
                this,
                "유형을 작성하세요.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        } else if (questionEditText.text.isNullOrEmpty()) {
            Toast.makeText(
                this,
                "질문을 작성하세요.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        } else if (answerEditText.text.isNullOrEmpty()) {
            Toast.makeText(
                this,
                "대답을 작성하세요.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.writing_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.save_btn -> {
            if (checkValidation()) {
                var saveIntent = Intent()
                saveIntent.putExtra("KEY_ID", id)
                saveIntent.putExtra("KEY_SUBJECT", subjectEditText.text.toString())
                saveIntent.putExtra("KEY_TYPE", typeEditText.text.toString())
                saveIntent.putExtra("KEY_QUESTION", questionEditText.text.toString())
                saveIntent.putExtra("KEY_ANSWER", answerEditText.text.toString())
                setResult(Activity.RESULT_OK, saveIntent)
                finish()
            }
            true
        }
        R.id.cancel_btn -> {
            setResult(Activity.RESULT_CANCELED)
            finish()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}