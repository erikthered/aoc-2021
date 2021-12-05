package com.github.erikthered.aoc2021

fun main() {
    val inputLines = loadInputFile(5).readLines()

    val lines = inputLines.map { line ->
        val (start, end) = line.split(" -> ")
        Line(
            start = start.split(",").let { (x, y) -> Point(x.toInt(), y.toInt()) },
            end = end.split(",").let { (x, y) -> Point(x.toInt(), y.toInt()) }
        )
    }

    val overlappingPoints = lines
        .filter { it.isHorizontal() || it.isVertical() }
        .map { it.getPoints() }
        .flatten()
        .fold(mutableMapOf<Point, Int>()) { acc, point ->
            acc.putIfAbsent(point, 0)
            acc.computeIfPresent(point) { _, v -> v.inc() }
            acc
        }.filterValues { it > 1 }

    println("Part 1: Number of overlapping points: ${overlappingPoints.keys.count()}")

    val overlappingPointsWithDiagonal = lines
        .map { it.getPoints() }
        .flatten()
        .fold(mutableMapOf<Point, Int>()) { acc, point ->
            acc.putIfAbsent(point, 0)
            acc.computeIfPresent(point) { _, v -> v.inc() }
            acc
        }.filterValues { it > 1 }

    println("Part 2: Number of overlapping points: ${overlappingPointsWithDiagonal.size}")
}

data class Point(val x: Int, val y: Int)

class Line(val start: Point, val end: Point) {
    fun isVertical() = start.x == end.x
    fun isHorizontal() = start.y == end.y

    fun yValues() = if (start.y < end.y) (start.y..end.y) else (end.y..start.y).reversed()
    fun xValues() = if (start.x < end.x) (start.x..end.x) else (end.x..start.x).reversed()

    fun getPoints() = when {
        this.isVertical() -> this.yValues().map { y -> Point(this.start.x, y) }
        this.isHorizontal() -> this.xValues().map { x -> Point(x, this.start.y) }
        else -> this.xValues().zip(this.yValues()).map { (x, y) -> Point(x, y) }
    }
}