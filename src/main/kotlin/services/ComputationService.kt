package services

import domain.EquationSystem
import domain.EquationSystemSolution
import domain.Status
import kotlin.math.abs

class ComputationService {
    fun solveSystem(equationSystem: EquationSystem): EquationSystemSolution? {
        if (DiagonalDominanceHandler.isDiagonalDominanceExists(equationSystem)) {
            // TODO
            println("DIAGONAL PROBLEM")
            try {
                DiagonalDominanceHandler.removeDiagonalDominance(equationSystem)
            } catch (e: Exception) {
                // TODO
                return EquationSystemSolution(matrixSize = equationSystem.matrixSize)
            }
        }

        val transformedEquationSystem = MatrixTransformer.transform(equationSystem)

        return null
    }

    private fun isAccuracyAchieved(
        newSolutionVector: Array<Double>,
        oldSolutionVector: Array<Double>,
        accuracy: Double,
    ): Boolean {
        val solutionDifferenceArray = newSolutionVector.zip(oldSolutionVector) {new: Double, old: Double -> abs(new - old) }.toTypedArray()
        return solutionDifferenceArray.maxOrNull()!! < accuracy
    }

    private fun startIterationCalculation(equationSystem: EquationSystem): EquationSystemSolution {
        var iterationCounter = 0
        val initialSolutionVector = Array(equationSystem.matrixSize) { 0.0 }

        return EquationSystemSolution(Status.FAIL, 4)
    }
}