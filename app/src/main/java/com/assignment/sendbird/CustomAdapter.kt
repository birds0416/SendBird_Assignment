package com.assignment.sendbird

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CustomAdapter (private val context: Context, private val dataList: ArrayList<data_New>) : RecyclerView.Adapter<CustomAdapter.ItemViewHolder>(){

    interface ItemClickListener {
        fun onItemClick (position: Int)
    }

    var mPos = 0

    fun getPostion() : Int {
        return mPos
    }

    private fun setPosition(position: Int) {
        mPos = position
    }

    fun addItem(dataNew: data_New) {
        dataList.add(dataNew)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        if (position > 0) {
            dataList.removeAt(position)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.view_item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        mItemClickListener = itemClickListener
    }

    private lateinit var mItemClickListener : ItemClickListener

    inner class ItemViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                mItemClickListener.onItemClick(adapterPosition)
            }
        }

        private val title_new = itemView.findViewById<TextView>(R.id.title_new)
        private val subtitle_new = itemView.findViewById<TextView>(R.id.subtitle_new)
        private val isbn13_new = itemView.findViewById<TextView>(R.id.isbn13_new)
        private val price_new = itemView.findViewById<TextView>(R.id.price_new)
        private val image_new = itemView.findViewById<ImageView>(R.id.image_new)
        private val url_new = itemView.findViewById<TextView>(R.id.url_new)


        fun bind (dataNew: data_New, context: Context) {
//            if (dataNew.img != "") {
            val resourceId = context.resources.getIdentifier(dataNew.img, "drawable", context.packageName)
            val imageUrl : String = dataNew.img
            Glide.with(context).load(imageUrl).into(image_new)
//                if(resourceId > 0){
//                    image_new.setImageResource(resourceId)
//                } else {
//                    image_new.setImageResource(R.mipmap.ic_launcher_round)
//                }
//            } else {
//                image_new.setImageResource(R.mipmap.ic_launcher_round)
//            }

            title_new.text = dataNew.title
            subtitle_new.text = dataNew.subtitle
            isbn13_new.text = dataNew.isbn13
            price_new.text = dataNew.price
            url_new.text = dataNew.url
        }

    }

}