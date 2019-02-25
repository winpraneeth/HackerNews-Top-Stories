package com.assignment.alchemy.hackernewsstories.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Comment: Serializable {
    @SerializedName("by")
    @Expose
    var by: String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("kids")
    @Expose
    var kids: List<Int>? = null
    @SerializedName("time")
    @Expose
    var time: Int? = null
    @SerializedName("text")
    @Expose
    var text: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("parent")
    @Expose
    var parent: Int? = null
}