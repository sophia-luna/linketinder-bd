package linketinder.projeto.gradle.UI

import linketinder.projeto.gradle.DAO.CandidatoDAO
import linketinder.projeto.gradle.DAO.CompetenciaCandidatoDAO
import linketinder.projeto.gradle.DAO.CompetenciaDAO
import linketinder.projeto.gradle.DAO.CompetenciaVagaDAO
import linketinder.projeto.gradle.DAO.VagaDAO
import linketinder.projeto.gradle.Model.Competencia

class CompetenciaUI {

    static void cadastrar(){

        print("Nome da competencia: ")
        String nome=System.in.newReader().readLine()

        CompetenciaDAO.cadastrar(new Competencia(nome))

    }

    static void deletar(){

        println("Insira o nome da competencia que deseja deletar: ")
        String nome=System.in.newReader().readLine()

        CompetenciaDAO.deletar(nome)

    }

    static void cadastrarCompetenciaCandidato(){

        print("Nome da competencia: ")
        String nome=System.in.newReader().readLine()

        print("CPF do candidato: ")
        String cpf=System.in.newReader().readLine()

        if(CandidatoDAO.buscar(cpf)){

            if(CompetenciaDAO.buscar(nome)){

                CompetenciaCandidatoDAO.cadastrar(nome, cpf)

            }else {
                println "Competencia não encontrada."
            }

        }
        else{
            println "Candidato não encontrado."
        }
    }

    static void cadastrarCompetenciaVaga(){

        print("Nome da competencia: ")
        String nome=System.in.newReader().readLine()

        print("ID da vaga: ")
        String id=System.in.newReader().readLine()

        if(VagaDAO.buscar(id)){

            if(CompetenciaDAO.buscar(nome)){

                CompetenciaVagaDAO.cadastrar(nome, id)

            }else {
                println "Vaga não encontrada."
            }

        }
        else{
            println "Candidato não encontrado."
        }
    }

}
