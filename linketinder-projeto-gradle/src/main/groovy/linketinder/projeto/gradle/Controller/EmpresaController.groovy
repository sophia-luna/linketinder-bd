package linketinder.projeto.gradle.Controller

import linketinder.projeto.gradle.DAO.EmpresaDAO
import linketinder.projeto.gradle.Model.Empresa

class EmpresaController {

    static void criar(Empresa empresa){
        EmpresaDAO.cadastrar(empresa)
    }

    static boolean buscar(String cnpj){
        return EmpresaDAO.buscar(cnpj)
    }
    static void alterar(String cnpj, String email, String senha, String pais, String cep, String descricaoEmpresa){
        EmpresaDAO.alterar(cnpj, email, senha, pais, cep, descricaoEmpresa)
    }

    static void deletar(String cnpj){
        EmpresaDAO.deletar(cnpj)
    }

    static LinkedList listar(){
        return EmpresaDAO.listar()
    }

}
