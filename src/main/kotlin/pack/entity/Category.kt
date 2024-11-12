package pack.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Size

@Entity
@Table(name = "category")
class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @Column(name = "program_category_no")
    var programCategoryNo: Int? = null

    @Column(name = "gfc_category_no")
    var gfcCategoryNo: Int? = null

    @Size(max = 255)
    @Column(name = "exercise_type")
    var exerciseType: String? = null

    @Size(max = 255)
    @Column(name = "category_name")
    var categoryName: String? = null
}