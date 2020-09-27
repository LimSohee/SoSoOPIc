package com.sh.sosoopic.common

import android.content.Context
import java.io.File
import java.io.IOException


/*
 * 스크립트 Data Model 역할의 Class
 */
data class ScriptData(
    var fileName: String,
    var question: String,
    var answer: String,
    var subject: String,
    var type: String
)

object ScriptManager {
    private const val SCRIPT_FILE_PATH = "/SoSoOpic/Script/"

    var subjectList = mutableListOf<String>()
    var typeList = mutableListOf<String>()
    var scriptList = mutableListOf<ScriptData>()


    //주제 추가하는 함수
    fun addSubject(newSubject: String) {
        subjectList.add(newSubject)
    }

    //주제 삭제하는 함수
    fun removeSubject(newSubject: String) {
        subjectList.remove(newSubject)
    }

    //유형 추가하는 함수
    fun addType(newType: String) {
        typeList.add(newType)
    }

    //유형 삭제하는 함수
    fun removeType(newType: String) {
        typeList.remove(newType)
    }

    fun saveSubjectAndTypeList(context: Context, subjectList: MutableList<String>, typeList: MutableList<String>) {
        this.subjectList = subjectList
        this.typeList = typeList
        PrefManager.setStringArrayPref(context, "KEY_SUBJECT_LIST", subjectList)
        PrefManager.setStringArrayPref(context, "KEY_TYPE_LIST", typeList)
    }

    fun getSubjectList(context: Context): MutableList<String> {
        subjectList = PrefManager.getStringArrayPref(context, "KEY_SUBJECT_LIST")
        return subjectList
    }

    fun getTypeList(context: Context): MutableList<String> {
        typeList = PrefManager.getStringArrayPref(context, "KEY_TYPE_LIST")
        return typeList
    }

    //모든 스크립트 읽어오는 함수
    fun isEmptyScript(): Boolean {
        return scriptList.count() <= 0
    }

    //모든 스크립트 읽어오는 함수
    fun readAllScript(context: Context) {
        val dir = File(context.filesDir, SCRIPT_FILE_PATH)
        dir.listFiles().forEach {
            val scriptData: ScriptData = readScript(it);
            scriptList.add(scriptData)
        }
    }

    //스크립트 1개 읽어오는 함수
    fun readScript(file: File): ScriptData {
        val text = File(file.path).readText(Charsets.UTF_8)
        val fileName = file.name

        val textArray = text.split("\n\n")
        val question = textArray[0]
        val answer = textArray[1]

        val fileNameArray = fileName.split("_")
        val subject = fileNameArray[0]
        val type = fileNameArray[1]

        return ScriptData(fileName, question, answer, subject, type)
    }

    //스크립트 1개 쓰는 함수
    fun writeScript(
        context: Context,
        question: String,
        answer: String,
        subject: String,
        type: String
    ) {
        val filePath: String = SCRIPT_FILE_PATH + subject + "_" + type + ".txt"
        val text: String = question + "\n\n" + answer
        try {
            File(context.filesDir, filePath).writeText(text, Charsets.UTF_8)
        } catch (e: IOException) {
        }
    }

    //폴더 생성하는 함수
    fun createFolder(context: Context) {
        val dir = File(context.filesDir, SCRIPT_FILE_PATH)
        if (!dir.exists()) {
            dir.mkdirs()
        }
    }
}