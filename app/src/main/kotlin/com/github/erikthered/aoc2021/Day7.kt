package com.github.erikthered.aoc2021

import kotlin.math.abs

fun main() {
    val input = loadInputFile(7).readText()
    val sortedPositions = input.split(",").map { it.toInt() }.sorted()
    val solution1 = (sortedPositions.minOf { it }..sortedPositions.maxOf { it })
        .map { position -> position to computeFuelUsageFixed(sortedPositions, position) }
        .minByOrNull { it.second }!!
    println(
        """
        Part 1 Solution:
        Position: ${solution1.first}
        Fuel: ${solution1.second}
        """.trimIndent()
    )
    println()
    val solution2 = (sortedPositions.minOf { it }..sortedPositions.maxOf { it })
        .map { position -> position to computeFuelUsageCompounding(sortedPositions, position) }
        .minByOrNull { it.second }!!
    println(
        """
        Part 2 Solution:
        Position: ${solution2.first}
        Fuel: ${solution2.second}
        """.trimIndent()
    )
}

fun computeFuelUsageFixed(positions: List<Int>, dest: Int): Int {
    return positions.sumOf { abs(it - dest) }
}

fun computeFuelUsageCompounding(positions: List<Int>, dest: Int): Int {
    return positions.sumOf { (1..abs(it - dest)).sum() }
}