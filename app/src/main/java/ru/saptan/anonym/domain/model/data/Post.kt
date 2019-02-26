package ru.saptan.anonym.domain.model.data

import com.google.gson.annotations.SerializedName
import ru.saptan.anonym.domain.model.rest.PostListRequestParams

class Post(var id: Int = 0, var text: String? = null, var date: Long? = 0L, var category: Int? = 0,
           var type: Int? = PostListRequestParams.POPULAR,
           @SerializedName("owner_name") var ownerName: String? = "",
           @SerializedName("owner_id") var ownerId: Int? = 0,
           @SerializedName("owner_photo") var ownerPhoto: String? = "",
           @SerializedName("reposts") var countReposts: Int? = 0,
           var tags: List<String>? = null,
           var postviews: PostCountStat? = null,
           var likes: PostCountStat? = null,
           var comments: PostCountStat? = null,
           var attachments: List<PostAttachment>? = null,
           var createdAt: Long = 0L) {

    fun getPreviewPhotoUrl(): String? {
        return attachments
                ?.firstOrNull { it.hasPreviewUrl() }
                ?.getPreviewUrl()
    }

    fun getCountLikes() = likes?.count ?: 0
    fun getCountComments() = comments?.count ?: 0
    fun getCountReposts() = countReposts ?: 0

}

data class PostCountStat(var count: Int? = 0, var my: Int? = 0)

data class PostAttachment(var link: String? = null, var type: String? = "photo",
                          var photo: AttachmentPhoto? = null) {


    /**
     * Проверяет есть ли ссылка на превью (среднего или наименьшего разрмера)
     * @return true - если такая ссылка есть, false - если ссылок нет
     */
    fun hasPreviewUrl(): Boolean {
        return photo != null && (photo?.urlSmallPhoto != null || photo?.urlMediumPhoto != null)
    }

    /**
     * Предоставляет ссылку на превью к посту. В приоритете идет ссылка на превью среднего размера,
     * но в случае если такой не будет, то загружается картинка наименьшего размера
     */
    fun getPreviewUrl(): String? {
        return photo?.urlMediumPhoto ?: photo?.urlSmallPhoto ?: photo?.urlBigPhoto
    }
}

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




