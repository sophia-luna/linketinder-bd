package linketinder.projeto.gradle.Model

import linketinder.projeto.gradle.DAO.CandidatoDAO

class Candidato extends Pessoa {

    public String cpf
    public String dataNascimento
    public String descricaoPessoal
    public String sobrenome

    Candidato(String nome, String sobrenome, String cpf, String email, String dataNascimento, String senha, String descricaoPessoal, String pais, String cep) {
        this.nome = nome
        this.sobrenome = sobrenome
        this.cpf = cpf
        this.dataNascimento = dataNascimento
        this.email = email
        this.senha = senha
        this.pais = pais
        this.cep = cep
        this.descricaoPessoal = descricaoPessoal
    }

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

        CandidatoDAO.cadastrar(new Candidato(nome, sobrenome, cpf, email, dataNascimento, senha, descricaoPessoal, pais, cep))
    }

    static void alterar() {

        print("CPF do Candidato que deseja alterar: ")
        String cpf = System.in.newReader().readLine()

        if (CandidatoDAO.buscar(cpf)) {

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

            CandidatoDAO.alterar(email, senha, descricaoPessoal, pais, cep, cpf)

        } else {
            println("Candidato não encontrado.")
        }
    }

    static void deletar() {

        println("Insira o CPF do candidato que deseja deletar: ")
        String cpf = System.in.newReader().readLine()

        if (CandidatoDAO.buscar(cpf)) {

            CandidatoDAO.deletar(cpf)

        } else {
            println("Candidato não encontrado.")
        }
    }

    static void listar() {

        CandidatoDAO.listar()

    }

}