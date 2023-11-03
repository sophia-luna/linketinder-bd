package linketinder.projeto.gradle.DAO

import linketinder.projeto.gradle.BD.Factory.ConexaoBDFactory
import linketinder.projeto.gradle.BD.Factory.IConexaoBD
import linketinder.projeto.gradle.Model.Competencia
import java.sql.PreparedStatement
import java.sql.ResultSet

class CompetenciaDAO {

    static void cadastrar(Competencia competencia){

        IConexaoBD conexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String inserir="INSERT INTO Competencias(nome) VALUES (?)"

        try{

           conexaoBD.conectar()

            PreparedStatement salvar = conexaoBD.conn.prepareStatement(inserir)
            salvar.setString(1, competencia.nome)

            salvar.executeUpdate()
            salvar.close()
            conexaoBD.desconectar()

        }catch (Exception e){

            e.printStackTrace()

        }

    }

    static void deletar(String nome){

        IConexaoBD conexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String deletar="DELETE FROM Competencias WHERE nome=?"
        String buscar="SELECT * FROM Competencias WHERE nome=?"

        CompetenciaCandidatoDAO.deletarPorNome(nome)
        CompetenciaVagaDAO.deletarPorNome(nome)

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

            if(quantidade) {

                PreparedStatement delete = conexaoBD.conn.prepareStatement(deletar)
                delete.setString(1, nome)
                delete.executeUpdate()
                delete.close()

            }

            conexaoBD.desconectar()

        }catch (Exception e){

            e.printStackTrace()

        }
    }

    static boolean buscar(String nome){

        IConexaoBD conexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        try{

            String buscar="SELECT * FROM Competencias WHERE nome=?"

            conexaoBD.conectar()

            PreparedStatement competencia= conexaoBD.conn.prepareStatement(
                    buscar,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            competencia.setString(1, nome)
            ResultSet result=competencia.executeQuery()

            result.last()
            boolean busca=result.getRow()

            conexaoBD.desconectar()

            return busca

        } catch(Exception e) {

            e.printStackTrace()
        }
    }
}
