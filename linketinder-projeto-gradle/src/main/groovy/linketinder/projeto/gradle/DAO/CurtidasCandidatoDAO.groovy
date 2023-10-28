package linketinder.projeto.gradle.DAO

import linketinder.projeto.gradle.BD.Factory.ConexaoBDFactory
import linketinder.projeto.gradle.BD.Factory.IConexaoBD

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CurtidasCandidatoDAO {

    static void curtir(String cpf, String id){

        IConexaoBD conexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String inserir="INSERT INTO Candidato_curte_vaga(cpf_candidato, id_vaga) VALUES (?, ?)"

        try{

           conexaoBD.conectar()

            PreparedStatement salvar = conexaoBD.conn.prepareStatement(inserir)
            salvar.setString(1, cpf)
            salvar.setString(2, id)

            salvar.executeUpdate()
            salvar.close()

            println("Candidato curtiu vaga.")

            String buscar="SELECT DISTINCT cnpj_empresa " +
                    "FROM Empresa_curte_candidato " +
                    "WHERE cpf_candidato=? AND  " +
                    "cnpj_empresa=(SELECT cnpj_empresa FROM Vagas WHERE id=?)"

            PreparedStatement like=conexaoBD.conn.prepareStatement(
                    buscar,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            like.setString(1, cpf)
            like.setString(2, id)
            ResultSet result=like.executeQuery()

            result.last()
            int quantidade=result.getRow()
            result.beforeFirst()

            if(quantidade){
                while (result.next()){
                    String cnpj=result.getString(1)
                    MatchDAO.match(cpf, cnpj)
                }
            }

            conexaoBD.desconectar()

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao curtir vaga.")
        }


    }

    static void deletarPorCpf(String cpf){

        IConexaoBD ConexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String deletar="DELETE FROM Candidato_curte_vaga WHERE cpf_candidato=?"
        String buscar="SELECT * FROM Candidato_curte_vaga WHERE cpf_candidato=?"

        try{

            ConexaoBD.conectar()
            PreparedStatement like=ConexaoBD.conn.prepareStatement(
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

                PreparedStatement delete = ConexaoBD.conn.prepareStatement(deletar)
                delete.setString(1, cpf)
                delete.executeUpdate()
                delete.close()
                ConexaoBD.desconectar()

                println("Curtidas do candidato deletados.")

            }else{
                println("Candidato não tem curtidas...")
            }

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao deletar curtidas do canidato.")

        }
    }

    static void deletarPorId(String id){

        IConexaoBD ConexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String deletar="DELETE FROM Candidato_curte_vaga WHERE id_vaga=?"
        String buscar="SELECT * FROM Candidato_curte_vaga WHERE id_vaga=?"

        try{

            ConexaoBD.conectar()
            PreparedStatement like=ConexaoBD.conn.prepareStatement(
                    buscar,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            like.setString(1, id)
            ResultSet result=like.executeQuery()

            result.last()
            int quantidade=result.getRow()
            result.beforeFirst()

            if(quantidade) {

                PreparedStatement delete = ConexaoBD.conn.prepareStatement(deletar)
                delete.setString(1, id)
                delete.executeUpdate()
                delete.close()
                ConexaoBD.desconectar()

                println("Curtidas da vaga deletadas.")

            }else{
                println("Vaga não tem curtidas...")
            }

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao deletar curtidas da vaga.")

        }
    }
}