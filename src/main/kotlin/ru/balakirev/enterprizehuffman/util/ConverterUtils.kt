package ru.balakirev.enterprizehuffman.util

import ru.balakirev.enterprizehuffman.entity.Alphabet
import ru.balakirev.enterprizehuffman.model.AlphabetDto

fun Alphabet.toDto(): AlphabetDto {
    return AlphabetDto(
        id = this.id,
        name = this.name,
        codes = this.codes,
        weights = this.weights
    )
}