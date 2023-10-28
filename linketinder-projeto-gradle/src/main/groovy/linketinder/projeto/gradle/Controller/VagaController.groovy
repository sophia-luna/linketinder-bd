package linketinder.projeto.gradle.Controller

import linketinder.projeto.gradle.DAO.VagaDAO
import linketinder.projeto.gradle.Model.Vaga

class VagaController {

    static void cadastrar(Vaga vaga){
        VagaDAO.cadastrar(vaga)
    }

    static boolean buscar(String id){
        VagaDAO.buscar(id)
    }
    static void alterar(String id, String descricao, String estado, String cidade){
        VagaDAO.alterar(id, descricao, estado, cidade)
    }

    static void deletar(String id){
        VagaDAO.deletar(id)
    }

    static LinkedList<Vaga> listar(){
        return VagaDAO.listar()
    }

}
