package pack.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pack.dto.request.CommentReqDto
import pack.dto.response.CommentResDto
import pack.service.CommentService

@RestController
@RequestMapping("/comments")
class CommentController (
    private val commentService: CommentService
) {

    // 댓글 작성
    @PostMapping
    fun addComment(
        @RequestBody requestDto: CommentReqDto,
        httpRequest: HttpServletRequest
    ): ResponseEntity<CommentResDto> {
        // 사용자 ID 확인
        val userId = httpRequest.getAttribute("userId") as String

        val response = commentService.addComment(requestDto, userId)
        return ResponseEntity.ok(response)
    }

    // 특정 댓글의 대댓글 조회
    @GetMapping("/replies/{parentId}")
    fun getRepliesByParentId(@PathVariable parentId: Int): ResponseEntity<List<CommentResDto>> {
        val response = commentService.getRepliesByParentId(parentId)
        return ResponseEntity.ok(response)
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable commentId: Int,
        @RequestBody requestDto: CommentReqDto,
        httpRequest: HttpServletRequest
    ): ResponseEntity<CommentResDto> {
        // 사용자 ID 확인
        val userId = httpRequest.getAttribute("userId") as String

        val response = commentService.updateComment(commentId, userId, requestDto)
        return ResponseEntity.ok(response)
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable commentId: Int,
        httpRequest: HttpServletRequest
    ): ResponseEntity<Void> {
        // 사용자 ID 확인
        val userId = httpRequest.getAttribute("userId") as String

        commentService.deleteComment(commentId, userId)
        return ResponseEntity.noContent().build()
    }

    // 특정 커뮤니티의 댓글 조회 (사용자 확인 불필요)
    @GetMapping("/community/{communityId}")
    fun getCommentsByCommunityId(
        @PathVariable communityId: Int,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<Page<CommentResDto>> {
        val response = commentService.getCommentsByCommunityId(communityId, page, size)
        return ResponseEntity.ok(response)
    }
}

