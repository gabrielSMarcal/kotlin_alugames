package br.com.alura.alugames.modelo

import com.google.gson.annotations.Expose

data class Jogo (@Expose val titulo:String,
                 @Expose val capa:String): Recomendavel {

    var descricao:String? = null
    var preco = 0.0
    var id = 0
    private val listaNotas = mutableListOf<Int>()
    override val media: Double
        get() = listaNotas.average()

    override fun recomendar(nota: Int) {
        if (nota < 1 || nota > 10) {
            throw IllegalArgumentException("Nota deve ser entre 1 e 10")
        }
        listaNotas.add(nota)
    }

    constructor(titulo:String, capa:String, preco:Double, descricao:String?, id: Int = 0)
            :this(titulo, capa){
        this.preco = preco
        this.descricao = descricao
        this.id = 0
    }

    override fun toString(): String {

        return "Jogo: \n" +
                "Título: $titulo \n" +
                "Capa: $capa \n" +
                "Preço: R$$preco \n" +
                "Descrição: $descricao \n" +
                "Reputação: $media \n" +
                "ID: $id \n"
    }
}