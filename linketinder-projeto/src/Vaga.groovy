class Vaga {

    String nome
    String id
    String descricao
    String estado
    String cidade
    String cnpjEmpresa

    Vaga (String nome, String id, String descricao, String estado, String cidade, String cnpjEmpresa){
        this.nome=nome
        this.id=id
        this.descricao=descricao
        this.estado=estado
        this.cidade=cidade
        this.cnpjEmpresa=cnpjEmpresa
    }

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

        VagaDAO.cadastrar(new Vaga(nome, id, descricao, estado, cidade, cnpj))

    }

    static void alterar(){

        print("ID da vaga que deseja alterar: ")
        String id=System.in.newReader().readLine()

        if(VagaDAO.buscar(id)){

            print("Descrição da Vaga: : ")
            String descricao=System.in.newReader().readLine()

            print("Estado: ")
            String estado=System.in.newReader().readLine()

            print("Cidade: ")
            String cidade=System.in.newReader().readLine()

            VagaDAO.alterar(id, descricao, estado, cidade)

        }else{
            println("Vaga não encontrada.")
        }
    }

    static void deletar(){

        println("Insira o ID da vaga que deseja deletar: ")
        String id=System.in.newReader().readLine()

        if(VagaDAO.buscar(id)){

            VagaDAO.deletar(id)

        }else{
            println("Vaga não encontrada.")
        }

    }

    static void listar(){

        VagaDAO.listar()

    }
}