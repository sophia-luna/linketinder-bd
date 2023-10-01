package DAO
import Utils.ConexaoBD
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CompetenciaVagaDAO {

    static void cadastrar(){

        String buscarCompetencia="SELECT * FROM Competencias WHERE nome=?"
        String buscarVaga="SELECT * FROM Vagas WHERE id=?"

        print("Nome da competencia: ")
        String nome=System.in.newReader().readLine()

        print("ID da vaga: ")
        String id=System.in.newReader().readLine()

        String inserir="INSERT INTO Competencia_empresa(nome_competencia, id_vaga) VALUES (?, ?)"

        try{

            Connection conn=ConexaoBD.conectar()

            PreparedStatement competencia=conn.prepareStatement(
                    buscarCompetencia,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            competencia.setString(1, nome)
            ResultSet result=competencia.executeQuery()

            result.last()
            int quantidade=result.getRow()
            result.beforeFirst()

            PreparedStatement vaga=conn.prepareStatement(
                    buscarVaga,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            vaga.setString(1, id)
            ResultSet res=vaga.executeQuery()

            res.last()
            int quant=res.getRow()
            res.beforeFirst()

            if(quantidade && quant){

                PreparedStatement salvar = conn.prepareStatement(inserir)
                salvar.setString(1, nome)
                salvar.setString(2, id)

                salvar.executeUpdate()
                salvar.close()
                ConexaoBD.desconectar(conn)
                println("A competencia $nome foi inserida para a vaga escolhida.")

            }else{
                println("Vaga e/ou competencia não encontrada(s).")
                println(quantidade)
                println(quant)

            }

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao adicionar competencia para vaga.")
        }
    }

    static void deletarPorNome(nome){

        String buscar="SELECT * FROM Competencia_empresa WHERE nome_competencia=?"
        String deletar="DELETE FROM Competencia_empresa WHERE nome_competencia=?"

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

            if(quantidade){

                PreparedStatement delete = conn.prepareStatement(deletar)
                delete.setString(1, nome)

                delete.executeUpdate()
                delete.close()
                ConexaoBD.desconectar(conn)
                println("A competencia $nome foi deletada de todas as vagas.")

            }else{
                "Competencia não encontrada em nenhuma vaga..."
            }

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao deletar competencia de vagas.")
        }

    }

    static void deletarPorVaga(id){

        String buscar="SELECT * FROM Competencia_empresa WHERE id_vaga=?"
        String deletar="DELETE FROM Competencia_empresa WHERE id_vaga=?"

        try{

            Connection conn=ConexaoBD.conectar()

            PreparedStatement competencia=conn.prepareStatement(
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

                PreparedStatement delete = conn.prepareStatement(deletar)
                delete.setString(1, id)

                delete.executeUpdate()
                delete.close()
                ConexaoBD.desconectar(conn)
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