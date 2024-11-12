package pack.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import java.time.Instant

@Entity
@Table(name = "user_physical_info")
class UserPhysicalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @Size(max = 255)
    @Column(name = "user_id")
    var userId: String? = null

    @Column(name = "height")
    var height: Double? = null

    @Column(name = "weight")
    var weight: Double? = null

    @Column(name = "skeletal_muscle_mass")
    var skeletalMuscleMass: Double? = null

    @Column(name = "body_fat_percentage")
    var bodyFatPercentage: Double? = null

    @Column(name = "exercise_frequency")
    var exerciseFrequency: Int? = null

    @Size(max = 255)
    @Column(name = "goal")
    var goal: String? = null

    @ColumnDefault("current_timestamp()")
    @Column(name = "measure_date")
    var measureDate: Instant? = null

    @Column(name = "category_id")
    var categoryId: Int? = null
}