import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.Scanner


fun main() {

    val leitura = Scanner(System.`in`)
    println("Digite o ID do jogo que deseja buscar:")
    val busca = leitura.nextLine()

    val endereco = "https://www.cheapshark.com/api/1.0/games?id=$busca"

    val client: HttpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create(endereco))
        .build()

//    try {
//        val response = client
//        .send(request, BodyHandlers.ofString())
//
//    val json = response.body()
//    println(json)
//
//    val gson = Gson()
//    val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)
//        val meuJogo = Jogo(
//            meuInfoJogo.info.title,
//            meuInfoJogo.info.thumb)
//
//        println(meuJogo)
//    } catch (ex: Exception) {
//        println("Erro ao buscar o jogo, tente outro ID.")
//    }

    var meuJogo:Jogo? = null

    val resultado = runCatching {

        val response = client
            .send(request, BodyHandlers.ofString())

        val json = response.body()
//        println(json)

        val gson = Gson()
        val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)

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

    resultado.onSuccess {
        println("Busca finalizada com sucesso")
    }
}