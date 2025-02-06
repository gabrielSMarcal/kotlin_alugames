package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.*
import br.com.alura.alugames.servicos.ConsumoApi
import java.util.Scanner

fun main() {

    val leitura = Scanner(System.`in`)
    val gamer = Gamer.criarGamer(leitura)
    println("Gamer criado com sucesso, confira os dados:")
    println(gamer)

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

            println("Deseja inserir uma descrição personalizada? (S/N)")
            val resposta = leitura.nextLine()

            if (resposta.equals("s", true)) {
                println("Digite a descrição personalizada:")
                val descricaoPersonalizada = leitura.nextLine()
                meuJogo?.descricao = descricaoPersonalizada
                println("Descrição inserida: $descricaoPersonalizada")
            } else {
                meuJogo?.descricao = meuJogo?.titulo

            }

            gamer.jogosBuscados.add(meuJogo)
        }

        println("\nDeseja buscar outro jogo? (S/N)")
        val resposta = leitura.nextLine()

    } while (resposta.equals("s", true))

    println("Jogos buscados:")
    println(gamer.jogosBuscados)

    println("Busca concluida com sucesso")
}