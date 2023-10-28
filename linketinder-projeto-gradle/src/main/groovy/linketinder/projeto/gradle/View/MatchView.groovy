package linketinder.projeto.gradle.View

import linketinder.projeto.gradle.Controller.MatchController
import linketinder.projeto.gradle.Model.Match

class MatchView {

    static void match(){
        println("MATCH!!!")
    }

    static void listar(){

        LinkedList<Match> listaMatches = MatchController.listar()

        listaMatches.each { Match match ->
            {
                println(match)
            }
        }

    }

}
