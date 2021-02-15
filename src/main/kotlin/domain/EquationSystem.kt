package domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EquationSystem(
    @SerializedName("matrix-size")
    val matrixSize: Int,
    val accuracy: Double,
    val factors: Array<Array<Double>>,
    @SerializedName("right-factors")
    val rightFactors: Array<Double>,
    @Expose(deserialize = false)
    val result: EquationSystemSolution? = null
) {
    fun copy(
        matrixSize: Int = this.matrixSize,
        accuracy: Double = this.accuracy,
        factors: Array<Array<Double>> = this.factors,
        rightFactors: Array<Double> = this.rightFactors
    ) = EquationSystem(matrixSize, accuracy, factors, rightFactors)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EquationSystem

        if (matrixSize != other.matrixSize) return false
        if (accuracy != other.accuracy) return false
        if (!factors.contentEquals(other.factors)) return false
        if (!rightFactors.contentEquals(other.rightFactors)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = matrixSize
        result = (31 * result + accuracy).toInt()
        result = 31 * result + factors.contentHashCode()
        result = 31 * result + rightFactors.contentHashCode()
        return result
    }

    override fun toString(): String {
        return "EquationSystem(matrixSize=$matrixSize, accuracy=$accuracy, factors=${factors.contentDeepToString()}, rightFactors=${rightFactors.contentToString()}, result=${result})"
    }
}
