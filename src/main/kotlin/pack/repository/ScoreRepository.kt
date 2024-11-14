package pack.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import pack.entity.Score

interface ScoreRepository : JpaRepository<Score, Long>, ScoreRepositoryCustom