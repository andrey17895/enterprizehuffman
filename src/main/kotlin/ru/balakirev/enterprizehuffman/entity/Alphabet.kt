package ru.balakirev.enterprizehuffman.entity

import lombok.Data
import ru.balakirev.enterprizehuffman.model.CodeSymbolDto
import javax.persistence.*

@Entity
@Table(name = "alphabet")
class Alphabet(
    @Id
    @GeneratedValue
    var id: Long? = null,
    var name: String,
    @ElementCollection
    var codes: Map<Char, String>,
    @ElementCollection
    var weights: Map<Char, Int>
)