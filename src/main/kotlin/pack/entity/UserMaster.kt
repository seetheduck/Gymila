package pack.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import java.time.Instant

@Entity
@Table(name = "user_master")
class UserMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @Size(max = 255)
    @Column(name = "user_id")
    var userId: String? = null

    @Size(max = 255)
    @Column(name = "password")
    var password: String? = null

    @Size(max = 255)
    @Column(name = "nickname")
    var nickname: String? = null

    @ColumnDefault("current_timestamp()")
    @Column(name = "create_date")
    var createDate: Instant? = null

    @ColumnDefault("current_timestamp()")
    @Column(name = "update_date")
    var updateDate: Instant? = null

    @ColumnDefault("0")
    @Column(name = "matching_is")
    var matchingIs: Boolean? = null
}