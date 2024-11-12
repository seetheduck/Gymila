package pack.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Size

@Entity
@Table(name = "`like`")
class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @Column(name = "post_id")
    var postId: Int? = null

    @Size(max = 255)
    @Column(name = "user_id")
    var userId: String? = null
}