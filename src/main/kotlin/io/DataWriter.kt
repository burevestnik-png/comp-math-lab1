package io

import com.google.gson.Gson
import domain.EquationSystem
import java.io.FileWriter

class DataWriter {
    fun writeData(equationSystem: EquationSystem) {
        val baseJsonResultFileName = "solution-result.json"

        val fileWriter = FileWriter(baseJsonResultFileName)
        Gson().toJson(equationSystem, fileWriter)
        fileWriter.close()
    }
}