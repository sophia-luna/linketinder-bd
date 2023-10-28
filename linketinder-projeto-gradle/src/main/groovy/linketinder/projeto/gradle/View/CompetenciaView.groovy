package linketinder.projeto.gradle.View

import linketinder.projeto.gradle.Controller.CandidatoController
import linketinder.projeto.gradle.Controller.CompetenciaController
import linketinder.projeto.gradle.Controller.VagaController

class CompetenciaView {

    static void cadastrar(){

        print("Nome da competencia: ")
        String nome=System.in.newReader().readLine()

        CompetenciaController.cadastrar(nome)

    }

    static void deletar(){

        println("Insira o nome da competencia que deseja deletar: ")
        String nome=System.in.newReader().readLine()

        CompetenciaController.deletar(nome)

    }

    static void cadastrarCompetenciaCandidato(){

        print("Nome da competencia: ")
        String nome=System.in.newReader().readLine()

        print("CPF do candidato: ")
        String cpf=System.in.newReader().readLine()

        if(CandidatoController.buscar(cpf)){

            if(CompetenciaController.buscar(nome)){

                CompetenciaController.cadastrarCompetenciaCandidato(nome, cpf)

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

        if(VagaController.buscar(id)){

            if(CompetenciaController.buscar(nome)){

                CompetenciaController.cadastrarCompetenciaVaga(nome, id)

            }else {
                println "Vaga não encontrada."
            }

        }
        else{
            println "Candidato não encontrado."
        }
    }
}
