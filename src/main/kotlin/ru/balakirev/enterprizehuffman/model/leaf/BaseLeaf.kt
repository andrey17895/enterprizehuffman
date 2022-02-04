package ru.balakirev.enterprizehuffman.model.leaf

abstract class BaseLeaf(
    val left: BaseLeaf?,
    val right: BaseLeaf?,
    val weight: Int,
    val symbols: List<Char>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BaseLeaf

        if (left != other.left) return false
        if (right != other.right) return false
        if (weight != other.weight) return false
        if (symbols != other.symbols) return false

        return true
    }

    override fun hashCode(): Int {
        var result = left?.hashCode() ?: 0
        result = 31 * result + (right?.hashCode() ?: 0)
        result = 31 * result + weight
        result = 31 * result + symbols.hashCode()
        return result
    }
}
