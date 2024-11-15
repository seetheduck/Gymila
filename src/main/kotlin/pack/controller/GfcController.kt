package pack.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pack.dto.response.RankResponse
import pack.service.GfcService

@RestController
@RequestMapping("/gfc")
class GfcController(
    private val gfcService: GfcService
    ) {
    // 각 체급별 상위 1~5위 나열
    @GetMapping("/top-ranks")
    fun getTopRanks(
        @RequestParam(required = false) gfcCategoryNo: Int?
    ): ResponseEntity<List<RankResponse>> {
        val topRanks = gfcService.getTopRanksByCategory(gfcCategoryNo)
        return ResponseEntity.ok(topRanks)
    }
    
}