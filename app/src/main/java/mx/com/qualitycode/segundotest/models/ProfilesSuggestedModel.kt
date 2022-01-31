package mx.com.qualitycode.segundotest.models



data class ProfilesSuggestedModel(
    var `data`: List<Data>,
    var error: String,
    var total: Int
) {
    data class Data(
        var customerId: String,
        var follow: Int,
        var nickname: String,
        var photo: String,
        var photoLastPost: String
    )
}