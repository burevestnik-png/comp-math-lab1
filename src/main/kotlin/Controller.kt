import domain.EquationSystem
import io.DataReader
import io.DataWriter
import io.Printer
import services.ComputationService
import java.io.FileNotFoundException

class Controller(private val appMode: AppMode) {
    private val dataReader = DataReader()
    private val printer = Printer()
    private val computationService = ComputationService()
    private val dataWriter = DataWriter()

    fun handle(args: Array<String>) {
        val equationSystem: EquationSystem? = try {
            dataReader.readData(appMode, args)
        } catch (e: Exception) {
            when (e) {
                is ArrayIndexOutOfBoundsException, is NumberFormatException -> {
                    printer.printExampleFormatError(args[1])
                    null
                }
                is FileNotFoundException -> {
                    printer.printFileNotFound(args[1])
                    null
                }
                else -> {
                    printer.printUnexpectedErrorEmerged(e.message)
                    null
                }
            }
        }

        val solution = equationSystem?.let {
            equationSystem.print()
            computationService.solveSystem(it)
        } ?: run {
            printer.printTroublesWithReadingInput()
            return
        }
        printer.printGaussSolution(solution).also { equationSystem.result = solution }

        dataWriter.writeData(equationSystem)
    }
}