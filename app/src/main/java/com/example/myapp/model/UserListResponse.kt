package com.example.myapp.model

data class UserListResponse(
    var `data`: ArrayList<Data>?= null,
    var pages: Int?= null,
    var per_page: Int?= null,
    var support: Support?= null,
    var total: Int?= null,
    var total_pages: Int?= null
) {
    data class Data(
        var avatar: String?= null,
        var email: String?= null,
        var first_name: String?= null,
        var id: Int?= null,
        var last_name: String?= null,
        var isSelected:Boolean=false
    )

    data class Support(
        var text: String?= null,
        var url: String?= null
    )
}