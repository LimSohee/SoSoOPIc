package com.sh.sosoopic.common

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

class ScriptRepository(application: Application) {

    private val scriptDao: ScriptDao by lazy {
        val db = ScriptDB.getInstance(application)!!
        db.getScriptDao()
    }

    //모든 Script 가져오는 함수
    fun getAllScript(): LiveData<List<Script>> {
        return scriptDao.getAllScript()
    }

    //Script 추가하는 함수
    fun insertScript(script: Script) {
        CoroutineScope(Dispatchers.IO).launch {
            scriptDao.insertScript(script)
        }
    }

    //Script 수정하는 함수
    fun updateScript(script: Script) {
        CoroutineScope(Dispatchers.IO).launch {
            scriptDao.updateScript(script)
        }
    }

    //Script 삭제하는 함수
    fun deleteScript(script: Script) {
        CoroutineScope(Dispatchers.IO).launch {
            scriptDao.deleteScript(script)
        }
    }
}