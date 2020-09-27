package com.sh.sosoopic.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ScriptViewModel : AndroidViewModel {
    private val repository: ScriptRepository
    private val allScript: LiveData<List<Script>>

    constructor(app: Application) : super(app) {
        repository = ScriptRepository(app)
        allScript = repository.getAllScript()
    }

    fun getAllScript(): LiveData<List<Script>> = allScript

    fun insert(script: Script) {
        repository.insertScript(script)
    }

    fun update(script: Script) {
        repository.updateScript(script)
    }

    fun delete(script: Script) {
        repository.deleteScript(script)
    }
}