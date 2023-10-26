package linketinder.projeto.gradle

import linketinder.projeto.gradle.DAO.CandidatoDAO
import linketinder.projeto.gradle.DAO.EmpresaDAO
import linketinder.projeto.gradle.Model.Candidato
import linketinder.projeto.gradle.Model.Empresa
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import groovy.test.GroovyTestCase


class CandidatoDaoTest extends GroovyTestCase{

    static Candidato candidato

    @BeforeAll
    static void setUp(){

        candidato = new Candidato(
                "sophia",
                "luna",
                "058.321.891-16",
                "sophia@gmail.com",
                "04/05/2002",
                "123456",
                "descricao pessoal do candidato aqui",
                "brasil",
                "34368-826"
        )

    }

    @Test
    void testCadastrarCandidato() {

        CandidatoDAO.cadastrar(candidato)

        LinkedList<Candidato> candidatos = CandidatoDAO.listar()
        boolean resultado = candidatos.find {Candidato c -> {c.cpf == candidato.cpf}}

        assertEquals(true, resultado)

    }

    @Test
    void testAlterarCandidato(){

        String email = "sophialuna@gmail.com"
        String senha = "654321"
        String descricaoPessoal = "Alteracao de descricao aqui"
        String pais = "Estados Unidos"
        String cep = "30345"

        CandidatoDAO.alterar(email, senha, descricaoPessoal, pais, cep, candidato.cpf)

        LinkedList<Candidato> candidatos = CandidatoDAO.listar()
        Candidato resultado = candidatos.find {Candidato c -> {c.cpf == candidato.cpf}}

        assertEquals(resultado.email, email)
        assertEquals(resultado.senha, senha)
        assertEquals(resultado.pais, pais)
        assertEquals(resultado.cep, cep)
        assertEquals(resultado.descricaoPessoal, descricaoPessoal)

    }

    @Test
    void testDeletarCandidato() {

        CandidatoDAO.deletar("058.321.891-16")

        LinkedList<Candidato> candidatos = CandidatoDAO.listar()
        boolean resultado = candidatos.find {Candidato c -> {c.cpf == candidato.cpf}}

        assertEquals(false, resultado)

    }
}
