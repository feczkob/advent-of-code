package common

import java.io.File

fun readFileAsLines(fileName: String): List<String> = File(fileName).readLines()

fun readFileAsText(fileName: String): String = File(fileName).inputStream().readAllBytes().toString(Charsets.UTF_8)