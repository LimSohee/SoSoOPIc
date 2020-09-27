package com.sh.sosoopic.common

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

private const val WRITE_EXTERNAL_STORAGE_RESULT_CODE = 0
private const val RECORD_AUDIO_RESULT_CODE = 1

object PermissionUtil {
    fun checkWriteExternalStoragePermission(context: Context) {
        val activity = context as AppCompatActivity

        //사용자의 OS 버전이 마시멜로우 이상인지 체크
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permissionResult =
                context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            if (permissionResult == PackageManager.PERMISSION_DENIED) {
                if (activity.shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    //권한이 필요한 이유 설명이 필요한 경우
                    val builder = AlertDialog.Builder(context)
                    builder.setTitle("권한 요청")
                        .setMessage("스크립트 파일에 접근하기 위해 단말기의 \"WRITE_EXTERNAL_STORAGE\" 권한이 필요합니다. 허용하시겠습니까?")
                        .setPositiveButton("네",
                            DialogInterface.OnClickListener { dialog, which ->
                                //WRITE_EXTERNAL_STORAGE 권한 허용을 선택한 경우
                                //WRITE_EXTERNAL_STORAGE 권한 요청 함
                                activity.requestPermissions(
                                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                                    WRITE_EXTERNAL_STORAGE_RESULT_CODE
                                )
                            })
                        .setNegativeButton("아니요",
                            DialogInterface.OnClickListener { dialog, which ->
                                //WRITE_EXTERNAL_STORAGE 권한 거부를 선택한 경우
                                //기능 이용 불가 안내 Toast팝업 노출
                                Toast.makeText(
                                    context,
                                    "\"WRITE_EXTERNAL_STORAGE\" 권한을 거부하여 [스크립트 쓰기/읽기/듣기/말하기] 기능을 이용할 수 없습니다.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            })
                        .create()
                        .show()
                } else {
                    //권한이 필요한 이유 설명이 필요없는 경우
                    //WRITE_EXTERNAL_STORAGE 권한 요청 함
                    activity.requestPermissions(
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        WRITE_EXTERNAL_STORAGE_RESULT_CODE
                    )
                }
            } else {
                //WRITE_EXTERNAL_STORAGE 권한 획득 상태인 경우
                //모든 스크립트 읽어오기
                ScriptManager.readAllScript(context)
            }
        } else {
            //마시멜로우 미만 버전인 경우, WRITE_EXTERNAL_STORAGE 권한 획득 상태 임
            //모든 스크립트 읽어오기
            ScriptManager.readAllScript(context)
        }
    }

    fun checkRecordAudioPermission(context: Context) {
        val activity = context as AppCompatActivity

        //사용자의 OS 버전이 마시멜로우 이상인지 체크
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permissionResult = context.checkSelfPermission(Manifest.permission.RECORD_AUDIO)
            if (permissionResult == PackageManager.PERMISSION_DENIED) {
                if (activity.shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO)) {
                    //권한이 필요한 이유 설명이 필요한 경우
                    val builder = AlertDialog.Builder(context)
                    builder.setTitle("권한 요청")
                        .setMessage("오디오 녹음을 위해 단말기의 \"RECORD_AUDIO\" 권한이 필요합니다. 허용하시겠습니까?")
                        .setPositiveButton("네",
                            DialogInterface.OnClickListener { dialog, which ->
                                //RECORD_AUDIO 권한 허용을 선택한 경우
                                //RECORD_AUDIO 권한 요청 함
                                activity.requestPermissions(
                                    arrayOf(Manifest.permission.RECORD_AUDIO),
                                    RECORD_AUDIO_RESULT_CODE
                                )
                            })
                        .setNegativeButton("아니요",
                            DialogInterface.OnClickListener { dialog, which ->
                                //RECORD_AUDIO 권한 거부를 선택한 경우
                                //기능 이용 불가 안내 Toast팝업 노출
                                Toast.makeText(
                                    context,
                                    "\"RECORD_AUDIO\" 권한을 거부하여 [스크립트 말하기/실전연습] 기능을 이용할 수 없습니다.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            })
                        .create()
                        .show()
                } else {
                    //권한이 필요한 이유 설명이 필요없는 경우
                    //RECORD_AUDIO 권한 요청 함
                    activity.requestPermissions(
                        arrayOf(Manifest.permission.RECORD_AUDIO),
                        RECORD_AUDIO_RESULT_CODE
                    )
                }
            } else {
                //RECORD_AUDIO 권한 획득 상태인 경우
            }
        } else {
            //마시멜로우 미만 버전인 경우, RECORD_AUDIO 권한 획득 상태 임
        }
    }

    fun onRequestPermissionsResult(
        context: Context,
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            WRITE_EXTERNAL_STORAGE_RESULT_CODE ->
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //권한 허용한 경우, 모든 스크립트 읽어오기
                    ScriptManager.readAllScript(context)
                } else {
                    //권한 거부한 경우, 기능 이용 불가 안내 Toast팝업 노출
                    Toast.makeText(
                        context,
                        "\"WRITE_EXTERNAL_STORAGE\" 권한을 거부하여 [스크립트 쓰기/읽기/듣기/말하기] 기능을 이용할 수 없습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            RECORD_AUDIO_RESULT_CODE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //권한 허용한 경우
                } else {
                    //권한 거부한 경우, 기능 이용 불가 안내 Toast팝업 노출
                    Toast.makeText(
                        context,
                        "\"RECORD_AUDIO\" 권한을 거부하여 [스크립트 말하기/실전연습] 기능을 이용할 수 없습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}