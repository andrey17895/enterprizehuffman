package ru.balakirev.enterprizehuffman.service

import org.springframework.stereotype.Service
import ru.balakirev.enterprizehuffman.entity.Alphabet
import ru.balakirev.enterprizehuffman.util.toDto
import ru.balakirev.enterprizehuffman.model.AlphabetDto
import ru.balakirev.enterprizehuffman.model.TextDto
import ru.balakirev.enterprizehuffman.model.leaf.Leaf
import ru.balakirev.enterprizehuffman.repository.AlphabetRepository

@Service
class AlphabetService(
    val alphabetRepository: AlphabetRepository,
    val treeService: TreeService,
) {
    fun createAlphabet(request: TextDto): AlphabetDto {

        if (request.text.isEmpty()) throw RuntimeException("Nothing to convert")

        val charWeights = request.text.groupBy { it }
            .map { entry -> entry.key to entry.value.count() }.toMap()

        val leafsList = charWeights.map { Leaf(it.key, it.value) }

        val huffmanTree = treeService.createTree(leafsList)

        val codeMap = treeService.createCodeMap(huffmanTree)

        val alphabet = Alphabet(null, request.name, codeMap, charWeights)

        val entity = alphabetRepository.save(alphabet)

        return entity.toDto()
    }

    fun getAlphabet(id: Long): AlphabetDto {
        return alphabetRepository.getById(id).toDto()
    }

    fun getAll(): List<AlphabetDto> {
        return alphabetRepository.findAll().map { it.toDto() }
    }

    fun encode(alphabetId: Long, text: String): String {
        val codeMap = alphabetRepository.getById(alphabetId).codes
        return text.map { codeMap[it].orEmpty() }.joinToString(separator = "") { it }
    }

    fun decode(alphabetId: Long, text: String): String {
        val leafList = alphabetRepository.getById(alphabetId).weights.map { Leaf(it.key, it.value) }
        val tree = treeService.createTree(leafList)
        return treeService.decodeText(text, tree)
    }
}