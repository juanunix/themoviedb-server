package com.juansanz.backend.data.server

import java.io.BufferedReader
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Paths

fun readJsonFile(jsonFile: String): String {

    val classLoader = Thread.currentThread().contextClassLoader
    val resource =
        classLoader.getResource(jsonFile) ?: throw IllegalArgumentException("Archivo no encontrado: $jsonFile")
    val jsonFilePath = Paths.get(resource.toURI())
    var bufferedReader: BufferedReader? = null

    try {
        bufferedReader = File(jsonFilePath.toString()).bufferedReader(StandardCharsets.UTF_8)

        val text = StringBuilder()
        var line: String?

        do {
            line = bufferedReader.readLine()
            line?.let { text.append(it) }
        } while (line != null)

        return text.toString()

    } finally {
        bufferedReader?.close()
    }
}

