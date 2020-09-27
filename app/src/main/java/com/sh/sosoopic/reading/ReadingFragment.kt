package com.sh.sosoopic.reading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sh.sosoopic.R

class ReadingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.item_reading, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.apply {
            val subjectTextView: TextView = view.findViewById(R.id.subjectTextView)
            subjectTextView.text = getString("KEY_SUBJECT")

            val typeTextView: TextView = view.findViewById(R.id.typeTextView)
            typeTextView.text = getString("KEY_TYPE")

            val questionTextView: TextView = view.findViewById(R.id.questionTextView)
            questionTextView.text = getString("KEY_QUESTION")

            val answerTextView: TextView = view.findViewById(R.id.answerTextView)
            answerTextView.text = getString("KEY_ANSWER")
        }
    }
}