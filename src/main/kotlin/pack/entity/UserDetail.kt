package pack.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Size

@Entity
@Table(name = "user_detail")
class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @Size(max = 255)
    @Column(name = "user_id")
    var userId: String? = null

    @Size(max = 255)
    @Column(name = "name")
    var name: String? = null

    @Column(name = "gender")
    var gender: Boolean? = null

    @Column(name = "age")
    var age: Int? = null

    @Size(max = 255)
    @Column(name = "email")
    var email: String? = null
}