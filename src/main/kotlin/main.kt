import io.Printer

enum class AppMode {
    CONSOLE,
    JSON,
    EXAMPLE
}

fun main(args: Array<String>) {
    val printer = Printer()
    if (args.size == 1 && args[0] == AppMode.CONSOLE.name) {
        printer.printGreeting(args[0])
        val controller = Controller(AppMode.CONSOLE)
        controller.handle(args)
        return
    }

    if (args.size == 2) {
        when (args[0]) {
            AppMode.EXAMPLE.name -> {
                printer.printGreeting(args[0])
                val controller = Controller(AppMode.EXAMPLE)
                controller.handle(args)
                return
            }
            AppMode.JSON.name -> {
                printer.printGreeting(args[0])
                val controller = Controller(AppMode.JSON)
                controller.handle(args)
                return
            }
        }
    }

    printer.printInputFormatError(args)
}
