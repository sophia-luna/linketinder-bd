package linketinder.projeto.gradle

import groovy.test.GroovyTestCase
import linketinder.projeto.gradle.DAO.MatchDAO
import linketinder.projeto.gradle.Model.Candidato
import linketinder.projeto.gradle.Model.Match
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class MatchDaoTest extends GroovyTestCase{

    static Match match
    @BeforeAll
    static void setUp(){

        match = new Match("123.456.789-01", "34.567.890/0001-04")

    }

    @Test
    void testCadastrarMatch(){

        MatchDAO.match("123.456.789-01", "34.567.890/0001-04")

        LinkedList<Match> matches = MatchDAO.listar()
        boolean resultado = matches.find { Match m -> {m.cpfCandidato == match.cpfCandidato}}

        assertEquals(true, resultado)
    }

}
