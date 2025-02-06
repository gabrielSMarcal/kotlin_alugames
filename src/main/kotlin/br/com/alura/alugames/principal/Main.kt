package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.*
import br.com.alura.alugames.servicos.ConsumoApi
import java.util.Scanner

fun main() {

    val leitura = Scanner(System.`in`)
    do {
        println("Digite o ID do jogo que deseja buscar:")
        val busca = leitura.nextLine()

        var meuJogo: Jogo? = null

        val resultado = runCatching {

            val buscaApi = ConsumoApi()
            val meuInfoJogo = buscaApi.buscaJogo(busca)

            meuJogo = Jogo(
                meuInfoJogo.info.title,
                meuInfoJogo.info.thumb)
        }

        resultado.onFailure {
            println("Erro ao buscar o jogo, tente outro ID.")
        }

        resultado.onSuccess {

            println("Deseja inserir uma descrição personalizada? (s/n)")
            val resposta = leitura.nextLine()

            if (resposta.equals("s", true)) {
                println("Digite a descrição personalizada:")
                val descricaoPersonalizada = leitura.nextLine()
                meuJogo?.descricao = descricaoPersonalizada
                println("Descrição inserida: $descricaoPersonalizada")
            } else {
                meuJogo?.descricao = meuJogo?.titulo

            }

            println(meuJogo)
        }

        println("\nDeseja buscar outro jogo? (s/n)")
        val resposta = leitura.nextLine()

    } while (resposta.equals("s", true))

    println("Busca concluida com sucesso")
}