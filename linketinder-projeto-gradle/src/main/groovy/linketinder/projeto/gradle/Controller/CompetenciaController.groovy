package linketinder.projeto.gradle.Controller

import linketinder.projeto.gradle.DAO.CandidatoDAO
import linketinder.projeto.gradle.DAO.CompetenciaCandidatoDAO
import linketinder.projeto.gradle.DAO.CompetenciaDAO
import linketinder.projeto.gradle.DAO.CompetenciaVagaDAO
import linketinder.projeto.gradle.Model.Competencia

class CompetenciaController {

    static void cadastrar(String nome) {
        CompetenciaDAO.cadastrar(new Competencia(nome))
    }

    static void deletar(String nome) {
        CompetenciaDAO.deletar(nome)
    }

    static boolean buscar(String nome) {
        return CompetenciaDAO.buscar(nome)
    }

    static void cadastrarCompetenciaCandidato(String nome, String cpf) {
        CompetenciaCandidatoDAO.cadastrar(nome, cpf)
    }

    static void cadastrarCompetenciaVaga(String nome, String id) {
        CompetenciaVagaDAO.cadastrar(nome, id)
    }

}
