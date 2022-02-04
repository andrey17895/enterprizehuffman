package ru.balakirev.enterprizehuffman.controller

import org.springframework.web.bind.annotation.*
import ru.balakirev.enterprizehuffman.model.AlphabetDto
import ru.balakirev.enterprizehuffman.model.TextDto
import ru.balakirev.enterprizehuffman.service.AlphabetService

@RestController
@RequestMapping("/alphabet")
class HuffmanController(
    val alphabetService: AlphabetService
) {

    @PostMapping
    fun createAlphabet(@RequestBody request: TextDto): AlphabetDto {

        return alphabetService.createAlphabet(request)
    }

    @GetMapping("/{id}")
    fun getAlphabet(@PathVariable id: Long): AlphabetDto {
        return alphabetService.getAlphabet(id)
    }

    @GetMapping
    fun GetAll(): List<AlphabetDto> {
        return alphabetService.getAll()
    }

    @GetMapping("/{id}/encode")
    fun encode(@PathVariable id: Long, @RequestParam text: String): String {
        return alphabetService.encode(id, text)
    }

    @GetMapping("/{id}/decode")
    fun decode(@PathVariable id: Long, @RequestParam text: String): String {
        return alphabetService.decode(id, text)
    }
}