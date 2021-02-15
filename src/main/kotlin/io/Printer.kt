package io

class Printer {
    fun printGreeting() {
        println("Welcome to gauss computation app for system of equations.")
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
        """.trimIndent())

    }
}