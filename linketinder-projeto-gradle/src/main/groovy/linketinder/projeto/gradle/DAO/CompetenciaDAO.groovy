package linketinder.projeto.gradle.DAO

import linketinder.projeto.gradle.Model.Competencia
import linketinder.projeto.gradle.Utils.ConexaoBD
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CompetenciaDAO {

    static void cadastrar(Competencia competencia){

        String inserir="INSERT INTO Competencias(nome) VALUES (?)"

        try{

            Connection conn=ConexaoBD.conectar()

            PreparedStatement salvar = conn.prepareStatement(inserir)
            salvar.setString(1, competencia.nome)

            salvar.executeUpdate()
            salvar.close()
            ConexaoBD.desconectar(conn)

        }catch (Exception e){

            e.printStackTrace()

        }

    }

    static void deletar(String nome){

        String deletar="DELETE FROM Competencias WHERE nome=?"
        String buscar="SELECT * FROM Competencias WHERE nome=?"

        CompetenciaCandidatoDAO.deletarPorNome(nome)
        CompetenciaVagaDAO.deletarPorNome(nome)

        try{

            Connection conn=ConexaoBD.conectar()
            PreparedStatement competencia=conn.prepareStatement(
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

                PreparedStatement delete = conn.prepareStatement(deletar)
                delete.setString(1, nome)
                delete.executeUpdate()
                delete.close()

            }

        }catch (Exception e){

            e.printStackTrace()

        }
    }

    static boolean buscar(String nome){

        String buscar="SELECT * FROM Competencias WHERE nome=?"

        Connection conn=ConexaoBD.conectar()

        PreparedStatement competencia= conn.prepareStatement(
                buscar,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        )
        competencia.setString(1, nome)
        ResultSet result=competencia.executeQuery()

        result.last()
        boolean busca=result.getRow()

        ConexaoBD.desconectar(conn)

        return busca
    }
}
