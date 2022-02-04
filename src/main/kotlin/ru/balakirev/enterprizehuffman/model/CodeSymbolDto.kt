package ru.balakirev.enterprizehuffman.model

data class CodeSymbolDto (
    val id: Long?,
    val symbol: Char,
    val weight: Int,
    val code: String
        ) {
    constructor(symbol: Char, weight: Int, code: String) : this(null, symbol, weight, code)
}