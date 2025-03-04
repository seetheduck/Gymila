package pack.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pack.entity.Comment

@Repository
interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByCommunityId(communityId: Int, pageable: Pageable): Page<Comment>
    fun findByParentId(parentId: Int): List<Comment>
}
