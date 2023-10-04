package linketinder.projeto.gradle.DAO
import linketinder.projeto.gradle.Utils.ConexaoBD
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CompetenciaDAO {

    static void cadastrar(){

        print("Nome da competencia: ")
        String nome=System.in.newReader().readLine()

        String inserir="INSERT INTO Competencias(nome) VALUES (?)"

        try{

            Connection conn=ConexaoBD.conectar()

            PreparedStatement salvar = conn.prepareStatement(inserir)
            salvar.setString(1, nome)

            salvar.executeUpdate()
            salvar.close()
            ConexaoBD.desconectar(conn)
            println("A competencia $nome foi inserida.")

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao cadastrar competencia.")
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

                println("A competencia foi deletada.")

            }

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao deletar competencia.")

        }
    }
}
