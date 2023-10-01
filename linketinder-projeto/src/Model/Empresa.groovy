package Model

import DAO.EmpresaDAO

class Empresa extends Pessoa {

    public String cnpj
    public String descricaoEmpresa

    Empresa(String nome, String cnpj, String email, String senha, String pais, String cep, String descricaoEmpresa) {
        this.nome=nome
        this.email=email
        this.cnpj=cnpj
        this.senha=senha
        this.pais=pais
        this.cep=cep
        this.descricaoEmpresa=descricaoEmpresa
    }


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

        EmpresaDAO.cadastrar(new Empresa(nome, cnpj, email, senha, pais, cep, descricaoEmpresa))

    }

    static void alterar(){

        print("CNPJ da empresa que deseja alterar: ")
        String cnpj=System.in.newReader().readLine()

        if(EmpresaDAO.buscar(cnpj)){

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

            EmpresaDAO.alterar(cnpj, email, senha, pais, cep, descricaoEmpresa)

        }else{
            println("Empresa não encontrada.")
        }
    }

    static void deletar(){

        println("Insira o CNPJ da empresa que deseja deletar: ")
        String cnpj=System.in.newReader().readLine()

        if(EmpresaDAO.buscar(cnpj)){

            EmpresaDAO.deletar(cnpj)

        }else{
            println("Empresa não encontrada.")
        }
    }

    static void listar(){

        EmpresaDAO.listar()

    }
}
