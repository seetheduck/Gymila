package pack.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pack.entity.Community

@Repository
interface CommunityRepository : JpaRepository<Community, Int> {
    fun findByTitleContaining(title: String, pageable: Pageable): Page<Community> // 제목 검색 기능
}