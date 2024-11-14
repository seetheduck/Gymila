package pack.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Size

@Entity
@Table(name = "score")
class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @Column(name = "category_no")
    var categoryNo: Int? = null

    @Size(max = 255)
    @Column(name = "user_id")
    var userId: String? = null

    @Column(name = "score")
    var score: Int? = null

    @Column(name = "rank")
    var rank: Int? = null

    @Column(name = "badge")
    var badge: Int? = null

    // Category와의 관계 매핑 (category_no와 Category 테이블의 id가 매칭된다고 가정)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_no", insertable = false, updatable = false)
    var category: Category? = null
}