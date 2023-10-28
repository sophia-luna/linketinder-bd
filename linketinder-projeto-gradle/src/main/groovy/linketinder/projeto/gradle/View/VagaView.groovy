package linketinder.projeto.gradle.View

import linketinder.projeto.gradle.Controller.VagaController
import linketinder.projeto.gradle.DAO.VagaDAO
import linketinder.projeto.gradle.Model.Vaga

class VagaView {

    static void criar(){

        print("Nome da vaga: ")
        String nome=System.in.newReader().readLine()

        print("ID da Vaga: ")
        String id=System.in.newReader().readLine()

        print("Descrição da Vaga: : ")
        String descricao=System.in.newReader().readLine()

        print("Estado: ")
        String estado=System.in.newReader().readLine()

        print("Cidade: ")
        String cidade=System.in.newReader().readLine()

        print("CNPJ da empresa: ")
        String cnpj=System.in.newReader().readLine()

        VagaController.cadastrar(new Vaga(nome, id, descricao, estado, cidade, cnpj))

    }

    static void alterar(){

        print("ID da vaga que deseja alterar: ")
        String id=System.in.newReader().readLine()

        if(VagaController.buscar(id)){

            print("Descrição da Vaga: : ")
            String descricao=System.in.newReader().readLine()

            print("Estado: ")
            String estado=System.in.newReader().readLine()

            print("Cidade: ")
            String cidade=System.in.newReader().readLine()

            VagaController.alterar(id, descricao, estado, cidade)

        }else{
            println("Vaga não encontrada.")
        }
    }

    static void deletar(){

        println("Insira o ID da vaga que deseja deletar: ")
        String id=System.in.newReader().readLine()

        if(VagaController.buscar(id)){

            VagaController.deletar(id)

        }else{
            println("Vaga não encontrada.")
        }

    }

    static void listar(){

        LinkedList<Vaga> listaVagas = VagaController.listar()

        listaVagas.each { Vaga vaga ->
            {
                println(vaga)
            }
        }

    }

}
