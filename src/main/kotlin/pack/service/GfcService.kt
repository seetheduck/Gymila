package pack.service

import org.springframework.stereotype.Service
import pack.dto.response.RankResponse
import pack.repository.ScoreRepository

@Service
class GfcService(
    private val scoreRepository: ScoreRepository
) {
    fun getTopRanksByCategory(gfcCategoryNo: Int?): List<RankResponse> {
        val scores = if (gfcCategoryNo != null) {
            scoreRepository.findTop5ByCategory(gfcCategoryNo)
        } else {
            scoreRepository.findTop5InAllCategories()
        }
        return scores.map { RankResponse.from(it) }
    }
}