package com.github.erikthered.aoc2021

import kotlin.math.absoluteValue

fun main() {
    val lines = loadInputFile(3).readLines()

    // Part 1
    val gamma = (0..lines[0].lastIndex).map { idx ->
        determineMostCommonBit(lines, idx)
    }.joinToString("").toInt(2)
    println("Gamma: $gamma")

    val epsilon = (0..lines[0].lastIndex).map { idx ->
        (determineMostCommonBit(lines, idx).digitToInt() - 1).absoluteValue
    }.joinToString("").toInt(2)
    println("Epsilon: $epsilon")

    println("Power consumption: ${gamma * epsilon}")

    // Part 2
    var o2 = lines
    (0..o2[0].lastIndex).forEach { idx ->
        val keep = determineMostCommonBit(o2, idx)
        o2 = o2.filter { it[idx] == keep }
    }
    val o2Rating = o2.single().toInt(2)
    println("O2 Rating: $o2Rating")

    var co2 = lines
    (0..co2[0].lastIndex).forEach { idx ->
        if(co2.size > 1) {
            val keep = determineMostCommonBit(co2, idx)
            co2 = co2.filter { it[idx] != keep }
        }
    }
    val co2Rating = co2.single().toInt(2)
    println("CO2 Rating: $co2Rating")

    println("Life support rating: ${o2Rating * co2Rating}")
}

fun determineMostCommonBit(lines: List<String>, col: Int): Char {
    var tracker = 0
    lines.forEach {
        if(it[col] == '1') tracker += 1 else tracker -= 1
    }
    return if(tracker >= 0) '1' else '0'
}