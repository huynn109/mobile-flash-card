package com.huynn109.androidcore

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ContentProviderActivity : AppCompatActivity() {
    private val LOADER_CHEESES = 1

    private var contentProviderAdapter: ContentProviderAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider)
        val list = findViewById<RecyclerView>(R.id.list)
        list.layoutManager = LinearLayoutManager(list.context)
        contentProviderAdapter = ContentProviderAdapter()
        list.adapter = contentProviderAdapter

        LoaderManager.getInstance(this).initLoader(LOADER_CHEESES, null, mLoaderCallbacks)
    }

    private val mLoaderCallbacks: LoaderManager.LoaderCallbacks<Cursor?> =
        object : LoaderManager.LoaderCallbacks<Cursor?> {
            override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor?> {
                return CursorLoader(
                    applicationContext,
                    MyContentProvider.CONTENT_URI, arrayOf("first_name"),
                    null, null, null
                )
            }

            override fun onLoadFinished(loader: Loader<Cursor?>, data: Cursor?) {
                contentProviderAdapter?.setCheeses(data)
            }

            override fun onLoaderReset(loader: Loader<Cursor?>) {
                contentProviderAdapter?.setCheeses(null)
            }
        }

    private class ContentProviderAdapter : RecyclerView.Adapter<ContentProviderAdapter.ViewHolder>() {
        private var mCursor: Cursor? = null
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(parent)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (mCursor?.moveToPosition(position) == true) {
                holder.mText.text = mCursor?.getString(
                    mCursor?.getColumnIndexOrThrow("first_name")!!
                )
            }
        }

        override fun getItemCount(): Int {
            return mCursor?.count ?: 0
        }

        fun setCheeses(cursor: Cursor?) {
            mCursor = cursor
            notifyDataSetChanged()
        }

        class ViewHolder(parent: ViewGroup) :
            RecyclerView.ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    android.R.layout.simple_list_item_1, parent, false
                )
            ) {
            val mText: TextView = itemView.findViewById(android.R.id.text1)

        }
    }
}