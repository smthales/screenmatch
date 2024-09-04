package principal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import excecao.ErroDeConversaoDeAnoException;
import modelos.*;
import calculos.*;

public class PrincipalComBusca {

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do filme a ser buscado:");
        var busca = sc.nextLine();

        // formatando a string da busca para o modelo URI
        String[] vetor_busca = busca.split(" ");
        String busca_formatada = "";
        for (int i = 0; i < vetor_busca.length; i++) {
            if(i < vetor_busca.length - 1) {
                busca_formatada += vetor_busca[i];
                busca_formatada += "+";
            }
            else {
                busca_formatada += vetor_busca[i];
            }
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.omdbapi.com/?t=" + busca_formatada + "&apikey=b3e4ca26")).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        String json = response.body();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();

        // Titulo meuTitulo = gson.fromJson(json, Titulo.class);
        TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
        System.out.println(meuTituloOmdb);

        try {
            Titulo meuTitulo = new Titulo(meuTituloOmdb);
            System.out.println("Título já convertido.");
            System.out.println(meuTitulo);
        } catch(NumberFormatException e) {
            System.out.println("Ocorreu um erro na conversão.");
            System.out.println(e.getMessage());
        } catch(IllegalArgumentException e) {
            System.out.println("Algum erro de argumento na busca, verifique o endereço.");
            e.getMessage();
        } catch(ErroDeConversaoDeAnoException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Programa finalizado.");

        sc.close();

    }
}
