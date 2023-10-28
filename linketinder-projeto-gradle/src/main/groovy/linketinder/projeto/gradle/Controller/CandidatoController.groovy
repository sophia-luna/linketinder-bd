package linketinder.projeto.gradle.Controller

import linketinder.projeto.gradle.DAO.CandidatoDAO
import linketinder.projeto.gradle.Model.Candidato

class CandidatoController {

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
