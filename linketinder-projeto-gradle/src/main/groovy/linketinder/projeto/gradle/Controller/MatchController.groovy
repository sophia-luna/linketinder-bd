package linketinder.projeto.gradle.Controller

import linketinder.projeto.gradle.DAO.MatchDAO
import linketinder.projeto.gradle.Model.Match

class MatchController {

    static LinkedList<Match> listar() {
        return MatchDAO.listar()
    }

}
