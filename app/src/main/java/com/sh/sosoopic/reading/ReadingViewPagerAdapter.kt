package com.sh.sosoopic.reading

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sh.sosoopic.common.Script

class ReadingViewPagerAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {

    private var scripts = emptyList<Script>()
    private var currentPosition = 0

    override fun getItemCount(): Int = scripts.size

    fun getCurrentID(): Int = scripts[currentPosition].id
    fun getCurrentSubject(): String = scripts[currentPosition].subject
    fun getCurrentType(): String = scripts[currentPosition].type
    fun getCurrentQuestion(): String = scripts[currentPosition].question
    fun getCurrentAnswer(): String = scripts[currentPosition].answer

    override fun createFragment(position: Int): Fragment {
        val fragment = ReadingFragment()
        fragment.arguments = Bundle().apply {
            putString("KEY_SUBJECT", scripts[position].subject)
            putString("KEY_TYPE", scripts[position].type)
            putString("KEY_QUESTION", scripts[position].question)
            putString("KEY_ANSWER", scripts[position].answer)
        }
        return fragment
    }

    internal fun setScripts(scripts: List<Script>) {
        this.scripts = scripts
        notifyDataSetChanged()
    }

    internal fun setCurrentPosition(currentPosition: Int) {
        this.currentPosition = currentPosition
    }
}