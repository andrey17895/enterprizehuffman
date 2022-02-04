package ru.balakirev.enterprizehuffman.service

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito
import ru.balakirev.enterprizehuffman.model.leaf.BaseLeaf
import ru.balakirev.enterprizehuffman.model.leaf.Brunch
import ru.balakirev.enterprizehuffman.model.leaf.Leaf

internal class TreeServiceTest {

    private val treeService: TreeService = TreeService()

    private val testList: List<BaseLeaf> = listOf(
        Leaf('a', 10),
        Leaf('b', 4),
        Leaf('c', 8),
        Leaf('d', 3)
    )

    private val testTree = Brunch(
        Leaf('a', 10),
        Brunch(
            Brunch(
                Leaf('d', 3),
                Leaf('b', 4)
            ),
            Leaf('c', 8)
        )
    )

    @Test
    fun createTree() {
        val resultTree = treeService.createTree(testList)
        assertEquals(resultTree, testTree)
    }
}