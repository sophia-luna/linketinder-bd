package DAO

import Utils.ConexaoBD

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet


class CompetenciaCandidatoDAO {

    static void cadastrar(){

        String buscarCompetencia="SELECT * FROM Competencias WHERE nome=?"
        String buscarCandidato="SELECT * FROM Candidatos WHERE cpf=?"

        print("Nome da competencia: ")
        String nome=System.in.newReader().readLine()

        print("CPF do candidato: ")
        String cpf=System.in.newReader().readLine()

        String inserir="INSERT INTO Competencia_candidato(nome_competencia, cpf_candidato) VALUES (?, ?)"

        try{

            Connection conn= ConexaoBD.conectar()

            PreparedStatement buscarComp=conn.prepareStatement(
                    buscarCompetencia,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            buscarComp.setString(1, nome)
            ResultSet result=buscarComp.executeQuery()

            result.last()
            int quantidade=result.getRow()
            result.beforeFirst()

            PreparedStatement buscarCand=conn.prepareStatement(
                    buscarCandidato,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            buscarCand.setString(1, cpf)
            ResultSet res=buscarCand.executeQuery()

            res.last()
            int quant=res.getRow()
            res.beforeFirst()

            if(quantidade && quant){

                PreparedStatement salvar = conn.prepareStatement(inserir)
                salvar.setString(1, nome)
                salvar.setString(2, cpf)

                salvar.executeUpdate()
                salvar.close()
                ConexaoBD.desconectar(conn)
                println("A competencia $nome foi inserida para o candidato escolhido.")

            }else{
                println("Candidato e/ou competencia não encontrado(s).")
            }

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao adicionar competencia para candidato.")
        }
    }

    static void deletarPorNome(nome){

        String buscar="SELECT * FROM Competencia_candidato WHERE nome_competencia=?"
        String deletar="DELETE FROM Competencia_candidato WHERE nome_competencia=?"

        try{

            Connection conn=ConexaoBD.conectar()

            PreparedStatement competenciaCandidato=conn.prepareStatement(
                    buscar,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            competenciaCandidato.setString(1, nome)
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

    static void deletarPorCandidato(cpf){

        String buscar="SELECT * FROM Competencia_candidato WHERE cpf_candidato=?"
        String deletar="DELETE FROM Competencia_candidato WHERE cpf_candidato=?"

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
