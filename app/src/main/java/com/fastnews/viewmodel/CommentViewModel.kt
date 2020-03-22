package com.fastnews.viewmodel

import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fastnews.mechanism.Coroutines
import com.fastnews.data.model.CommentData
import com.fastnews.interactors.usecase.GetPostsCommentsUseCase

class CommentViewModel(private val getComments:GetPostsCommentsUseCase): ViewModel() {

    private lateinit var comments: MutableLiveData<List<CommentData>>

    @UiThread
    fun getComments(postId: String): LiveData<List<CommentData>> {
        if(!::comments.isInitialized) {
            comments = MutableLiveData()
            Coroutines.ioThenMain({
                getComments.comments(postId)
            }) {
                comments.postValue(it)
            }
        }
        return comments
    }

}