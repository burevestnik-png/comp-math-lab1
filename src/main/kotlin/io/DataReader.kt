package io

import AppMode
import com.google.gson.Gson
import domain.EquationSystem
import domain.utils.ResourceExamples

class DataReader {
    fun readData(appMode: AppMode, args: Array<String>): EquationSystem?  {
        return when (appMode) {
            AppMode.EXAMPLE -> {
                readDataFromResource(ResourceExamples.values()[args[1].toInt() - 1].source)
            }
            else -> {
                null
            }
        }
    }

    private fun readDataFromResource(resource: String): EquationSystem? {
        return Gson().fromJson(DataReader::class.java.getResource(resource).readText(), EquationSystem::class.java)
    }
}