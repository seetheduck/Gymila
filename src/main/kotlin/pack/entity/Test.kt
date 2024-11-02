package pack.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Test (
    @Id
    val id: Int

)