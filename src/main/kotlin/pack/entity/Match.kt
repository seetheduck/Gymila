package pack.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Size

@Entity
@Table(name = "`match`")
class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @Column(name = "category_no")
    var categoryNo: Int? = null

    @Size(max = 255)
    @Column(name = "player1_id")
    var player1Id: String? = null

    @Size(max = 255)
    @Column(name = "player2_id")
    var player2Id: String? = null

    @Column(name = "result")
    var result: Boolean? = null
}