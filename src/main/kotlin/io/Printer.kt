package io

import domain.EquationSystemSolution
import domain.Status

class Printer {
    fun printGreeting(mode: String) {
        println("""
            Welcome to gauss computation app for system of equations!
            You invoked this app in mode: $mode
            
        """.trimIndent())
    }

    fun printExampleFormatError(argument: String) {
        println("""
            Invalid number of example: $argument
            Possible numbers: [ 1 .. 4 ]
        """.trimIndent())
    }

    fun printInputFormatError(args: Array<String>) {
        println("""
            Invalid input!
            You entered: ${args.contentToString()}
            
            Format should be:
                <AppMode> [ arguments ]
                
            E.g.:
                CONSOLE
                EXAMPLE [ number ]
                JSON [ fileName ]
                
            CONSOLE mode example:
                Enter matrix size: 2
                Enter accuracy [float] (DUE TO COMMA, NO DOT!): 0,01
                Enter factors [float] (totally factors: 4): 2
                4
                5
                1
                Enter right factors [float] (totally factors: 2): 5
                1
        """.trimIndent())

    }

    fun printUnexpectedErrorEmerged(message: String?) {
        println("""
            
            AN UNEXPECTED ERROR EMERGED!
            Error message: $message
            
        """.trimIndent())
    }

    fun printTroublesWithReadingInput() {
        println("""
            Troubles emerged during reading input, closing app...
        """.trimIndent())
    }

    fun printFileNotFound(filename: String) {
        println("""
          
            File with name <$filename> not found!
            Please repeat input
        """.trimIndent())
    }

    fun printDiagonalUnbalanceFound() {
        println("""
            Found diagonal unbalance, trying to repair...
        """.trimIndent())
    }

    fun printImpossibilityOfDiagonalBalance() {
        println("""
            It is impossible to repair the diagonal unbalance!
            The solution of system of equations is unreachable 
            
        """.trimIndent())
    }

    fun printDiagonalUnbalanceRepaired() {
        println("""
            Diagonal unbalance was successfully repaired
            
        """.trimIndent())
    }

    fun printGaussIteration(solutionVector: Array<Double>, iteration: Int) {
        println("Iteration $iteration: ${solutionVector.contentDeepToString()}")
    }

    fun printGaussSolution(solution: EquationSystemSolution) {
        println("""
            
            Result was computed ${solution.status.name}!
            ${if (solution.status == Status.SUCCESS)
            """Solution: ${solution.result.contentDeepToString()}
            Faults: ${solution.faults.contentDeepToString()}
            Iterations: ${solution.iterationCounts}
                """.trimIndent()
                else 
                    ""
            
        }
        """.trimIndent())
    }
}