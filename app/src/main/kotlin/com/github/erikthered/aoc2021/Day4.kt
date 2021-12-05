package com.github.erikthered.aoc2021

fun main() {
    val lines = loadInputFile(4).readLines()

    val draws = lines[0].split(",").map { it.toInt() }
    var boards: List<Board> = lines.slice(2..lines.lastIndex)
        .windowed(5, 6).map { rows ->
            rows.map { row ->
                row.split(" ")
                    .filter { it.isNotEmpty() }
                    .map { tile -> tile.toInt() to false }
                    .toTypedArray()
            }.toTypedArray()
        }

    var part1Complete = false

    game@for (draw in draws) {
        boards = boards.map { board -> updateForRound(board, draw) }
        for((idx, board) in boards.withIndex()) {
            if(!part1Complete && board.isWinner()) {
                println("Board $idx is the first winner")
                println("Part 1: Score ${board.getScore(draw)}")
                part1Complete = true
            }
        }
        if(boards.size == 1 && boards.single().isWinner()) {
            println("Part 2: Score ${boards.single().getScore(draw)}")
            break@game
        }
        boards = boards.filter { !it.isWinner() }
    }
}

typealias Board = Array<Array<Pair<Int, Boolean>>>

fun updateForRound(board: Board, draw: Int): Board {
    return board.map { row ->
        row.map { (tile, marked) ->
            when {
                marked -> tile to true
                tile == draw -> tile to true
                else -> tile to false
            }
        }.toTypedArray()
    }.toTypedArray()
}

fun Board.print() {
    this.forEach { println(it.joinToString(" ")) }
    println()
}

fun Board.isWinner(): Boolean {
    // rows
    this.forEach { row ->
        if (!(row.map { (_, marked) -> marked }.contains(false))) return true
    }

    // columns
    (0..4).map { col ->
        this.map { row -> row[col] }
    }.forEach { col ->
        if (!(col.map { (_, marked) -> marked }.contains(false))) return true
    }

    return false
}

fun Board.getScore(draw: Int): Int {
    val sumUnmarked = this.flatten().filter { (tile, marked) -> !marked }.sumOf { (tile, _) -> tile }
    return draw * sumUnmarked
}