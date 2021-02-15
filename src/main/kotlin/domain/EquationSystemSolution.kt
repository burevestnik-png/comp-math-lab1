package domain

enum class Status {
    FAIL,
    SUCCESS
}

data class EquationSystemSolution(
    val status: Status = Status.FAIL,
    val result: Array<Double>,
    val faults: Array<Double>,
    val iterationCounts: Int,
) {
    constructor(status: Status = Status.FAIL) : this(status,
        emptyArray(),
        emptyArray(),
        0
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EquationSystemSolution

        if (status != other.status) return false
        if (!result.contentEquals(other.result)) return false

        return true
    }

    override fun hashCode(): Int {
        var result1 = status.hashCode()
        result1 = 31 * result1 + result.contentHashCode()
        return result1
    }

}