package com.shofiq.gtafuitest.models


import com.google.gson.annotations.SerializedName

data class CommitResponseItem(
    @SerializedName("author")
    val author: Author,
    @SerializedName("comments_url")
    val commentsUrl: String,
    @SerializedName("commit")
    val commit: Commit,
    @SerializedName("committer")
    val committer: CommitterX,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("parents")
    val parents: List<Parent>,
    @SerializedName("sha")
    val sha: String,
    @SerializedName("url")
    val url: String
)