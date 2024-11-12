package pack.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import java.time.Instant

@Entity
@Table(name = "upload_files")
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

    @ColumnDefault("current_timestamp()")
    @Column(name = "upload_date")
    var uploadDate: Instant? = null
}