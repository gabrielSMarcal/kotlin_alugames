package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Gamer

fun main() {

    val gamer1 = Gamer("Gabriel", "gabriel@email.com")
    println(gamer1)

    val gamer2 = Gamer(
        "Marcelo",
        "marcelo@email.com",
        "01/01/1990",
        "marcelo963")
    println(gamer2)

    gamer1.let {
        it.dataNascimento = "01/01/1995"
        it.usuario = "yhamerith"
        it.idInterno = "yhamerith123"
    }
    println(gamer1)
}