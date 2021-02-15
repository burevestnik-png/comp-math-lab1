package services

import domain.EquationSystem
import domain.EquationSystemSolution
import domain.Status
import io.Printer
import kotlin.math.abs

class GaussSolver {
    companion object {
        private val printer = Printer()

        private fun isAccuracyAchieved(
            newSolutionVector: Array<Double>,
            oldSolutionVector: Array<Double>,
            accuracy: Double,
        ): Boolean {
            val solutionDifferenceArray =
                newSolutionVector.zip(oldSolutionVector) { new: Double, old: Double -> abs(new - old) }.toTypedArray()
            return solutionDifferenceArray.maxOrNull()!! < accuracy
        }

        fun solve(
            equationSystem: EquationSystem,
            initialSolutionVector: Array<Double> = Array(equationSystem.matrixSize) { 0.0 },
        ): EquationSystemSolution {
            var iterationCounter = 0
            val newSolutionVector = initialSolutionVector.clone()
            var oldSolutionVector: Array<Double>

            do {
                oldSolutionVector = newSolutionVector.clone()
                newSolutionVector.forEachIndexed { i: Int, _ ->
                    updateSolutionVector(i, equationSystem, newSolutionVector)
                }
                iterationCounter++
                printer.printGaussIteration(newSolutionVector, iterationCounter)
            } while (!isAccuracyAchieved(newSolutionVector, oldSolutionVector, equationSystem.accuracy))

            return EquationSystemSolution(Status.SUCCESS, newSolutionVector, generateFaults(newSolutionVector, oldSolutionVector), iterationCounter )
        }

        private fun generateFaults(newSolutionVector: Array<Double>, oldSolutionVector: Array<Double>): Array<Double> {
            return newSolutionVector.zip(oldSolutionVector) {new: Double, old: Double -> abs(new - old) }.toTypedArray()
        }

        private fun updateSolutionVector(index: Int, equationSystem: EquationSystem, solutionVector: Array<Double>) {
            solutionVector[index] = equationSystem.rightFactors[index] +
                    solutionVector.mapIndexed { j: Int, solution: Double ->
                        equationSystem.factors[index][j] * solution
                    }.sumByDouble { it }
        }
    }
}