package linketinder.projeto.gradle

import groovy.test.GroovyTestCase
import linketinder.projeto.gradle.DAO.VagaDAO
import linketinder.projeto.gradle.Model.Vaga
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class VagaDaoTest extends GroovyTestCase{

    static Vaga vaga

    @BeforeAll
    static void setUp(){

        vaga = new Vaga(

                "nome da vaga",
                "VAGA111",
                "descricao da vaga aqui",
                "Goias",
                "Goiania",
                "12.345.678/0001-01"
        )

    }

    @Test
    void testCadastrarVaga() {

        VagaDAO.cadastrar(vaga)

        LinkedList<Vaga> vagas = VagaDAO.listar()
        boolean resultado = vagas.find {Vaga v -> {v.id == vaga.id}}

        assertEquals(true, resultado)

    }

    @Test
    void testAlterarVaga(){

        String descricao = "Alteracao de descricao aqui"
        String estado = "DF"
        String cidade = "Brasilia"

        VagaDAO.alterar(vaga.id, descricao, estado, cidade)

        LinkedList<Vaga> vagas = VagaDAO.listar()
        Vaga resultado = vagas.find {Vaga v -> {v.id == vaga.id}}

        assertEquals(resultado.descricao, descricao)
        assertEquals(resultado.estado, estado)
        assertEquals(resultado.cidade, cidade)

    }

    @Test
    void testDeletarVaga() {

        VagaDAO.deletar(vaga.id)

        LinkedList<Vaga> vagas = VagaDAO.listar()
        boolean resultado = vagas.find {Vaga v -> {v.id == vaga.id}}

        assertEquals(false, resultado)

    }

}
