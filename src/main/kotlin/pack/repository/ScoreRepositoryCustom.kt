package pack.repository

import pack.entity.Score

interface ScoreRepositoryCustom {
    fun findTop5ByCategory(gfcCategoryNo: Int): List<Score>
    fun findTop5InAllCategories(): List<Score>
}