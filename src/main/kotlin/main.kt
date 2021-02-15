import io.Printer

enum class AppMode {
    CONSOLE,
    JSON,
    EXAMPLE
}

fun main(args: Array<String>) {
    val printer = Printer().also { it.printGreeting() }
    if (args.size == 1 && args[0] == AppMode.CONSOLE.name) {
        // logic
        return
    }

    if (args.size == 2) {
        when (args[0]) {
            AppMode.EXAMPLE.name -> {
                val controller = Controller(AppMode.EXAMPLE)
                controller.handle(args)

                return
            }
            AppMode.JSON.name -> {

                return
            }
        }
    }

    printer.printInputFormatError(args)
}
