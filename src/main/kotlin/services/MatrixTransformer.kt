package services

import domain.EquationSystem

class MatrixTransformer {
    companion object {
        fun transform(equationSystem: EquationSystem): EquationSystem {
            return equationSystem.copy(rightFactors = transformRightFactors(equationSystem),
                factors = transformMainFactors(equationSystem))
        }

        private fun transformMainFactors(equationSystem: EquationSystem): Array<Array<Double>> {
            return equationSystem.factors.mapIndexed { i: Int, line: Array<Double> ->
                line.mapIndexed { j: Int, factor: Double ->
                    if (i == j) 0.0 else (-1) * factor / line[i]
                }.toTypedArray()
            }.toTypedArray()
        }

        private fun transformRightFactors(equationSystem: EquationSystem): Array<Double> {
            val diagonalElements: List<Double> =
                equationSystem.factors.mapIndexed { index: Int, line: Array<Double> -> line[index] }
            val adductedRightFactors =
                equationSystem.rightFactors.zip(diagonalElements) { a: Double, b: Double -> a / b }
            return adductedRightFactors.toTypedArray()
        }
    }
}