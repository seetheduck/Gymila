package pack.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Size
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@Entity
@Table(name = "community")
@EntityListeners(AuditingEntityListener::class)
class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @Size(max = 255)
    @Column(name = "user_id")
    var userId: String? = null

    @CreatedDate
    @Column(name = "create_at", updatable = false)
    var createAt: Instant? = null

    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedAt: Instant? = null

    @Size(max = 255)
    @Column(name = "title")
    var title: String? = null

    @Lob
    @Column(name = "contents")
    var contents: String? = null
}
