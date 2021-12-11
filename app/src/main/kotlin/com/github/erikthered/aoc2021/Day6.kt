package com.github.erikthered.aoc2021

fun main() {
    val input = loadInputFile(6).readText()
    var fish = input.split(",").map(String::toInt)

    (1..80).forEach { day ->
        val newFish = mutableListOf<Int>()
        fish = fish.fold(mutableListOf<Int>()) { acc, current ->
            when (current) {
                0 -> { acc.add(6); newFish.add(8) }
                else -> acc.add(current - 1)
            }
            acc
        } + newFish
    }
    println("Part 1: ${fish.size}")

    fish = input.split(",").map(String::toInt)

    val groups = mutableMapOf<Int, Long>()
    (0..8).forEach { groups[it] = 0 }
    fish.forEach { groups[it] = groups[it]?.plus(1L)!! }
    repeat(256) {
        val birth = groups[0]!!
        (0..7).forEach {
            groups[it] = groups[it+1]!!
        }
        groups[6] = groups[6]!!.plus(birth)
        groups[8] = birth
    }
    println("Part 2: ${groups.values.sum()}")
}