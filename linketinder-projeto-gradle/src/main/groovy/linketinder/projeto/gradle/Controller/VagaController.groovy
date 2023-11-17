package linketinder.projeto.gradle.Controller

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import linketinder.projeto.gradle.DAO.EmpresaDAO
import linketinder.projeto.gradle.DAO.VagaDAO
import linketinder.projeto.gradle.Model.Empresa
import linketinder.projeto.gradle.Model.Vaga

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/vaga")
class VagaController extends HttpServlet{

    @Override
    void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try{

            Class.forName("org.postgresql.Driver")
            LinkedList<Vaga> listaVagas = VagaDAO.listar()

            String json = JsonOutput.toJson(listaVagas)
            response.getOutputStream().println(json)

        } catch (Exception e) {
            e.printStackTrace()
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {

            String jsonInput = request.getReader().lines().collect { it }.join("\n")

            JsonSlurper jsonSlurper = new JsonSlurper()
            def json = jsonSlurper.parseText(jsonInput)

            String nome = json.nome
            String id = json.id
            String descricao = json.descricao
            String estado = json.estado
            String cidade = json.cidade
            String cnpjEmpresa = json.cnpjEmpresa

            Vaga vaga = new Vaga(nome, id, descricao, estado, cidade, cnpjEmpresa)

            Class.forName("org.postgresql.Driver")
            VagaDAO.cadastrar(vaga)

            String vagaJson = JsonOutput.toJson(vaga)
            response.getOutputStream().println(vagaJson)

        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    static void cadastrar(Vaga vaga){
        VagaDAO.cadastrar(vaga)
    }

    static boolean buscar(String id){
        VagaDAO.buscar(id)
    }
    static void alterar(String id, String descricao, String estado, String cidade){
        VagaDAO.alterar(id, descricao, estado, cidade)
    }

    static void deletar(String id){
        VagaDAO.deletar(id)
    }

    static LinkedList<Vaga> listar(){
        return VagaDAO.listar()
    }

}
