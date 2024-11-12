package pack.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Size

@Entity
@Table(name = "admin")
class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @Size(max = 255)
    @Column(name = "admin_id")
    var adminId: String? = null

    @Size(max = 255)
    @Column(name = "password")
    var password: String? = null

    @Column(name = "role")
    var role: Int? = null
}