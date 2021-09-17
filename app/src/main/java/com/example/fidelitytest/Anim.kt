package com.example.fidelitytest

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Anim(
    var request_hash: String ?= null,
    var request_cached: Boolean ?= null,
    var request_cache_expiry: Long ?= null,
    var results: List<AnimsResult> ?= emptyList(),
    var last_page: Int ?= null
): Parcelable


data class Anims(
    var animS: List<AnimsResult> ?= null
)

@Parcelize
data class AnimsResult(
    var mal_id: Long ?= null,
    var url: String ?= null,
    var image_url: String ?= null,
    var title: String ?= null,
    var airing: Boolean ?= null,
    var synopsis: String ?= null,
    var type: String ?= null,
    var episodes: Int ?= null,
    var score: Double ?= null,
    var start_date: String ?= null,
    var end_date: String ?= null,
    var members: Long ?= null,
    var rated: String ?= null
):Parcelable
