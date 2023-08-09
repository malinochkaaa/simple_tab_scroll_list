package com.example.vodovoz_task

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonArray


class MainActivity : AppCompatActivity() {

    private lateinit var tovaryArray: JsonArray
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var contentResolver = getContentResolver()
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        tovaryArray = StringToJson(contentResolver)?.get("TOVARY")?.asJsonArray ?: return
        var tabArray = mutableListOf<String>()
        for(i in 0 until tovaryArray.size()){
            if(tovaryArray.get(i).asJsonObject.get("NAME") != null )
                tabArray.add(tovaryArray.get(i).asJsonObject.get("NAME").asString)
        }


        var tabHost = findViewById<TabHost>(android.R.id.tabhost);
        // инициализация
        tabHost.setup();

        for(i in 0 until tabArray.size){
            // создаем вкладку и указываем тег
            var tabSpec = tabHost.newTabSpec("tab"+ i);
            // название вкладки
            tabSpec.setIndicator(tabArray[i]);
            // указываем id компонента из FrameLayout, он и станет содержимым
            tabSpec.setContent(R.id.recyclerView);
            // добавляем в корневой элемент
            tabHost.addTab(tabSpec);
        }


        // обработчик переключения вкладок
        tabHost.setOnTabChangedListener(TabHost.OnTabChangeListener()
        {
            fun onTabChanged(tabId: String) {
                setData(tabId.last().digitToInt())
                Toast.makeText(getBaseContext(), "tabId = " + tabId, Toast.LENGTH_SHORT).show();
            }
        });
        setData(0)
    }

    private fun setData(index: Int) {
        val dataArray = tovaryArray[index].asJsonObject.get("data").asJsonArray
        val imgList = mutableListOf<String>()
        for(i in 0 until dataArray.size()){
            imgList.add("http://szorinvodovoz.tw1.ru" + dataArray.get(i).asJsonObject.get("DETAIL_PICTURE")?.asString)
        }
        println(imgList)

        val priceList = mutableListOf<String>()
        for(i in 0 until dataArray.size()){
            priceList.add(dataArray.get(i).asJsonObject.get("EXTENDED_PRICE")?.asJsonArray?.get(0)?.asJsonObject?.get("PRICE")?.asString ?: "")
        }
        println(priceList)

        // находим список
        val recycleV: RecyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val adapter = CustomRecyclerAdapter(imgList, priceList)
        // присваиваем адаптер списку
        recycleV.adapter = adapter
        recycleV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
}