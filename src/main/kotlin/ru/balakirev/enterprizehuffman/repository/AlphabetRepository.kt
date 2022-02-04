package ru.balakirev.enterprizehuffman.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.balakirev.enterprizehuffman.entity.Alphabet

@Repository
interface AlphabetRepository : JpaRepository<Alphabet, Long> {
    fun save(entity: Alphabet): Alphabet
}