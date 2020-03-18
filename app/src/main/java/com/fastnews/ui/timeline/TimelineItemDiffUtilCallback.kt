package com.fastnews.ui.timeline


import androidx.recyclerview.widget.DiffUtil
import com.fastnews.service.model.PostData

class TimelineItemDiffUtilCallback : DiffUtil.ItemCallback<PostData>() {

    override fun areItemsTheSame(oldItem: PostData, newItem: PostData): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: PostData, newItem: PostData): Boolean {
        return oldItem.title == newItem.title
                && oldItem.score == newItem.score
                && oldItem.num_comments == newItem.num_comments
    }

}