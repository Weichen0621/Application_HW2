package com.example.view_hw

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

//特別注意，若以後要宣告第二個程式而非一個新類別，用內建選單建立比較省事!
//路徑: New/Activity/Empty Views Activity，就和講義裡教的一樣
//否則就要到AndroidManifest.xml的application裏頭，在MainActivity底下補上:""<activity android:name=".副程式名稱" />

class SecActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sec)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edName = findViewById<EditText>(R.id.edName)
        val edPhone = findViewById<EditText>(R.id.edPhone)
        val btnSend = findViewById<Button>(R.id.btnSend)

        btnSend.setOnClickListener {
            when {
                edName.text.isEmpty() -> showToast("請輸入姓名")
                edPhone.text.isEmpty() -> showToast("請輸入電話")

                else -> {
                    val b = Bundle()
                    b.putString("name", edName.text.toString())
                    b.putString("phone", edPhone.text.toString())
                    //使用setResult()回傳聯絡人資料
                    setResult(Activity.RESULT_OK, Intent().putExtras(b))
                    finish()
                }
            }
        }
    }

    private fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}