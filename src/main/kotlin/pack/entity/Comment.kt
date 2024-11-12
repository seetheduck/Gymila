package pack.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import java.time.Instant

@Entity
@Table(name = "comment")
class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @Column(name = "post_id")
    var postId: Int? = null

    @Size(max = 255)
    @Column(name = "user_id")
    var userId: String? = null

    @Lob
    @Column(name = "content")
    var content: String? = null

    @ColumnDefault("current_timestamp()")
    @Column(name = "create_at")
    var createAt: Instant? = null

    @Column(name = "is_secret")
    var isSecret: Boolean? = null
}