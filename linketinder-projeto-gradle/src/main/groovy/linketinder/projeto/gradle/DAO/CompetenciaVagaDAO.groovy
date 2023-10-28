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
            println("A competencia $nome foi inserida para a vaga escolhida.")


        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao adicionar competencia para vaga.")
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
                println("A competencia $nome foi deletada de todas as vagas.")

            }else{
                "Competencia não encontrada em nenhuma vaga..."
            }

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao deletar competencia de vagas.")
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
                println("A vaga foi deletada da tabela de competencia.")

            }else{
                "Vaga não possui nenhuma competencia..."
            }

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao deletar vaga da tabela de competencia.")
        }

    }
}
