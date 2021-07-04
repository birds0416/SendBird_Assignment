 package com.assignment.sendbird

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.res.AssetManager
import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONArray
import java.io.IOException

 class NewFragment : Fragment() {

    private var dataList : ArrayList<data_New> = ArrayList()
     @SuppressLint("SetJavaScriptEnabled")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment_new, container, false)
        try {
            val assetManager : AssetManager = resources.assets
            val inputStream = assetManager.open("new.json")
            val jsonString = inputStream.bufferedReader().use { it.readText() }

            val jObject = JSONObject(jsonString)
            val jArray = jObject.getJSONArray("books")


            for (i in 0.. jArray.length() - 1) {
                val obj = jArray.getJSONObject(i)
//                val imageButton : ImageView = view.findViewById(R.id.image_new)
//                imageButton.setOnClickListener {
//                    val intent = Intent(activity, DetailBookActivity::class.java)
//                    startActivity(intent)
//                }

                val tempData = data_New(
                        obj.getString("title"),
                        obj.getString("subtitle"),
                        obj.getString("isbn13"),
                        obj.getString("price"),
                        obj.getString("image"),
                        obj.getString("url")
                    )

                dataList.add(tempData)

            }
        } catch (e: JSONException) {
             e.printStackTrace()
        } catch (e: IOException) {
             e.printStackTrace()
        }
        val mAdapter = CustomAdapter(this.requireContext(), dataList)
        val recyclerView : RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.adapter = mAdapter
        mAdapter.setItemClickListener(object : CustomAdapter.ItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(activity, DetailBookActivity::class.java)
                startActivity(intent)
            }
        })
        val lm = LinearLayoutManager(this.requireContext())
        recyclerView.layoutManager = lm
        recyclerView.setHasFixedSize(true)

        return view
    }

}