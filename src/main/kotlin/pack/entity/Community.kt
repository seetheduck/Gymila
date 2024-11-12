package pack.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import java.time.Instant

@Entity
@Table(name = "community")
class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @Size(max = 255)
    @Column(name = "user_id")
    var userId: String? = null

    @ColumnDefault("current_timestamp()")
    @Column(name = "create_at")
    var createAt: Instant? = null

    @Column(name = "updated_at")
    var updatedAt: Instant? = null

    @Size(max = 255)
    @Column(name = "title")
    var title: String? = null

    @Lob
    @Column(name = "contents")
    var contents: String? = null
}