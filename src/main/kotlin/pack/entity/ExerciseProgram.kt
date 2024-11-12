package pack.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Size

@Entity
@Table(name = "exercise_program")
class ExerciseProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @Column(name = "category_no")
    var categoryNo: Int? = null

    @Size(max = 255)
    @Column(name = "user_id")
    var userId: String? = null

    @Column(name = "duration")
    var duration: Int? = null

    @Lob
    @Column(name = "intensity")
    var intensity: String? = null

    @Size(max = 255)
    @Column(name = "equipment_needed")
    var equipmentNeeded: String? = null

    @Lob
    @Column(name = "description")
    var description: String? = null

    @Column(name = "calories_burned")
    var caloriesBurned: Int? = null

    @Column(name = "exercise_set")
    var exerciseSet: Int? = null

    @Column(name = "exercise_count")
    var exerciseCount: Int? = null

    @Column(name = "exercise_kg")
    var exerciseKg: Int? = null
}