package com.github.erikthered.aoc2021

fun main() {
    val lines = loadInputFile(8).readLines()

    val toDigitSimple: (String) -> Int? = { str ->
        when (str.length) {
            2 -> 1
            4 -> 4
            3 -> 7
            7 -> 8
            else -> null
        }
    }

    val outputs = lines.map { line ->
        val (_, o) = line.split(" | ")
        val output = o.split(" ").mapNotNull { toDigitSimple(it) }
        output
    }

    println("Part 1: ${outputs.flatten().size}")

    val total = lines.map { line ->

        val (left, right) = line.split(" | ")

        val mappings = mutableMapOf<String, Int>()
        val signals = left.split(" ")
        val output = right.split(" ")

        // First pass
        signals.forEach {
            if (!mappings.containsKey(it)) {
                val digit = toDigitSimple(it)
                if (digit != null) {
                    mappings[it] = digit
                }
            }
        }

        // Second pass
        signals.forEach { input ->
            val flippedMappings = mappings.flip()
            if (!mappings.containsKey(input)) {
                when {
                    // The check between 9 and 0 is wrong
                    !flippedMappings.containsKey(9) && input.length == 6 && input.toSet().containsAll(flippedMappings[4]!!.toSet()) -> mappings[input] = 9
                    !flippedMappings.containsKey(0) && input.length == 6 && input.toSet().containsAll(flippedMappings[1]!!.toSet()) -> mappings[input] = 0
                    !flippedMappings.containsKey(3) && input.length == 5 && input.toSet().containsAll(flippedMappings[7]!!.toSet()) -> mappings[input] = 3
                }
            }
        }

        // Third pass
        signals.forEach { input ->
            if (!mappings.containsKey(input)) {
                if (input.length == 6) mappings[input] = 6
            }
        }

        // Fourth pass
        signals.forEach { input ->
            if (!mappings.containsKey(input)) {
                val flippedMappings = mappings.flip()
                if (input.length == 5 && flippedMappings[6]!!.toSet().containsAll(input.toSet())) mappings[input] = 5
            }
        }

        // Final pass
        signals.forEach { input ->
            if (!mappings.containsKey(input)) {
                mappings[input] = 2
            }
        }

        mappings.forEach {
            println("${it.key}: ${it.value}")
        }
        println(mappings)

        val digits = output.map { code -> mappings.entries.find { it.key.toSet() == code.toSet() }!!.value }.joinToString("")
        println(digits)
        digits
    }.sumOf { it.toInt() }

    println("Part 2: $total")
}

fun <K, V> Map<K, V>.flip(): Map<V, K> = this.entries.associate { it.value to it.key }
