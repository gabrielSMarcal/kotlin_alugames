package br.com.alura.alugames.modelo

import java.time.LocalDate
import java.time.Period

data class Aluguel(
    val gamer: Gamer,
    val jogo: Jogo,
    val periodo: Periodo) {

    val valorDoAluguel = jogo.preco * periodo.emDias

    override fun toString(): String {
        val valorFormatado = String.format("%.2f", valorDoAluguel)
        return "Aluguel do ${jogo.titulo} por ${gamer.nome}, pelo valor de R$$valorFormatado\n"
    }
}
