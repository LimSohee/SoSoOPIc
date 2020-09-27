package com.sh.sosoopic.reading

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.sh.sosoopic.R
import com.sh.sosoopic.common.Script
import com.sh.sosoopic.common.ScriptManager
import com.sh.sosoopic.common.ScriptViewModel
import com.sh.sosoopic.writing.WritingActivity


class ReadingActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_INSERT_SCRIPT = 0
        private const val REQUEST_UPDATE_SCRIPT = 1
    }

    private lateinit var scriptViewModel: ScriptViewModel

    private lateinit var readingViewPager: ViewPager2
    private var readingViewPagerAdapter = ReadingViewPagerAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reading)
        setSupportActionBar(findViewById(R.id.reading_toolbar))

        //if (!checkValidation())
        //    return

        readingViewPager = findViewById(R.id.readingViewPager)
        readingViewPager.adapter = readingViewPagerAdapter
        readingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                readingViewPagerAdapter.setCurrentPosition(position)
            }
        })

        scriptViewModel = ViewModelProvider(this).get(ScriptViewModel::class.java)
        scriptViewModel.getAllScript().observe(this, Observer { script ->
            script?.let {
                readingViewPagerAdapter.setScripts(it)
            }
        })
    }

    private fun checkValidation(): Boolean {
        if (ScriptManager.isEmptyScript()) {
            Toast.makeText(
                this,
                "작성된 스크립트가 없습니다.",
                Toast.LENGTH_SHORT
            ).show()
            finish()
            return false
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.reading_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.insert_btn -> {
            val readingIntent = Intent(this, WritingActivity::class.java)
            startActivityForResult(readingIntent, REQUEST_INSERT_SCRIPT)
            true
        }
        R.id.update_btn -> {
            val readingIntent = Intent(this, WritingActivity::class.java)
            readingIntent.putExtra("KEY_ID", readingViewPagerAdapter.getCurrentID())
            readingIntent.putExtra("KEY_SUBJECT", readingViewPagerAdapter.getCurrentSubject())
            readingIntent.putExtra("KEY_TYPE", readingViewPagerAdapter.getCurrentType())
            readingIntent.putExtra("KEY_QUESTION", readingViewPagerAdapter.getCurrentQuestion())
            readingIntent.putExtra("KEY_ANSWER", readingViewPagerAdapter.getCurrentAnswer())
            startActivityForResult(readingIntent, REQUEST_UPDATE_SCRIPT)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_INSERT_SCRIPT && resultCode == Activity.RESULT_OK) {
            data?.let {
                var subject = it.getStringExtra("KEY_SUBJECT") ?: ""
                var type = it.getStringExtra("KEY_TYPE") ?: ""
                var question = it.getStringExtra("KEY_QUESTION") ?: ""
                var answer = it.getStringExtra("KEY_ANSWER") ?: ""
                val newScript = Script(0, subject, type, question, answer)
                scriptViewModel.insert(newScript)

                Toast.makeText(
                    this,
                    "저장되었습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else if (requestCode == REQUEST_UPDATE_SCRIPT && resultCode == Activity.RESULT_OK) {
            data?.let {
                var id = it.getIntExtra("KEY_ID", 0)
                var subject = it.getStringExtra("KEY_SUBJECT") ?: ""
                var type = it.getStringExtra("KEY_TYPE") ?: ""
                var question = it.getStringExtra("KEY_QUESTION") ?: ""
                var answer = it.getStringExtra("KEY_ANSWER") ?: ""
                val newScript = Script(id, subject, type, question, answer)
                scriptViewModel.update(newScript)

                Toast.makeText(
                    this,
                    "수정되었습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}