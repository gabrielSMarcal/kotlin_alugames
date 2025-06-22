package br.com.alura.alugames.principal

import br.com.alura.alugames.servicos.ConsumoApi

fun main() {

    val consumo = ConsumoApi()
    val listaGamers = consumo.buscaGamers()
    val listaJogoJson = consumo.buscaJogoJson()


//    println(listaGamers)
//    println(jogoApi)

    val gamerCaroline = listaGamers.get(3)
    val jogoResidentVillage = listaJogoJson.get(10)

    println(gamerCaroline)
    println(jogoResidentVillage)

    val alguel = gamerCaroline.alugaJogo(jogoResidentVillage)
    println(alguel)
}