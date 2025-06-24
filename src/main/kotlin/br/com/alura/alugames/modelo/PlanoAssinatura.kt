package br.com.alura.alugames.modelo

class PlanoAssinatura (
    tipo: String,
    val mensalidade: Double,
    val jogosIncluidos: Int,
    val percentualDescontoReputacao: Double,
    id: Int = 0): Plano(tipo) {

    override fun obterValor(aluguel: Aluguel): Double {

        val totalJogosNoMes = aluguel.gamer.jogosDoMes(
            aluguel.periodo.dataInicial.monthValue).size + 1

        return if (totalJogosNoMes <= jogosIncluidos) {
            0.0
        } else {
            var valorOriginal = super.obterValor(aluguel)

            if (aluguel.gamer.media > 8) {
                valorOriginal -= valorOriginal * percentualDescontoReputacao
            }

            valorOriginal
        }
    }

    override fun toString(): String {
        return "Plano Assinatura: \n" +
                "Tipo: $tipo \n" +
                "ID: $id \n" +
                "Mensalidade: R$$mensalidade \n" +
                "Jogos Incluídos: $jogosIncluidos \n" +
                "Desconto percentual de Reputação: $percentualDescontoReputacao \n"

    }
}
