package com.example.ejercicioapigatos

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterString : RecyclerView.Adapter<AdapterString.CatFactsViewHolder>(){

    private var data = mutableListOf<CatsFacts>()
    var positionSelect : Int = 0


    class CatFactsViewHolder(val root : View, val textView : TextView) : RecyclerView.ViewHolder(root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatFactsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        val textView = view.findViewById<TextView>(R.id.textView)
        return CatFactsViewHolder(view, textView)
    }

    override fun onBindViewHolder(holder: CatFactsViewHolder, position: Int) {
        data?.let{
            holder.textView.text = it[position].toString()
        }

        holder.root.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                holder.root.setBackgroundColor(Color.CYAN)
                positionSelect = position+1
            }else{
                holder.root.setBackgroundColor(Color.WHITE)
            }
        }
        holder.root.setOnClickListener{
            SecondActivity.createSecondActivity(it.context, positionSelect.toString())
        }
    }

    override fun getItemCount(): Int {
        data?.let{
            return it.size
        }
        return 0
    }


    fun setData(booksList: MutableList<CatsFacts>){
        this.data = booksList
        notifyDataSetChanged()


    }

}