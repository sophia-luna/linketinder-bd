package linketinder.projeto.gradle.DAO

import linketinder.projeto.gradle.BD.Factory.ConexaoBDFactory
import linketinder.projeto.gradle.BD.Factory.IConexaoBD

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet


class CompetenciaCandidatoDAO {

    static void cadastrar(String nome, String cpf){

        IConexaoBD conexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String inserir = "INSERT INTO Competencia_candidato(nome_competencia, cpf_candidato) VALUES (?, ?)"

        try{

            conexaoBD.conectar()

            PreparedStatement salvar = conexaoBD.conn.prepareStatement(inserir)
            salvar.setString(1, nome)
            salvar.setString(2, cpf)

            salvar.executeUpdate()
            salvar.close()

            conexaoBD.desconectar()
            println("A competencia $nome foi inserida para o candidato escolhido.")


        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao adicionar competencia para candidato.")
        }
    }

    static void deletarPorNome(String nome){

        IConexaoBD conexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String buscar = "SELECT * FROM Competencia_candidato WHERE nome_competencia=?"
        String deletar = "DELETE FROM Competencia_candidato WHERE nome_competencia=?"

        try{

            conexaoBD.conectar()

            PreparedStatement competenciaCandidato=conexaoBD.conn.prepareStatement(
                    buscar,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            competenciaCandidato.setString (1, nome)
            ResultSet result=competenciaCandidato.executeQuery()

            result.last()
            int quantidade=result.getRow()
            result.beforeFirst()

            if(quantidade){

                PreparedStatement delete = conexaoBD.conn.prepareStatement(deletar)
                delete.setString(1, nome)

                delete.executeUpdate()
                delete.close()
                conexaoBD.desconectar()
                println("A competencia $nome foi deletada de todos os candidatos.")

            }else{
                "Competencia não encontrada em nenhum candidato..."
            }

            conexaoBD.desconectar()

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao deletar competencia de candidatos.")
        }

    }

    static void deletarPorCandidato(String cpf){

        IConexaoBD conexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String buscar = "SELECT * FROM Competencia_candidato WHERE cpf_candidato=?"
        String deletar = "DELETE FROM Competencia_candidato WHERE cpf_candidato=?"

        try{

            conexaoBD.conectar()

            PreparedStatement competenciaCandidato=conexaoBD.conn.prepareStatement(
                    buscar,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            competenciaCandidato.setString(1, cpf)
            ResultSet result=competenciaCandidato.executeQuery()

            result.last()
            int quantidade=result.getRow()
            result.beforeFirst()

            if(quantidade){

                PreparedStatement delete = conexaoBD.conn.prepareStatement(deletar)
                delete.setString(1, cpf)

                delete.executeUpdate()
                delete.close()
                conexaoBD.desconectar()
                println("O candidato foi deletado da tabela de competencia.")

            }else{
                "Candidato não possui nenhuma competencia..."
            }

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao deletar candidato da tabela de competencia.")
        }

    }
}
