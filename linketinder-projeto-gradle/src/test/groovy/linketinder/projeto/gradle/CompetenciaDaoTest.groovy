package linketinder.projeto.gradle

import groovy.test.GroovyTestCase
import linketinder.projeto.gradle.DAO.CompetenciaDAO
import linketinder.projeto.gradle.Model.Candidato
import linketinder.projeto.gradle.Model.Competencia
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class CompetenciaDaoTest extends GroovyTestCase{

    static Competencia competencia

    @BeforeAll
    static void setUP() {

        competencia = new Competencia("nome da competencia")

    }

    @Test
    void testCadastrarCompetencia() {

        CompetenciaDAO.cadastrar(competencia)

        boolean resultado = CompetenciaDAO.buscar(competencia.nome)

        assertEquals(true, resultado)

    }

    @Test
    void testDeletarCompetencia() {

        CompetenciaDAO.deletar(competencia.nome)

        boolean resultado = CompetenciaDAO.buscar(competencia.nome)

        assertEquals(false, resultado)
    }
}
