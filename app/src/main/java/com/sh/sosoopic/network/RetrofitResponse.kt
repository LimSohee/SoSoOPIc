package com.sh.sosoopic.network

class RetrofitResponse {
    var count: Int? = null

    inner class List {
        var idx: String? = null
        var imageUrl: String? = null
        var imageType: String? = null
    }
    var posterList: ArrayList<List>? = null
}