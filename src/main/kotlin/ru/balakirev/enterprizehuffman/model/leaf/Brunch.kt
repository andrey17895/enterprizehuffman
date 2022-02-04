package ru.balakirev.enterprizehuffman.model.leaf

class Brunch(
    left: BaseLeaf,
    right: BaseLeaf
) : BaseLeaf(
    left = left,
    right = right,
    weight = left.weight + right.weight,
    symbols = left.symbols + right.symbols
)
