package io

import AppMode
import com.google.gson.Gson
import domain.EquationSystem
import domain.utils.ResourceExamples
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*

class DataReader {
    fun readData(appMode: AppMode, args: Array<String>): EquationSystem? {
        return when (appMode) {
            AppMode.EXAMPLE -> {
                readDataFromResource(ResourceExamples.values()[args[1].toInt() - 1].source)
            }
            AppMode.JSON -> {
                readDataFromFile(args[1])
            }
            AppMode.CONSOLE -> {
                readFromConsole()
            }
        }
    }

    private fun readFromConsole(): EquationSystem? {
        val matrixSize: Int
        val accuracy: Double
        val factors: Array<Array<Double>>
        val rightFactors: Array<Double>

        try {
            with(Scanner(System.`in`)) {
                print("Enter matrix size [integer]: ")
                matrixSize = nextInt()

                print("Enter accuracy [float] (DUE TO COMMA, NO DOT!): ")
                accuracy = nextDouble()

                print("Enter factors [float] (totally factors: ${matrixSize * matrixSize}): ")
                factors = Array(matrixSize) { Array(matrixSize) { 0.0 } }
                for (i in 0 until matrixSize) {
                    for (j in 0 until matrixSize) {
                        print("Enter next factor: ")
                        factors[i][j] = nextDouble()
                    }
                }

                print("Enter right factors [float] (totally factors: ${matrixSize}): ")
                rightFactors = Array(matrixSize) { 0.0}
                for (i in 0 until matrixSize) {
                    print("Enter next right factor: ")
                    rightFactors[i] = nextDouble()
                }

                println("Input was successful done!\n")
            }
        } catch (e: Exception) {
            when (e) {
                is InputMismatchException -> {
                    printInputError()
                }
            }

            return null
        }

        return EquationSystem(matrixSize, accuracy, factors, rightFactors)
    }

    private fun readDataFromFile(filename: String): EquationSystem? {
        val fileInputStream = FileInputStream(File(filename))
        val bufferedReader = BufferedReader(InputStreamReader(fileInputStream))
        return Gson().fromJson(bufferedReader, EquationSystem::class.java)
    }

    private fun readDataFromResource(resource: String): EquationSystem? {
        return Gson().fromJson(DataReader::class.java.getResource(resource).readText(), EquationSystem::class.java)
    }

    private fun printInputError() {
        println("""
            Incorrect input!
        """.trimIndent())
    }

}