package linketinder.projeto.gradle.View

import linketinder.projeto.gradle.Controller.CandidatoController
import linketinder.projeto.gradle.DAO.CandidatoDAO
import linketinder.projeto.gradle.Model.Candidato

class CandidatoView {

    static void criar() {

        print("Nome do Candidato: ")
        String nome = System.in.newReader().readLine()

        print("Sobrenome do Candidato: ")
        String sobrenome = System.in.newReader().readLine()

        print("CPF do Candidato: ")
        String cpf = System.in.newReader().readLine()

        print("Data de nascimento do Candidato: ")
        String dataNascimento = System.in.newReader().readLine()

        print("Email do Candidato: ")
        String email = System.in.newReader().readLine()

        print("Senha:")
        String senha = System.in.newReader().readLine()

        print("Descrição Pessoal: ")
        String descricaoPessoal = System.in.newReader().readLine()

        print("País: ")
        String pais = System.in.newReader().readLine()

        print("CEP: ")
        String cep = System.in.newReader().readLine()

        CandidatoController.cadastrarCandidato(new Candidato(nome, sobrenome, cpf, email, dataNascimento, senha, descricaoPessoal, pais, cep))
    }

    static void alterar() {

        print("CPF do Candidato que deseja alterar: ")
        String cpf = System.in.newReader().readLine()

        if (CandidatoController.buscar(cpf)) {

            print("Email do Candidato: ")
            String email = System.in.newReader().readLine()

            print("Senha:")
            String senha = System.in.newReader().readLine()

            print("Descrição Pessoal: ")
            String descricaoPessoal = System.in.newReader().readLine()

            print("País: ")
            String pais = System.in.newReader().readLine()

            print("CEP: ")
            String cep = System.in.newReader().readLine()

            CandidatoController.alterar(email, senha, descricaoPessoal, pais, cep, cpf)

        } else {
            println("Candidato não encontrado.")
        }
    }

    static void deletar() {

        println("Insira o CPF do candidato que deseja deletar: ")
        String cpf = System.in.newReader().readLine()

        if (CandidatoController.buscar(cpf)) {

            CandidatoController.deletar(cpf)

        } else {
            println("Candidato não encontrado.")
        }
    }

    static void listar() {

        LinkedList<Candidato> listaCandidatos = CandidatoController.listar()

        if(listaCandidatos){

            listaCandidatos.each { Candidato candidato ->
                {
                    println(candidato)
                }
            }

        }else{
            println "Nenhum candidato encontrado."
        }

    }

}
