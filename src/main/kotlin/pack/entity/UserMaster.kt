package pack.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@Entity
@Table(name = "user_master")
@EntityListeners(AuditingEntityListener::class)
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

    @CreatedDate
    @Column(name = "create_date", updatable = false)
    var createDate: Instant? = null

    @LastModifiedDate
    @Column(name = "update_date")
    var updateDate: Instant? = null

    @ColumnDefault("0")
    @Column(name = "matching_is")
    var matchingIs: Int? = 0

    fun updatePassword(password: String){
        this.password = password
    }

    fun updateNickname(nickname: String){
        this.nickname = nickname
    }

    fun updateUpdateDate(){
        this.updateDate = Instant.now()
    }
}
