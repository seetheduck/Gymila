package pack.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import pack.entity.QCategory.category
import pack.entity.QScore.score1
import pack.entity.Score

@Repository
class ScoreRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : ScoreRepositoryCustom {

    override fun findTop5ByCategory(gfcCategoryNo: Int): List<Score> {
        return queryFactory
            .selectFrom(score1)
            .join(score1.category, category)
            .where(category.gfcCategoryNo.eq(gfcCategoryNo))
            .orderBy(score1.rank.asc())
            .limit(5)
            .fetch()
    }

    override fun findTop5InAllCategories(): List<Score> {
        return queryFactory
            .selectFrom(score1)
            .join(score1.category, category)
            .where(score1.rank.loe(5))
            .orderBy(category.gfcCategoryNo.asc(), score1.rank.asc())
            .fetch()
    }
}
