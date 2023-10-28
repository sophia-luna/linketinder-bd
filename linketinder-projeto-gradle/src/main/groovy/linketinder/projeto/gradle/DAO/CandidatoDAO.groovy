package linketinder.projeto.gradle.DAO

import linketinder.projeto.gradle.BD.Factory.ConexaoBDFactory
import linketinder.projeto.gradle.BD.Factory.IConexaoBD

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import linketinder.projeto.gradle.Model.Candidato

class CandidatoDAO {


     static void cadastrar(Candidato candidato){

         IConexaoBD conexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String inserir = "INSERT INTO Candidatos(nome, sobrenome, cpf, email, data_nascimento, senha, descricao_pessoal, pais, cep) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"

        try{

            conexaoBD.conectar()

            PreparedStatement salvar = conexaoBD.conn.prepareStatement(inserir)
            salvar.setString(1, candidato.nome)
            salvar.setString(2, candidato.sobrenome)
            salvar.setString(3, candidato.cpf)
            salvar.setString(4, candidato.email)
            salvar.setString(5, candidato.dataNascimento)
            salvar.setString(6, candidato.senha)
            salvar.setString(7, candidato.descricaoPessoal)
            salvar.setString(8, candidato.pais)
            salvar.setString(9, candidato.cep)

            salvar.executeUpdate()
            salvar.close()
            conexaoBD.desconectar()

        }catch (Exception e){

            e.printStackTrace()

        }

    }

    static boolean buscar(String cpf){

        IConexaoBD conexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String buscar = "SELECT * FROM Candidatos WHERE cpf=?"

        conexaoBD.conectar()

        PreparedStatement candidato= conexaoBD.conn.prepareStatement(
                buscar,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        )
        candidato.setString(1, cpf)
        ResultSet result=candidato.executeQuery()

        result.last()
        boolean busca=result.getRow()

        conexaoBD.desconectar()

        return busca
    }

    static void alterar(String email, String senha, String descricaoPessoal, String pais, String cep, String cpf){

        IConexaoBD conexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        try{

            conexaoBD.conectar()

            String update = "UPDATE Candidatos SET email=?, senha=?, descricao_pessoal=?, pais=?, cep=? WHERE cpf=?"

            PreparedStatement atualizar = conexaoBD.conn.prepareStatement(update)
            atualizar.setString(1, email)
            atualizar.setString(2, senha)
            atualizar.setString(3, descricaoPessoal)
            atualizar.setString(4, pais)
            atualizar.setString(5, cep)
            atualizar.setString(6, cpf)

            atualizar.executeUpdate()
            atualizar.close()
            conexaoBD.desconectar()

        }catch (Exception e){

            e.printStackTrace()

        }

    }

    static void deletar(String cpf){

        IConexaoBD conexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String deletar = "DELETE FROM Candidatos WHERE cpf=?"

        CompetenciaCandidatoDAO.deletarPorCandidato(cpf)
        CurtidasCandidatoDAO.deletarPorCpf(cpf)
        CurtidasEmpresaDAO.deletarPorCpf(cpf)
        MatchDAO.deletarPorCpf(cpf)

        try{

            conexaoBD.conectar()

            PreparedStatement delete = conexaoBD.conn.prepareStatement(deletar)
            delete.setString(1, cpf)
            delete.executeUpdate()
            delete.close()
            conexaoBD.desconectar()



        }catch (Exception e){

            e.printStackTrace()

        }
    }

    static LinkedList<Candidato> listar(){

        IConexaoBD conexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String buscar = "SELECT * FROM Candidatos"

        LinkedList<Candidato> listaCandidatos = new LinkedList<Candidato>()

        try{

            conexaoBD.conectar()
            PreparedStatement candidatos=conexaoBD.conn.prepareStatement(
                    buscar,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            ResultSet result=candidatos.executeQuery()

            result.last()
            int quantidade=result.getRow()
            result.beforeFirst()

            if(quantidade>0){

                while(result.next()) {

                    Candidato candidato = new Candidato(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7), result.getString(8), result.getString(9))
                    listaCandidatos.add(candidato)

                }

            }

            conexaoBD.desconectar()
            return listaCandidatos

        }catch(Exception e){

            e.printStackTrace()

        }
    }
}
