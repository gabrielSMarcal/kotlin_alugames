package br.com.alura.alugames.dados

sealed class PlanoEntity(val tipo :String)

class PlanoAvulsoEntity(tipo: String): PlanoEntity(tipo)

class PlanoAssunaturaEntity(
    tipo: String,
    val mendalidade: Double,
    val jogosIncluidos: Int,
    val percentualDescontoReputacao: Double): PlanoEntity(tipo)