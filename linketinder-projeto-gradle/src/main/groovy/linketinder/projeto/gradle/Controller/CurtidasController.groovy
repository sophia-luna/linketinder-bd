package linketinder.projeto.gradle.Controller

import linketinder.projeto.gradle.DAO.CurtidasCandidatoDAO
import linketinder.projeto.gradle.DAO.CurtidasEmpresaDAO

class CurtidasController {

    static void curtidasEmpresa(String cnpj, String cpf) {
        CurtidasEmpresaDAO.curtir(cnpj, cpf)
    }

    static void curtidasCandidato(String cpf, String id) {
        CurtidasCandidatoDAO.curtir(cpf, id)
    }

}
