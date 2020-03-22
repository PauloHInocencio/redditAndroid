package com.fastnews.util

import androidx.room.Embedded
import androidx.room.PrimaryKey
import com.fastnews.data.model.PostData
import com.fastnews.data.model.Preview
import com.fastnews.data.model.PreviewImage
import com.fastnews.data.model.PreviewImageSource



object PostDataFactory {


    fun createPostDataList(count: Int = 30) : List<PostData> {
        val items:MutableList<PostData> = mutableListOf()
        repeat(count){
            items.add(createPostData())
        }
        return items
    }

    fun createPostData() =
        PostData(
            id = DataFactory.randomUuid(),
            author = DataFactory.randomUuid(),
            thumbnail = DataFactory.randomUuid(),
            name = DataFactory.randomUuid(),
            num_comments = DataFactory.randomInt(),
            score = DataFactory.randomInt(),
            title = DataFactory.randomUuid(),
            created_utc = DataFactory.randomLong(),
            url = DataFactory.randomUuid(),
            preview = createPreview()
    )

    fun createPreview() =
        Preview(
            previewId = DataFactory.randomInt(),
            images = createPreviewImageList()
        )

    fun createPreviewImageList(count:Int = 10) : List<PreviewImage> {
        val items:MutableList<PreviewImage> = mutableListOf()
        repeat(count){
            items.add(createPreviewImage())
        }
        return items
    }


    fun createPreviewImage() : PreviewImage =
        PreviewImage(
            id = DataFactory.randomUuid(),
            source = createPreviewImageDataSource()
        )


    fun createPreviewImageDataSource() : PreviewImageSource =
        PreviewImageSource(
            url = DataFactory.randomUuid() ,
            width = DataFactory.randomInt(),
            height = DataFactory.randomInt()
        )

}

