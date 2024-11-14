package pack.dto.response

import pack.entity.Score

data class RankResponse(
    val userId: String,
    val score: Int,
    val rank: Int,
    val gfcCategoryNo: Int,
    val categoryName: String?
) {
    companion object {
        fun from(score: Score): RankResponse {
            return RankResponse(
                userId = score.userId ?: "",
                score = score.score ?: 0,
                rank = score.rank ?: 0,
                gfcCategoryNo = score.category?.gfcCategoryNo ?: 0,
                categoryName = score.category?.categoryName
            )
        }
    }
}

