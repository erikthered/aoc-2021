package com.github.erikthered.aoc2021

fun main() {
    val lines = loadInputFile(8).readLines()

    var toDigitSimple: (String) -> Int? = { str ->
        when (str.length) {
            2 -> 1
            4 -> 4
            3 -> 7
            7 -> 8
            else -> null
        }
    }

    var outputs = lines.map { line ->
        val (_, o) = line.split(" | ")
        val output = o.split(" ").mapNotNull { toDigitSimple(it) }
        output
    }

    println("Part 1: ${outputs.flatten().size}")
}
