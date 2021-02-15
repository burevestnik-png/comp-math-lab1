package services

import domain.EquationSystem
import domain.EquationSystemSolution
import io.Printer

class ComputationService {
    private val printer = Printer()

    fun solveSystem(equationSystem: EquationSystem): EquationSystemSolution {
        if (DiagonalDominanceHandler.isDiagonalDominanceExists(equationSystem)) {
            printer.printDiagonalUnbalanceFound()
            try {
                DiagonalDominanceHandler.removeDiagonalDominance(equationSystem)
            } catch (e: Exception) {
                printer.printImpossibilityOfDiagonalBalance()
                return EquationSystemSolution()
            }
            printer.printDiagonalUnbalanceRepaired()
        }

        val transformedEquationSystem = MatrixTransformer.transform(equationSystem)
        return GaussSolver.solve(transformedEquationSystem)
    }
}