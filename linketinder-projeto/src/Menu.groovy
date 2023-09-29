class Menu {

    static void displayMenu() {

        println("    Menu")
        println("1. Cadastrar novo candidato")
        println("2. Alterar candidato existente")
        println("3. Deletar candidato")
        println("4. Listar todos os candidatos")
        println("5. Cadastrar nova empresa")
        println("6. Alterar empresa existente")
        println("7. Deletar empresa")
        println("8. Listar todas as empresas")
        println("9. Criar nova vaga")
        println("10. Alterar vaga existente")
        println("11. Deletar vaga")
        println("12. Listar todas as vagas")
        println("13. Cadastrar nova competencia")
        println("14. Adicionar competencia para candidato")
        println("15. Adicionar competencia para vaga")
        println("16. Deletar competencia")
        println("17. Curtir candidato")
        println("18. Curtir empresa")
        println("19. Listar todos os matches")
        println("20. Sair")

    }

    static void curtirCandidato(LinkedList<Tuple2<String, String>> matches, LinkedList<Candidato> candidatos, LinkedList<Empresa> empresas){

        println("CNPJ da empresa: ")
        String cnpj = System.in.newReader().readLine()

        println("CPF do candidato: ")
        String cpf = System.in.newReader().readLine()

        for(Empresa empresa: empresas){
            if(empresa.cnpj == cnpj) {
                empresa.curtidas.add(cpf)
            }
        }

        for(Candidato candidato: candidatos){
            if(candidato.cpf == cpf){
                for(String curtido: candidato.curtidas){
                    if(curtido == cnpj){
                        matches.add(new Tuple2<String, String>(cpf, cnpj))
                    }
                }
            }
        }

    }

    static void curtirEmpresa(LinkedList<Tuple2<String, String>> matches, LinkedList<Candidato> candidatos, LinkedList<Empresa> empresas){

        println("CPF do candidato: ")
        String cpf = System.in.newReader().readLine()

        println("CNPJ da empresa: ")
        String cnpj = System.in.newReader().readLine()

        for(Candidato candidato: candidatos){
            if(candidato.cpf == cpf){
                candidato.curtidas.add(cnpj)
            }
        }

        for(Empresa empresa: empresas){
            if(empresa.cnpj == cnpj) {
                for(String curtido: empresa.curtidas){
                    if(curtido == cpf){
                        matches.add(new Tuple2<>(cpf, cnpj))
                    }
                }
            }
        }

    }

}
