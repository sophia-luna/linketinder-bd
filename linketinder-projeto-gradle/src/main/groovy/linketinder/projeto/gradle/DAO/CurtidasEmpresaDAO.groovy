package linketinder.projeto.gradle.DAO
import linketinder.projeto.gradle.Utils.ConexaoBD
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CurtidasEmpresaDAO {

    static void curtir(String cnpj, String cpf){

        String inserir="INSERT INTO Empresa_curte_Candidato(cnpj_empresa, cpf_candidato) VALUES (?, ?)"

        try{

            Connection conn=ConexaoBD.conectar()

            PreparedStatement salvar = conn.prepareStatement(inserir)
            salvar.setString(1, cnpj)
            salvar.setString(2, cpf)

            salvar.executeUpdate()
            salvar.close()

            println("Empresa curtiu candidato.")

            //verificar se candidato curtiu empresa
            String buscar="SELECT * FROM Candidato_curte_vaga " +
                    "WHERE cpf_candidato=? AND " +
                    "id_vaga=(SELECT id_vaga FROM Vagas WHERE cnpj_empresa=?)"


            PreparedStatement like=conn.prepareStatement(
                    buscar,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            like.setString(1, cpf)
            like.setString(2, cnpj)
            ResultSet result=like.executeQuery()

            result.last()
            int quantidade=result.getRow()
            result.beforeFirst()

            if(quantidade){
                MatchDAO.match(cpf, cnpj)
            }

            ConexaoBD.desconectar(conn)

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao curtir candidato.")
        }


    }

    static void deletarPorCpf(String cpf){

        String deletar="DELETE FROM Empresa_curte_candidato WHERE cpf_candidato=?"
        String buscar="SELECT * FROM Empresa_curte_candidato WHERE cpf_candidato=?"

        try{

            Connection conn=ConexaoBD.conectar()
            PreparedStatement like=conn.prepareStatement(
                    buscar,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            like.setString(1, cpf)
            ResultSet result=like.executeQuery()

            result.last()
            int quantidade=result.getRow()
            result.beforeFirst()

            if(quantidade) {

                PreparedStatement delete = conn.prepareStatement(deletar)
                delete.setString(1, cpf)
                delete.executeUpdate()
                delete.close()
                ConexaoBD.desconectar(conn)

                println("Curtidas dadas no candidato deletadas.")

            }else{
                println("Candidato não foi curtido...")
            }

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao deletar curtidas dadas no candidato.")

        }
    }

    static void deletarPorCnpj(String cnpj){

        String deletar="DELETE FROM Empresa_curte_candidato WHERE cnpj_empresa=?"
        String buscar="SELECT * FROM Empresa_curte_candidato WHERE cnpj_empresa=?"

        try{

            Connection conn=ConexaoBD.conectar()
            PreparedStatement like=conn.prepareStatement(
                    buscar,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            like.setString(1, cnpj)
            ResultSet result=like.executeQuery()

            result.last()
            int quantidade=result.getRow()
            result.beforeFirst()

            if(quantidade) {

                PreparedStatement delete = conn.prepareStatement(deletar)
                delete.setString(1, cnpj)
                delete.executeUpdate()
                delete.close()
                ConexaoBD.desconectar(conn)

                println("Curtidas da empresa deletadas.")

            }else{
                println("Empresa não tem curtidas...")
            }

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao deletar curtidas da empresa.")

        }
    }
}
