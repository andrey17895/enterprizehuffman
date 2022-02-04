package ru.balakirev.enterprizehuffman.model.leaf

class Leaf(
    symbol: Char,
    weight: Int
) : BaseLeaf(
    left = null,
    right = null,
    weight = weight,
    symbols = listOf(symbol)
)
