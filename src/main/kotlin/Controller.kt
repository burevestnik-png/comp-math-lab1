import domain.EquationSystem
import io.DataReader
import io.Printer
import services.ComputationService
import services.MatrixTransformer
import java.lang.Exception
import java.lang.NumberFormatException

class Controller(private val appMode: AppMode) {
    private val dataReader = DataReader()
    private val printer = Printer()
    private val computationService = ComputationService()

    fun handle(args: Array<String>) {
        val equationSystem: EquationSystem? = try {
            dataReader.readData(appMode, args)
        } catch (e: Exception) {
            when (e) {
                is ArrayIndexOutOfBoundsException, is NumberFormatException -> {
                    printer.printExampleFormatError(args[1])
                    null
                }
                else -> {
                    TODO()
                }
            }
        }

        println(equationSystem)
        if (equationSystem == null) {
            // TODO
            return
        }
        computationService.solveSystem(equationSystem)
        println(equationSystem)
    }
}