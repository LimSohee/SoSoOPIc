package com.sh.sosoopic.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sh.sosoopic.R
import com.sh.sosoopic.common.PermissionUtil
import com.sh.sosoopic.listening.ListeningActivity
import com.sh.sosoopic.network.RetrofitAPI
import com.sh.sosoopic.network.RetrofitResponse
import com.sh.sosoopic.practice.PracticeActivity
import com.sh.sosoopic.reading.ReadingActivity
import com.sh.sosoopic.speaking.SpeakingActivity
import com.sh.sosoopic.survey.SurveyActivity
import com.sh.sosoopic.writing.WritingActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //////////Test Code
        var retrofit: Retrofit = Retrofit.Builder().apply {
            baseUrl("https://sosoopic/getallscript")
            addConverterFactory(GsonConverterFactory.create())
        }.build()

        var retrofitAPI: RetrofitAPI = retrofit.create(RetrofitAPI::class.java)
        var call: Call<RetrofitResponse> = retrofitAPI.posterList("5525255", "0", "5")
        call.enqueue(object: Callback<RetrofitResponse> {
            override fun onResponse(call: Call<RetrofitResponse>, response: Response<RetrofitResponse>) {
                var data: RetrofitResponse? = response?.body()
            }

            override fun onFailure(call: Call<RetrofitResponse>?, t: Throwable?) {
            }
        })
        //////////

        //설문조사 버튼
        surveyBtn.setOnClickListener {
            val surveyIntent = Intent(this, SurveyActivity::class.java)
            startActivity(surveyIntent)
        }

        //스크립트 쓰기 버튼
        writingBtn.setOnClickListener {
            val writingIntent = Intent(this, WritingActivity::class.java)
            startActivity(writingIntent)
        }

        //스크립트 읽기 버튼
        readingBtn.setOnClickListener {
            val readingIntent = Intent(this, ReadingActivity::class.java)
            startActivity(readingIntent)
        }

        //스크립트 듣기 버튼
        listeningBtn.setOnClickListener {
            val listeningIntent = Intent(this, ListeningActivity::class.java)
            startActivity(listeningIntent)
        }

        //스크립트 말하기 버튼
        speakingBtn.setOnClickListener {
            val speakingIntent = Intent(this, SpeakingActivity::class.java)
            startActivity(speakingIntent)
        }

        //실전연습 버튼
        practiceBtn.setOnClickListener {
            val practiceIntent = Intent(this, PracticeActivity::class.java)
            startActivity(practiceIntent)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionUtil.onRequestPermissionsResult(
            this,
            requestCode,
            permissions,
            grantResults
        )
    }
}

