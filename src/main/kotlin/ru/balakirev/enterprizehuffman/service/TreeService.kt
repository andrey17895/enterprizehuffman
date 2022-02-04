package ru.balakirev.enterprizehuffman.service

import org.springframework.stereotype.Service
import ru.balakirev.enterprizehuffman.model.leaf.BaseLeaf
import ru.balakirev.enterprizehuffman.model.leaf.Brunch
import ru.balakirev.enterprizehuffman.model.leaf.Leaf

@Service
class TreeService {

    fun createTree(charFrequencies: List<BaseLeaf>): BaseLeaf {

        val sorted = charFrequencies.sortedBy { it.weight }

        val tree = if (sorted.count() == 1) {
            sorted.first()
        } else {
            createTree(sorted.drop(2) + mergeBrunches(sorted[0], sorted[1]))
        }
        return tree
    }

    fun mergeBrunches(left: BaseLeaf, right: BaseLeaf): BaseLeaf {
        return if (left.weight < right.weight)
            Brunch(left, right)
        else
            Brunch(right, left)
    }

    fun decodeSymbol(symbol: Char, leaf: BaseLeaf): String {
        return when (leaf) {
            is Leaf -> ""
            is Brunch -> {
                if (leaf.left != null && leaf.left.symbols.contains(symbol)) {
                    "0" + decodeSymbol(symbol, leaf.left)
                } else if (leaf.right != null && leaf.right.symbols.contains(symbol)) {
                    "1" + decodeSymbol(symbol, leaf.right)
                } else throw RuntimeException("No symbol '$symbol' in tree")
            }
            else -> throw RuntimeException("No symbol '$symbol' in tree")
        }

    }

    fun decodeText(text: String, root: BaseLeaf): String {
        fun decodeTextAcc(acc: String, remainingText: String, branch: BaseLeaf): String {
            return when (branch) {
                is Leaf -> {
                    if (remainingText.isEmpty()) acc + branch.symbols[0]
                    else decodeTextAcc(acc + branch.symbols[0], remainingText, root)
                }
                is Brunch -> {
                    if (branch.left != null && remainingText.first() == '0')
                        decodeTextAcc(acc, remainingText.drop(1), branch.left)
                    else if (branch.right != null && remainingText.first() == '1')
                        decodeTextAcc(acc, remainingText.drop(1), branch.right)
                    else throw RuntimeException("Incorrect symbol in encoded text")
                }
                else -> throw RuntimeException("Incredible shit")
            }
        }
        return decodeTextAcc("", text, root)
    }

    fun createCodeMap(leaf: BaseLeaf): Map<Char, String> {
        return leaf.symbols.associateWith { decodeSymbol(it, leaf) }
    }

}