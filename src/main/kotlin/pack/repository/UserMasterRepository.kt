package pack.repository

import org.springframework.data.jpa.repository.JpaRepository
import pack.entity.UserMaster
import java.util.Optional

interface UserMasterRepository : JpaRepository<UserMaster, Int> {
    fun findByUserId(userId: String): UserMaster?
}
