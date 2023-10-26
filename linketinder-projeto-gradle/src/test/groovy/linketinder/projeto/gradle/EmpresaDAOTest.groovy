package linketinder.projeto.gradle

import groovy.test.GroovyTestCase
import linketinder.projeto.gradle.DAO.EmpresaDAO
import linketinder.projeto.gradle.Model.Empresa
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class EmpresaDAOTest extends GroovyTestCase{

    static Empresa empresa

    @BeforeAll
    static void setUp(){

        empresa = new Empresa(
                "TechSolucione",
                "12.345.678/0001-90",
                "techsolucione@gmail.com",
                "123456",
                "descricao da empresa aqui",
                "brasil",
                "34368-826"
        )

    }

    @Test
    void testCadastrarEmpresa(){

        EmpresaDAO.cadastrar(empresa)

        LinkedList<Empresa> empresas = EmpresaDAO.listar()
        boolean resultado = empresas.find {Empresa e -> {e.cnpj == empresa.cnpj}}

        assertEquals(true, resultado)

    }

    @Test
    void testAlterarEmpresa(){

        String email = "techsol@gmail.com"
        String senha = "654321"
        String pais = "Estados Unidos"
        String cep = "30345"
        String descricaoEmpresa = "Alteracao de descricao aqui"

        EmpresaDAO.alterar(empresa.cnpj, email, senha, pais, cep, descricaoEmpresa)

        LinkedList<Empresa> empresas = EmpresaDAO.listar()
        Empresa resultado = empresas.find {Empresa e -> {e.cnpj == empresa.cnpj}}

        assertEquals(resultado.email, email)
        assertEquals(resultado.senha, senha)
        assertEquals(resultado.pais, pais)
        assertEquals(resultado.cep, cep)
        assertEquals(resultado.descricaoEmpresa, descricaoEmpresa)

    }

    @Test
    void testDeletarEmpresa() {

        EmpresaDAO.deletar("12.345.678/0001-90")

        LinkedList<Empresa> empresas = EmpresaDAO.listar()
        boolean resultado = empresas.find {Empresa e -> {e.cnpj == empresa.cnpj}}

        assertEquals(false, resultado)

    }

}
