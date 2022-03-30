package com.huynn109.integratenative

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class MovieAdapter(
    var context: Context,
    private var dataList: Array<String> = arrayOf("Sunday", "Monday"),
    private val flutterViewEngine: FlutterViewEngine
) : BaseAdapter() {
    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItem(position: Int): String {
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val vh: ListRowHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false)
            vh = ListRowHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }

        vh.label.text = dataList[position]
        return view
    }
}

private class ListRowHolder(row: View?) {
    public val label: TextView = row?.findViewById(R.id.label) as TextView

}