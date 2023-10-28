package linketinder.projeto.gradle.DAO

import linketinder.projeto.gradle.BD.Factory.ConexaoBDFactory
import linketinder.projeto.gradle.BD.Factory.IConexaoBD

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CurtidasEmpresaDAO {

    static void curtir(String cnpj, String cpf){

        IConexaoBD ConexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String inserir="INSERT INTO Empresa_curte_Candidato(cnpj_empresa, cpf_candidato) VALUES (?, ?)"

        try{

            ConexaoBD.conectar()

            PreparedStatement salvar = ConexaoBD.conn.prepareStatement(inserir)
            salvar.setString(1, cnpj)
            salvar.setString(2, cpf)

            salvar.executeUpdate()
            salvar.close()

            String buscar="SELECT * FROM Candidato_curte_vaga " +
                    "WHERE cpf_candidato=? AND " +
                    "id_vaga=(SELECT id_vaga FROM Vagas WHERE cnpj_empresa=?)"


            PreparedStatement like=ConexaoBD.conn.prepareStatement(
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

            ConexaoBD.desconectar()

        }catch (Exception e){

            e.printStackTrace()

        }


    }

    static void deletarPorCpf(String cpf){

        IConexaoBD ConexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String deletar="DELETE FROM Empresa_curte_candidato WHERE cpf_candidato=?"
        String buscar="SELECT * FROM Empresa_curte_candidato WHERE cpf_candidato=?"

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

            }

        }catch (Exception e){

            e.printStackTrace()

        }
    }

    static void deletarPorCnpj(String cnpj){

        IConexaoBD ConexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String deletar="DELETE FROM Empresa_curte_candidato WHERE cnpj_empresa=?"
        String buscar="SELECT * FROM Empresa_curte_candidato WHERE cnpj_empresa=?"

        try{

            ConexaoBD.conectar()
            PreparedStatement like=ConexaoBD.conn.prepareStatement(
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

                PreparedStatement delete = ConexaoBD.conn.prepareStatement(deletar)
                delete.setString(1, cnpj)
                delete.executeUpdate()
                delete.close()
                ConexaoBD.desconectar()


            }

        }catch (Exception e){

            e.printStackTrace()

        }
    }
}
