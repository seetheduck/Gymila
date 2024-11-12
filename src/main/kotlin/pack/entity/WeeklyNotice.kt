package pack.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import java.time.Instant

@Entity
@Table(name = "weekly_notice")
class WeeklyNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @Size(max = 255)
    @Column(name = "notice_title")
    var noticeTitle: String? = null

    @Lob
    @Column(name = "notice_content")
    var noticeContent: String? = null

    @Column(name = "notice_time")
    var noticeTime: Instant? = null

    @Size(max = 255)
    @Column(name = "notice_day")
    var noticeDay: String? = null

    @Column(name = "notice_activate")
    var noticeActivate: Boolean? = null
}