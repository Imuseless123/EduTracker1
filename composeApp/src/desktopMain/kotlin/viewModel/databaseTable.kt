package viewModel

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val ID: Int,
    val Name: String,
    val Gender: Boolean,
    val DOB: LocalDate
)


@Serializable
data class ClassRoom(
    val iD: Int,
    val name: String
    )

@Serializable
data class SummaryPrize(
    var item_id: Int,
    var item_name: String,
)