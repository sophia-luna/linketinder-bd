
// SOPHIA SOUSA-AGUIAR LUNA

static void main(String[] args) {

  boolean loop=true
  while(loop){

    Menu.displayMenu()
    String input = System.in.newReader().readLine()
    int i= Validacao.opcaoValida(input)

    switch (i) {
      case 1:

        CandidatoDAO.cadastrar()
        break

      case 2:

        CandidatoDAO.alterar()
        break

      case 3:

        CandidatoDAO.deletar()
        break

      case 4:

        CandidatoDAO.listar()
        break

      case 5:

        EmpresaDAO.cadastrar()
        break

      case 6:

        EmpresaDAO.alterar()
        break

      case 7:

        EmpresaDAO.deletar()
        break

      case 8:

        EmpresaDAO.listar()
        break

      case 9:

        VagaDAO.cadastrar()
        break

      case 10:

        VagaDAO.alterar()
        break

      case 11:

        println("Insira o ID da vaga que deseja deletar: ")
        String id=System.in.newReader().readLine()
        VagaDAO.deletar(id)
        break

      case 12:

        VagaDAO.listar()
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


        break

      case 18:


        break

      case 19:


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

