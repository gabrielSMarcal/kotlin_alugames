package br.com.alura.alugames.modelo

import br.com.alura.alugames.modelo.PlanoAvulso
import java.util.Scanner
import kotlin.random.Random

data class Gamer(var nome: String, var email: String): Recomendavel {

    var dataNascimento: String? = null
    var usuario: String? = null
        set(value) {
            field = value
            if (idInterno.isNullOrBlank()) {
                criarIdInterno()
            }
        }
    var id = 0
    var idInterno: String? = null
        private set
    var plano: Plano = PlanoAvulso("BRONZE")
    val jogosBuscados = mutableListOf<Jogo?>()
    val jogosAlugados = mutableListOf<Aluguel>()
    private val listaNotas = mutableListOf<Int>()
    val jogosRecomendados = mutableListOf<Jogo>()

    override val media: Double
        get() = listaNotas.average()

    fun recomendarJogo(jogo: Jogo, nota: Int) {

        if (nota < 1 || nota > 10) {
            throw IllegalArgumentException("Nota deve ser entre 1 e 10")
        }
        jogo.recomendar(nota)
        jogosRecomendados.add(jogo)
    }

    override fun recomendar(nota: Int) {

        if (nota < 1 || nota > 10) {
            throw IllegalArgumentException("Nota deve ser entre 1 e 10")
        }
        listaNotas.add(nota)
    }

    constructor(nome: String, email: String, dataNascimento: String?, usuario: String?, id: Int = 0)
            : this(nome, email) {

        this.dataNascimento = dataNascimento
        this.usuario = usuario
        this.id = id
        criarIdInterno()
    }

    init {
        if (nome.isNullOrBlank()){
            throw IllegalArgumentException("Nome não pode ser vazio")
        }
        this.email = validarEmail()
    }

    override fun toString(): String {
        return "Gamer:" +
                "Nome: $nome\n" +
                "Email: $email\n" +
                "Data de Nascimento: $dataNascimento\n" +
                "Usuario: $usuario\n" +
                "ID Interno: $idInterno\n" +
                "Reputação: $media\n" +
                "ID: $id\n"
    }

    fun criarIdInterno() {

        val numero = Random.nextInt(10000)
        val tag = String.format("%04d", numero)

        idInterno = "$usuario#$tag"
    }

    fun validarEmail(): String {

        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")

        if (regex.matches(email)) {
            return email
        } else {
            throw IllegalArgumentException("Email inválido")
        }
    }

    fun alugaJogo(jogo: Jogo, periodo: Periodo): Aluguel {

        val aluguel = Aluguel(this, jogo, periodo)
        jogosAlugados.add(aluguel)

        return aluguel
    }

    fun jogosDoMes(mes:Int): List<Jogo> {
        return jogosAlugados
            .filter { aluguel -> aluguel.periodo.dataInicial.monthValue == mes}
            .map { aluguel -> aluguel.jogo}
    }

    companion object {
        fun criarGamer(leitura: Scanner): Gamer {

            println("Seja bem vindo! Para inicializarmos, vamos criar sua conta, informe seu nome:")
            val nome = leitura.nextLine()

            println("Informe seu e-mail:")
            val email = leitura.nextLine()

            println("Deseja completar seu cadastro com usuário e data de nascimento? (S/N)")
            val resposta = leitura.nextLine()

            if (resposta.equals("s", true)) {

                println("Informe sua data de nascimento (DD/MM/AAAA):")
                val dataNascimento = leitura.nextLine()

                println("Informe seu nome de usuário:")
                val usuario = leitura.nextLine()

                return Gamer(nome, email, dataNascimento, usuario)
            } else {
                return Gamer(nome, email)
            }
        }
    }


}
