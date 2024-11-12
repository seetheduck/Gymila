package pack.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Size
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@Entity
@Table(name = "upload_files")
@EntityListeners(AuditingEntityListener::class)
class UploadFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @Size(max = 255)
    @Column(name = "community_id")
    var communityId: String? = null

    @Size(max = 255)
    @Column(name = "path")
    var path: String? = null

    @CreatedDate
    @Column(name = "upload_date", updatable = false)
    var uploadDate: Instant? = null
}
