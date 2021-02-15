package services

import domain.EquationSystem
import kotlin.math.abs

class DiagonalDominanceHandler {
    companion object {
        fun isDiagonalDominanceExists(equationSystem: EquationSystem): Boolean {
            var areAllDiagFactorsGreaterNotStrong = 0 // условие преобладания диагональных элементов
            var isAtLeastOneDiagFactorGreater = false // хотя бы одно уравнение должно выполняться строго

            equationSystem.factors.forEachIndexed { index, lineFactors ->
                val diagonalFactor = abs(lineFactors[index])
                val lineSum = lineFactors.sumByDouble { abs(it) } - diagonalFactor
                when {
                    diagonalFactor >= lineSum -> areAllDiagFactorsGreaterNotStrong++
                    diagonalFactor > lineSum -> isAtLeastOneDiagFactorGreater = true
                }
            }

            return !((areAllDiagFactorsGreaterNotStrong == equationSystem.factors.size) && isAtLeastOneDiagFactorGreater)
        }

        fun removeDiagonalDominance(equationSystem: EquationSystem) {
            val maxFactorIndices = findMaxFactorIndices(equationSystem)

            for (i in equationSystem.factors.indices) {
                for (j in equationSystem.factors.indices) {
                    if (i != j && maxFactorIndices[i] == maxFactorIndices[j]) {
                        // TODO
                        throw Exception("Diagonal dominance is unreachable")
                    }
                }
            }

            sortEquations(equationSystem, maxFactorIndices)
        }

        private fun findMaxFactorIndices(equationSystem: EquationSystem): List<Int> {
            return equationSystem.factors.map { it.indexOf(it.maxOf { factor -> abs(factor) }) }
        }

        private fun sortEquations(equationSystem: EquationSystem, maxFactorIndices: List<Int>) {
            equationSystem.factors.sortBy { it.indexOf(it.maxOf { factor: Double -> abs(factor) }) }

            val rightFactorsCopy = equationSystem.rightFactors.clone()
            equationSystem.rightFactors.forEachIndexed { index, _ ->
                equationSystem.rightFactors[maxFactorIndices[index]] = rightFactorsCopy[index]
            }
        }
    }
}