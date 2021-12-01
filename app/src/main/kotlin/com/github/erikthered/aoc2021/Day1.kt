package com.github.erikthered.aoc2021

fun main() {
    val file = loadInputFile(1)

    // Part 1
    file.useLines { lines ->
        lines
            .map { it.toInt() }
            .windowed(2)
            .count { it.component2() > it.component1() }
            .also { println("Part 1: $it") }
    }

    // Part 2
    file.useLines { lines ->
        lines
            .map { it.toInt() }
            .windowed(3) { it.sum() }
            .windowed(2)
            .count { it.component2() > it.component1() }
            .also { println("Part 2: $it") }
    }
}