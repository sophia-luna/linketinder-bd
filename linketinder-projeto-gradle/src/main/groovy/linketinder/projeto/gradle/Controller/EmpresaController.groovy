package linketinder.projeto.gradle.Controller

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import linketinder.projeto.gradle.DAO.CandidatoDAO
import linketinder.projeto.gradle.DAO.EmpresaDAO
import linketinder.projeto.gradle.Model.Candidato
import linketinder.projeto.gradle.Model.Empresa

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/empresa")
class EmpresaController extends HttpServlet{

    @Override
    void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try{

            Class.forName("org.postgresql.Driver")
            LinkedList<Empresa> listaEmpresas = EmpresaDAO.listar()

            String json = JsonOutput.toJson(listaEmpresas)
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
            String cnpj = json.cnpj
            String email = json.email
            String senha = json.senha
            String descricaoEmpresa = json.descricaoEmpresa
            String pais = json.pais
            String cep = json.cep

            Empresa empresa = new Empresa(nome, cnpj, email, senha, descricaoEmpresa, pais, cep)

            Class.forName("org.postgresql.Driver")
            EmpresaDAO.cadastrar(empresa)

            String empresaJson = JsonOutput.toJson(empresa)
            response.getOutputStream().println(empresaJson)

        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    static void criar(Empresa empresa){
        EmpresaDAO.cadastrar(empresa)
    }

    static boolean buscar(String cnpj){
        return EmpresaDAO.buscar(cnpj)
    }
    static void alterar(String cnpj, String email, String senha, String pais, String cep, String descricaoEmpresa){
        EmpresaDAO.alterar(cnpj, email, senha, pais, cep, descricaoEmpresa)
    }

    static void deletar(String cnpj){
        EmpresaDAO.deletar(cnpj)
    }

    static LinkedList listar(){
        return EmpresaDAO.listar()
    }

}
