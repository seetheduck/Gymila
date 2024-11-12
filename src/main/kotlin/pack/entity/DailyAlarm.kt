package pack.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Size

@Entity
@Table(name = "daily_alarm")
class DailyAlarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @Size(max = 255)
    @Column(name = "user_id")
    var userId: String? = null

    @Size(max = 255)
    @Column(name = "alarm_description")
    var alarmDescription: String? = null

    @Size(max = 255)
    @Column(name = "alarm_time")
    var alarmTime: String? = null

    @Column(name = "alarm_activate")
    var alarmActivate: Boolean? = null

    @Column(name = "alarm_type")
    var alarmType: Int? = null
}