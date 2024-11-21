package pack.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import pack.dto.request.CommunityReqDto
import pack.dto.response.CommunityResDto
import pack.service.CommunityService

@Tag(name = "Community API", description = "Operations related to community posts")
@RestController
@RequestMapping("/community")
class CommunityController (
    private val communityService: CommunityService
) {

    @Operation(summary = "Create a new post", description = "Allows a logged-in user to create a new community post.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Post created successfully",
            content = [Content(schema = Schema(implementation = CommunityResDto::class))]),
        ApiResponse(responseCode = "400", description = "Invalid request data"),
        ApiResponse(responseCode = "401", description = "Unauthorized access")
    ])
    @PostMapping
    fun createPost(
        @RequestParam("title") title: String,
        @RequestParam("contents") contents: String,
        @RequestPart("files", required = false) files: List<MultipartFile>?,
        httpRequest: HttpServletRequest
    ): ResponseEntity<CommunityResDto> {
        // AuthInterceptor에서 설정한 userId 가져오기
        val userId = httpRequest.getAttribute("userId") as String

        val communityReqDto = CommunityReqDto(
            title = title,
            contents = contents
        )
        val response = communityService.createPost(userId, communityReqDto, files)
        return ResponseEntity.ok(response)
    }

    @PutMapping("/{id}")
    fun updatePost(
        @PathVariable id: Int,
        @RequestParam("title") title: String,
        @RequestParam("contents") contents: String,
        @RequestPart("files", required = false) files: List<MultipartFile>?,
        httpRequest: HttpServletRequest
    ): ResponseEntity<CommunityResDto> {
        // AuthInterceptor에서 설정한 userId 가져오기
        val userId = httpRequest.getAttribute("userId") as String

        val communityReqDto = CommunityReqDto(
            title = title,
            contents = contents
        )
        val response = communityService.updatePost(id, userId, communityReqDto, files)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun deletePost(
        @PathVariable id: Int,
        httpRequest: HttpServletRequest
    ): ResponseEntity<Void> {
        // AuthInterceptor에서 설정한 userId 가져오기
        val userId = httpRequest.getAttribute("userId") as String

        communityService.deletePost(id, userId)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/posts")
    fun getAllPosts(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<Page<CommunityResDto>> {
        val response = communityService.getAllPosts(page, size)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/posts/search")
    fun searchPosts(
        @RequestParam title: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<Page<CommunityResDto>> {
        val response = communityService.searchPosts(title, page, size)
        return ResponseEntity.ok(response)
    }
}