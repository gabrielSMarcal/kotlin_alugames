package br.com.alura.alugames.principal

import java.util.Scanner

import br.com.alura.alugames.modelo.*
import br.com.alura.alugames.servicos.ConsumoApi
import transformarIdade

fun main() {

    val leitura = Scanner(System.`in`)
    val gamer = Gamer.criarGamer(leitura)
    println("Gamer criado com sucesso, confira os dados:")
    println(gamer)
    println("Idade do Gamer: " + gamer.dataNascimento?.transformarIdade())

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
                meuJogo?.descricao = "Não disponível"

            }

            gamer.jogosBuscados.add(meuJogo)
        }

        println("\nDeseja buscar outro jogo? (S/N)")
        val resposta = leitura.nextLine()

    } while (resposta.equals("s", true))

    println("Jogos buscados:")
    println(gamer.jogosBuscados)

    println("\nJogos ordenados por título:")
    gamer.jogosBuscados.sortBy {
        it?.titulo
    }

    gamer.jogosBuscados.forEach {
        println("Título: " + it?.titulo)
    }

    val jogosFiltrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("batman", true) ?: false
    }

    println("\nJogos filtrados por Batman:")
    println(jogosFiltrados)

    println("Deseja excluir algum jogo da lista original? (S/N)")
    val respostaExclusao = leitura.nextLine()

    if (respostaExclusao.equals("s", true)) {

        // Reformulando apenas o título dos jogos, já ordenados por titulo
        gamer.jogosBuscados.forEach {
            println("Título: " + it?.titulo)
        }

        println("\nDigite o índice do jogo que deseja excluir:")
        val indice = leitura.nextInt()
        gamer.jogosBuscados.removeAt(indice)
    }

    println("\nLista atualizada:")
    println(gamer.jogosBuscados)

    println("******************************")
    println("Busca concluida com sucesso")
}