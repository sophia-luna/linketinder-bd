package linketinder.projeto.gradle.View

import linketinder.projeto.gradle.Controller.CurtidasController
import linketinder.projeto.gradle.DAO.CurtidasCandidatoDAO
import linketinder.projeto.gradle.DAO.CurtidasEmpresaDAO

class CurtidasView {

    static void curtidasEmpresa() {

        println("Insira o CNPJ da empresa: ")
        String cnpj=System.in.newReader().readLine()

        println("Insira o CPF do candidato: ")
        String cpf=System.in.newReader().readLine()

        CurtidasController.curtidasEmpresa(cnpj, cpf)

    }

    static void curtidasCandidato() {

        println("Insira o CPF do candidato: ")
        String cpf=System.in.newReader().readLine()

        println("Insira o ID da vaga: ")
        String id=System.in.newReader().readLine()

        CurtidasController.curtidasCandidato(cpf, id)

    }
}
