package pack.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import java.time.Instant

@Entity
@Table(name = "wearable_data")
class WearableDatum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @Size(max = 255)
    @Column(name = "user_id")
    var userId: String? = null

    @ColumnDefault("current_timestamp()")
    @Column(name = "record_date")
    var recordDate: Instant? = null

    @Column(name = "sleep_duration")
    var sleepDuration: Int? = null

    @Column(name = "steps_count")
    var stepsCount: Int? = null
}