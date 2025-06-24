package br.com.alura.alugames.modelo

data class Aluguel(
    val gamer: Gamer,
    val jogo: Jogo,
    val periodo: Periodo) {

    val valorDoAluguel = gamer.plano.obterValor(this)
    var id = 0

    override fun toString(): String {
        val valorFormatado = String.format("%.2f", valorDoAluguel)
        return "Aluguel do ${jogo.titulo} por ${gamer.nome}, pelo valor de R$$valorFormatado\n"
    }
}
