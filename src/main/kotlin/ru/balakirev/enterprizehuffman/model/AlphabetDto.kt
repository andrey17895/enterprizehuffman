package ru.balakirev.enterprizehuffman.model

class AlphabetDto (
    val id: Long?,
    val name: String,
    val codes: Map<Char, String>,
    val weights: Map<Char, Int>
        )