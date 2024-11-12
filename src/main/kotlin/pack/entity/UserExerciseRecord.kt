package pack.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import java.time.Instant

@Entity
@Table(name = "user_exercise_record")
class UserExerciseRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @Size(max = 255)
    @Column(name = "user_id")
    var userId: String? = null

    @ColumnDefault("current_timestamp()")
    @Column(name = "create_at")
    var createAt: Instant? = null

    @ColumnDefault("current_timestamp()")
    @Column(name = "update_at")
    var updateAt: Instant? = null

    @Column(name = "exercise_set")
    var exerciseSet: Int? = null

    @Column(name = "exercise_count")
    var exerciseCount: Int? = null

    @Column(name = "exercise_kg")
    var exerciseKg: Int? = null

    @Size(max = 255)
    @Column(name = "exercise_time")
    var exerciseTime: String? = null

    @Size(max = 255)
    @Column(name = "exercise_photo")
    var exercisePhoto: String? = null

    @Lob
    @Column(name = "exercise_memo")
    var exerciseMemo: String? = null

    @Lob
    @Column(name = "meal_record")
    var mealRecord: String? = null

    @Column(name = "`condition`")
    var condition: Int? = null
}