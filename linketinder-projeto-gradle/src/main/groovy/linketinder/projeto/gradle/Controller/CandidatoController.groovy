package linketinder.projeto.gradle.Controller

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import linketinder.projeto.gradle.DAO.CandidatoDAO
import linketinder.projeto.gradle.Model.Candidato
import linketinder.projeto.gradle.Model.Empresa

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/candidato")
class CandidatoController extends HttpServlet{

    @Override
    void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try{

            Class.forName("org.postgresql.Driver")
            LinkedList<Candidato> listaCandidatos = CandidatoDAO.listar()

            String json = JsonOutput.toJson(listaCandidatos)
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
            String sobrenome = json.sobrenome
            String cpf = json.cpf
            String email = json.email
            String dataNascimento = json.dataNascimento
            String senha = json.senha
            String descricaoPessoal = json.descricaoPessoal
            String pais = json.pais
            String cep = json.cep

            Empresa candidato = new Candidato(nome, sobrenome, cpf, email, dataNascimento, senha, descricaoPessoal, pais, cep)

            Class.forName("org.postgresql.Driver")
            CandidatoController.cadastrar(candidato)

            String candidatoJson = JsonOutput.toJson(candidato)
            response.getOutputStream().println(candidatoJson)

        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    static void cadastrar(Candidato candidato) {
        CandidatoDAO.cadastrar(candidato)
    }

    static boolean buscar(String cpf) {

        if (CandidatoDAO.buscar(cpf)) {
            return true
        }
        return false

    }

    static void alterar(String email, String senha, String descricaoPessoal, String pais, String cep, String cpf) {
        CandidatoDAO.alterar(email, senha, descricaoPessoal, pais, cep, cpf)
    }

    static void deletar(String cpf){
        CandidatoDAO.deletar(cpf)
    }

    static LinkedList<Candidato> listar(){
        return CandidatoDAO.listar()
    }

}
