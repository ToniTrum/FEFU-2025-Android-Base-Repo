package co.feip.fefu2025.data.model.dto

import com.google.gson.annotations.SerializedName

data class GitRepositoryDto (
    val id: Int,
    val description: String,
    val name: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("star_count") val starCount: Int,
    @SerializedName("forks_count") val forksCount: Int
)