
// SOPHIA SOUSA-AGUIAR LUNA

static void main(String[] args) {

  boolean loop=true
  while(loop){

    Menu.displayMenu()
    String input = System.in.newReader().readLine()
    int i= Validacao.opcaoValida(input)

    switch (i) {
      case 1:

        Candidato.criar()
        break

      case 2:

        Candidato.alterar()
        break

      case 3:

        Candidato.deletar()
        break

      case 4:

        Candidato.listar()
        break

      case 5:

        Empresa.criar()
        break

      case 6:

        Empresa.alterar()
        break

      case 7:

        Empresa.deletar()
        break

      case 8:

        Empresa.listar()
        break

      case 9:

        Vaga.criar()
        break

      case 10:

        Vaga.alterar()
        break

      case 11:

        Vaga.deletar()
        break

      case 12:

        Vaga.listar()
        break

      case 13:

        CompetenciaDAO.cadastrar()
        break

      case 14:

        CompetenciaCandidatoDAO.cadastrar()
        break

      case 15:

        CompetenciaVagaDAO.cadastrar()
        break

      case 16:

        println("Insira o nome da competencia que deseja deletar: ")
        String nome=System.in.newReader().readLine()
        CompetenciaDAO.deletar(nome)
        break

      case 17:

        println("Insira o CNPJ da empresa: ")
        String cnpj=System.in.newReader().readLine()

        println("Insira o CPF do candidato: ")
        String cpf=System.in.newReader().readLine()

        CurtidasEmpresaDAO.curtir(cnpj, cpf)
        break

      case 18:

        println("Insira o CPF do candidato: ")
        String cpf=System.in.newReader().readLine()

        println("Insira o ID da vaga: ")
        String id=System.in.newReader().readLine()

        CurtidasCandidatoDAO.curtir(cpf, id)
        break

      case 19:

        MatchDAO.listar()
        break

      case 20:

        loop=false
        break

      default:

        println("Opção Inválida.")
        break

    }
  }
}

