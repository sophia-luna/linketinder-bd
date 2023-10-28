package linketinder.projeto.gradle.UI

import linketinder.projeto.gradle.DAO.MatchDAO
import linketinder.projeto.gradle.Model.Match

class MatchUI {

    static void match(){
        println("MATCH!!!")
    }

    static void listar(){

        LinkedList<Match> listaMatches = MatchDAO.listar()

        listaMatches.each { Match match ->
            {
                println(match)
            }
        }

    }

}
