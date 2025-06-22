package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Periodo
import br.com.alura.alugames.servicos.ConsumoApi
import java.time.LocalDate
import java.time.Period

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

    val periodo = Periodo(LocalDate.now(), LocalDate.now().plusDays(7))

    val alguel = gamerCaroline.alugaJogo(jogoResidentVillage, periodo)
    println(alguel)
}