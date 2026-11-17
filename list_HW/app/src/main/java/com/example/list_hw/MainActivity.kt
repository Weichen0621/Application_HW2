package com.example.list_hw


import android.media.RouteListingPreference
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ListView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spinner = findViewById<Spinner>(R.id.spinner)
        val listView = findViewById<ListView>(R.id.listView)
        val gridView = findViewById<GridView>(R.id.gridView)
        val count = ArrayList<String>()
        val item = ArrayList<Item>()
        val priceRange = IntRange(10, 100)
        val array = resources.obtainTypedArray(R.array.image_list) //若有用到R，代表資料來自其中一個xml檔，image_list被建立於res/values/strings中

        for(index in 0 until array.length()) {

            val photo = array.getResourceId(index,0)
            val name = "水果${index+1}"
            // 亂數產生價格
            val price = priceRange.random()
            // 新增可購買數量資訊
            count.add("${index+1}個")
            // 新增水果資訊
            item.add(Item(photo, name, price))
        }

        // 釋放圖檔資源
        array.recycle()

        // 建立 ArrayAdapter 物件，並傳入字串與 simple_list_item_1.xml
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, count)

        // 設定橫向顯示列數
        gridView.numColumns = 3

        gridView.adapter = MyAdapter(this, item, R.layout.adapter_vertical)

        listView.adapter = MyAdapter(this, item, R.layout.adapter_horizontal)


    }
}