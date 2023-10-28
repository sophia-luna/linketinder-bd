package linketinder.projeto.gradle.DAO

import linketinder.projeto.gradle.BD.Factory.ConexaoBDFactory
import linketinder.projeto.gradle.BD.Factory.IConexaoBD

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CompetenciaVagaDAO {

    static void cadastrar(String nome, String id){

        IConexaoBD conexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String inserir="INSERT INTO Competencia_empresa(nome_competencia, id_vaga) VALUES (?, ?)"

        try{

            conexaoBD.conectar()

            PreparedStatement salvar = conexaoBD.conn.prepareStatement(inserir)
            salvar.setString(1, nome)
            salvar.setString(2, id)

            salvar.executeUpdate()
            salvar.close()

            conexaoBD.desconectar()

        }catch (Exception e){

            e.printStackTrace()

        }
    }

    static void deletarPorNome(String nome){

        IConexaoBD conexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String buscar="SELECT * FROM Competencia_empresa WHERE nome_competencia=?"
        String deletar="DELETE FROM Competencia_empresa WHERE nome_competencia=?"

        try{

            conexaoBD.conectar()

            PreparedStatement competencia=conexaoBD.conn.prepareStatement(
                    buscar,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            competencia.setString(1, nome)
            ResultSet result=competencia.executeQuery()

            result.last()
            int quantidade=result.getRow()
            result.beforeFirst()

            if(quantidade){

                PreparedStatement delete = conexaoBD.conn.prepareStatement(deletar)
                delete.setString(1, nome)

                delete.executeUpdate()
                delete.close()
                conexaoBD.desconectar()

            }

        }catch (Exception e){

            e.printStackTrace()

        }

    }

    static void deletarPorVaga(String id){

        IConexaoBD conexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String buscar="SELECT * FROM Competencia_empresa WHERE id_vaga=?"
        String deletar="DELETE FROM Competencia_empresa WHERE id_vaga=?"

        try{

            conexaoBD.conectar()

            PreparedStatement competencia=conexaoBD.conn.prepareStatement(
                    buscar,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            competencia.setString(1, id)
            ResultSet result=competencia.executeQuery()

            result.last()
            int quantidade=result.getRow()
            result.beforeFirst()

            if(quantidade){

                PreparedStatement delete = conexaoBD.conn.prepareStatement(deletar)
                delete.setString(1, id)

                delete.executeUpdate()
                delete.close()
                conexaoBD.desconectar()

            }

        }catch (Exception e){

            e.printStackTrace()

        }

    }
}
