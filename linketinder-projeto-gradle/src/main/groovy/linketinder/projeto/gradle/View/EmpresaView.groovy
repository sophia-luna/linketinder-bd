package linketinder.projeto.gradle.View

import linketinder.projeto.gradle.Controller.EmpresaController
import linketinder.projeto.gradle.DAO.EmpresaDAO
import linketinder.projeto.gradle.Model.Empresa

class EmpresaView {

    static void criar(){

        print("Nome da Empresa: ")
        String nome=System.in.newReader().readLine()

        print("CNPJ da Empresa: ")
        String cnpj=System.in.newReader().readLine()

        print("Email da Empresa: ")
        String email=System.in.newReader().readLine()

        print("Senha:")
        String senha=System.in.newReader().readLine()

        print("País: ")
        String pais=System.in.newReader().readLine()

        print("CEP: ")
        String cep=System.in.newReader().readLine()

        print("Descrição da Empresa: ")
        String descricaoEmpresa=System.in.newReader().readLine()

        EmpresaController.criar(new Empresa(nome, cnpj, email, senha, descricaoEmpresa, pais, cep))

    }

    static void alterar(){

        print("CNPJ da empresa que deseja alterar: ")
        String cnpj=System.in.newReader().readLine()

        if(EmpresaController.buscar(cnpj)){

            print("Email da Empresa: ")
            String email=System.in.newReader().readLine()

            print("Senha:")
            String senha=System.in.newReader().readLine()

            print("País: ")
            String pais=System.in.newReader().readLine()

            print("CEP: ")
            String cep=System.in.newReader().readLine()

            print("Descrição da Empresa: ")
            String descricaoEmpresa=System.in.newReader().readLine()

            EmpresaController.alterar(cnpj, email, senha, pais, cep, descricaoEmpresa)

        }else{
            println("Empresa não encontrada.")
        }
    }

    static void deletar(){

        println("Insira o CNPJ da empresa que deseja deletar: ")
        String cnpj=System.in.newReader().readLine()

        if(EmpresaController.buscar(cnpj)){

            EmpresaController.deletar(cnpj)

        }else{
            println("Empresa não encontrada.")
        }
    }

    static void listar(){

        LinkedList<Empresa> listaEmpresas = EmpresaController.listar()

        listaEmpresas.each { Empresa empresa ->
            {
                println(empresa)
            }
        }

    }

}
