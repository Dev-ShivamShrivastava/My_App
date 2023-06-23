package com.example.myapp.model

data class UserListResponse(
    var `data`: ArrayList<Data>,
    var pages: Int?,
    var per_page: Int?,
    var support: Support?,
    var total: Int?,
    var total_pages: Int?
) {
    data class Data(
        var avatar: String?,
        var email: String?,
        var first_name: String?,
        var id: Int?,
        var last_name: String?,
        var isSelected:Boolean=false
    )

    data class Support(
        var text: String?,
        var url: String?
    )
}