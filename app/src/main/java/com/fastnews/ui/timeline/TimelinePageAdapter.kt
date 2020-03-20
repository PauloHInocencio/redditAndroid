package com.fastnews.ui.timeline

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import com.fastnews.R
import com.fastnews.data.model.PostData
import kotlinx.android.synthetic.main.include_item_timeline_thumbnail.view.*

class TimelinePageAdapter(val onClickItem: (PostData?, ImageView) -> Unit) : PagedListAdapter<PostData, TimelineItemViewHolder>(TimelineItemDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_timeline, parent, false)
        return TimelineItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TimelineItemViewHolder, position: Int) {
        holder.data = getItem(position)
        holder.view.setOnClickListener { onClickItem(holder.data, holder.view.item_timeline_thumbnail) }
    }


}