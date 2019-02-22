package ru.saptan.anonym.domain.model.data

import com.google.gson.annotations.SerializedName
import ru.saptan.anonym.domain.model.rest.PostListRequest

class Post(var id: Int = 0, var text: String? = null, var date: Long? = 0L, var category: Int? = 0,
           var type: Int? = PostListRequest.TYPE_POST_POPULAR,
           @SerializedName("owner_name") var ownerName: String? = "",
           @SerializedName("owner_id") var ownerId: Int? = 0,
           @SerializedName("owner_photo") var ownerPhoto: String? = "",
           @SerializedName("reposts") var countReposts: Int? = 0,
           var postviews: PostCountStat? = null,
           var postLikes: PostCountStat? = null,
           var comments: PostCountStat? = null,
           var attachments: List<PostAttachment>? = null) {

    fun getMediumPhotoUrl(): String? {
        return attachments?.firstOrNull { it.photo?.urlMediumPhoto != null }?.photo?.urlMediumPhoto
    }

}

data class PostCountStat(var count: Int? = 0, var my: Int? = 0)

data class PostAttachment(var link: String? = null, var type: String? = "photo",
                          var photo: AttachmentPhoto? = null)

data class AttachmentPhoto(
        @SerializedName("photo_big") var urlBigPhoto: String? = null,
        @SerializedName("photo_medium") var urlMediumPhoto: String? = null,
        @SerializedName("photo_small") var urlSmallPhoto: String? = null,
        var size: PhotoSizes? = null
)

data class PhotoSizes(
        @SerializedName("photo_big") var photoBig: SizeItem? = null,
        @SerializedName("photo_medium") var photoMedium: SizeItem? = null,
        @SerializedName("photo_small") var photoSmall: SizeItem? = null)

data class SizeItem(var width: Int? = 0, var height: Int? = 0)




