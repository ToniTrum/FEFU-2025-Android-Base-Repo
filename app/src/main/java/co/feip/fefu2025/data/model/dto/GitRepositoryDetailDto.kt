package co.feip.fefu2025.data.model.dto

import com.google.gson.annotations.SerializedName

data class GitRepositoryDetailDto(
    val id: Int,
    val description: String,
    val name: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("forks_count") val forksCount: Int,
    @SerializedName("star_count") val starCount: Int,
)