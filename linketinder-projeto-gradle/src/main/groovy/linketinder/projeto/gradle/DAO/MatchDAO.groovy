package linketinder.projeto.gradle.DAO

import linketinder.projeto.gradle.BD.Factory.ConexaoBDFactory
import linketinder.projeto.gradle.BD.Factory.IConexaoBD
import linketinder.projeto.gradle.Model.Match
import linketinder.projeto.gradle.View.MatchView

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class MatchDAO {

    static void match(String cpf, String cnpj){

        IConexaoBD ConexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String inserir="INSERT INTO Matches(cpf_candidato, cnpj_empresa) VALUES (?, ?)"

        try{

            ConexaoBD.conectar()

            PreparedStatement salvar = ConexaoBD.conn.prepareStatement(inserir)
            salvar.setString(1, cpf)
            salvar.setString(2, cnpj)

            salvar.executeUpdate()
            salvar.close()
            ConexaoBD.desconectar()

            MatchView.match()

        }catch (Exception e){

            e.printStackTrace()

        }

    }

    static void deletarPorCnpj(String cnpj){

        IConexaoBD ConexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String deletar="DELETE FROM Matches WHERE cnpj_empresa=?"
        String buscar="SELECT * FROM Matches WHERE cnpj_empresa=?"

        try{

            ConexaoBD.conectar()
            PreparedStatement match=ConexaoBD.conn.prepareStatement(
                    buscar,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            match.setString(1, cnpj)
            ResultSet result=match.executeQuery()

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

    static void deletarPorCpf(String cpf){

        IConexaoBD ConexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String deletar="DELETE FROM Matches WHERE cpf_candidato=?"
        String buscar="SELECT * FROM Matches WHERE cpf_candidato=?"

        try{

            ConexaoBD.conectar()
            PreparedStatement matches=ConexaoBD.conn.prepareStatement(
                    buscar,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            matches.setString(1, cpf)
            ResultSet result=matches.executeQuery()

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

    static LinkedList<Match> listar(){

        IConexaoBD ConexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String buscar="SELECT * FROM Matches"

        LinkedList<Match> listaMatches = new LinkedList<Match>()

        try{

            ConexaoBD.conectar()
            PreparedStatement matches=ConexaoBD.conn.prepareStatement(
                    buscar,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            ResultSet result=matches.executeQuery()

            result.last()
            int quantidade=result.getRow()
            result.beforeFirst()

            if(quantidade>0){

                while (result.next()){
                    Match match = new Match(result.getString(1), result.getString(2))
                    listaMatches.add(match)
                }

            }

            ConexaoBD.desconectar()
            return listaMatches

        }catch(Exception e){

            e.printStackTrace()

        }
    }

}
