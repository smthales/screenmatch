package principal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modelos.Livro;
import modelos.Pessoa;
import modelos.Editora;

public class PrincipalDesafio {
    public static void main(String[] args) {
        String json = """
                {
                    "nome":"Thales",
                    "idade":24,
                    "cidade":"Rio de Janeiro"
                }
                """;

        String json_mais_campos = """
                {
                    "nome":"Brenda",
                    "idade":22,
                    "cidade":"São Paulo",
                    "profissão":"Designer"
                }
                """;

        String json_menos_campos = """
                {
                    "nome":"Flávia",
                    "idade":42
                }
                """;

        Gson gson = new Gson();

        var p = gson.fromJson(json, Pessoa.class);
        System.out.println(p);

        var p2 = gson.fromJson(json_mais_campos, Pessoa.class);
        System.out.println(p2);

        var p3 = gson.fromJson(json_menos_campos, Pessoa.class);
        System.out.println(p3);


        String json_livro = """
                {
                    "titulo":"Harry Potter",
                    "autor":"JK Rowling",
                    "editora":{
                        "nome":"Globo",
                        "cidade":"Rio de Janeiro"
                    }
                }
                """;


        var l1 = gson.fromJson(json_livro, Livro.class);
        System.out.println(l1);
    }
}
