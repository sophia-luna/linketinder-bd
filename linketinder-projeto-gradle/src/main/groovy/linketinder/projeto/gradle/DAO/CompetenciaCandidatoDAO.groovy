package linketinder.projeto.gradle.DAO

import linketinder.projeto.gradle.Utils.ConexaoBD

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet


class CompetenciaCandidatoDAO {

    static void cadastrar(String nome, String cpf){

        String inserir = "INSERT INTO Competencia_candidato(nome_competencia, cpf_candidato) VALUES (?, ?)"

        try{

            Connection conn= ConexaoBD.conectar()

            PreparedStatement salvar = conn.prepareStatement(inserir)
            salvar.setString(1, nome)
            salvar.setString(2, cpf)

            salvar.executeUpdate()
            salvar.close()

            ConexaoBD.desconectar(conn)
            println("A competencia $nome foi inserida para o candidato escolhido.")


        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao adicionar competencia para candidato.")
        }
    }

    static void deletarPorNome(String nome){

        String buscar = "SELECT * FROM Competencia_candidato WHERE nome_competencia=?"
        String deletar = "DELETE FROM Competencia_candidato WHERE nome_competencia=?"

        try{

            Connection conn=ConexaoBD.conectar()

            PreparedStatement competenciaCandidato=conn.prepareStatement(
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

                PreparedStatement delete = conn.prepareStatement(deletar)
                delete.setString(1, nome)

                delete.executeUpdate()
                delete.close()
                ConexaoBD.desconectar(conn)
                println("A competencia $nome foi deletada de todos os candidatos.")

            }else{
                "Competencia não encontrada em nenhum candidato..."
            }

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao deletar competencia de candidatos.")
        }

    }

    static void deletarPorCandidato(String cpf){

        String buscar = "SELECT * FROM Competencia_candidato WHERE cpf_candidato=?"
        String deletar = "DELETE FROM Competencia_candidato WHERE cpf_candidato=?"

        try{

            Connection conn=ConexaoBD.conectar()

            PreparedStatement competenciaCandidato=conn.prepareStatement(
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

                PreparedStatement delete = conn.prepareStatement(deletar)
                delete.setString(1, cpf)

                delete.executeUpdate()
                delete.close()
                ConexaoBD.desconectar(conn)
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
