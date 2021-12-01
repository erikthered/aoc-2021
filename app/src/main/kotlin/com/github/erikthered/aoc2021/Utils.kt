package com.github.erikthered.aoc2021

import java.io.File

fun loadInputFile(day: Int) = File(ClassLoader.getSystemResource("input/day$day.txt").file)