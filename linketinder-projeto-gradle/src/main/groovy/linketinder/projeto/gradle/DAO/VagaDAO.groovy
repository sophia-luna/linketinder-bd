package linketinder.projeto.gradle.DAO
import linketinder.projeto.gradle.Utils.ConexaoBD
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import linketinder.projeto.gradle.Model.Vaga

class VagaDAO {

    static void cadastrar(Vaga vaga){

        String inserir="INSERT INTO Vagas(nome, id, descricao_vaga, estado, cidade, cnpj_empresa) VALUES (?, ?, ?, ?, ?, ?)"

        try{

            Connection conn=ConexaoBD.conectar()

            PreparedStatement salvar = conn.prepareStatement(inserir)
            salvar.setString(1, vaga.nome)
            salvar.setString(2, vaga.id)
            salvar.setString(3, vaga.descricao)
            salvar.setString(4, vaga.estado)
            salvar.setString(5, vaga.cidade)
            salvar.setString(6, vaga.cnpjEmpresa)

            salvar.executeUpdate()
            salvar.close()
            ConexaoBD.desconectar(conn)
            println("A vaga $vaga.nome foi inserida.")

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao cadastrar empresa.")
        }

    }

    static int buscar(String id){

        String buscar="SELECT * FROM Vagas WHERE id=?"

        Connection conn=ConexaoBD.conectar()

        PreparedStatement vaga= conn.prepareStatement(
                buscar,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        )
        vaga.setString(1, id)
        ResultSet result=vaga.executeQuery()

        result.last()
        int quantidade=result.getRow()

        ConexaoBD.desconectar(conn)

        return quantidade

    }


    static void alterar(String id, String descricao, String estado, String cidade){

        try{

            Connection conn=ConexaoBD.conectar()

            String update="UPDATE Vagas SET descricao_vaga=?, estado=?, cidade=? WHERE id=?"

            PreparedStatement atualizar = conn.prepareStatement(update)
            atualizar.setString(1, descricao)
            atualizar.setString(2, estado)
            atualizar.setString(3, cidade)
            atualizar.setString(4, id)

            atualizar.executeUpdate()
            atualizar.close()
            ConexaoBD.desconectar(conn)

            println("Os dados da vaga foram atualizados.")

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao atualizar vaga.")

        }

    }

    static void deletar(String id){

        String deletar="DELETE FROM Vagas WHERE id=?"

        CompetenciaVagaDAO.deletarPorVaga(id)
        CurtidasCandidatoDAO.deletarPorId(id)

        try{

            Connection conn=ConexaoBD.conectar()

            PreparedStatement delete = conn.prepareStatement(deletar)
            delete.setString(1, id)
            delete.executeUpdate()
            delete.close()
            ConexaoBD.desconectar(conn)

            println("A vaga foi deletada.")

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao deletar vaga.")

        }
    }

    static void listar(){

        String buscar="SELECT * FROM Vagas"

        try{

            Connection conn= ConexaoBD.conectar()
            PreparedStatement vagas=conn.prepareStatement(
                    buscar,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            ResultSet result=vagas.executeQuery()

            result.last()
            int quantidade=result.getRow()
            result.beforeFirst()

            if(quantidade>0){

                println("Listando todas as vagas...\n")

                while(result.next()){
                    println("\n\nNome: " + result.getString(1))
                    println("ID: " + result.getString(2))
                    println("Descrição da vaga: " + result.getString(3))
                    println("Estado: " + result.getString(4))
                    println("Cidade: " + result.getString(5))
                    println("CNPJ da empresa: " + result.getString(6))
                }
                ConexaoBD.desconectar(conn)

            }else{
                println("Nenhuma vaga cadastrada.")
            }

        }catch(Exception e){

            e.printStackTrace()
            println("Erro ao buscar vagas.")
        }
    }

    static void deletarPorEmpresa(cnpj){

        String buscar="SELECT id FROM Vagas WHERE cnpj_empresa=?"

        try{

            Connection conn=ConexaoBD.conectar()
            PreparedStatement vaga=conn.prepareStatement(
                    buscar,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            vaga.setString(1, cnpj)
            ResultSet result=vaga.executeQuery()

            result.last()
            int quantidade=result.getRow()
            result.beforeFirst()

            if(quantidade) {
                while (result.next()){
                    VagaDAO.deletar(result.getString(1))
                }
            }else{
                println("Empresa não tem vagas...")
            }

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao deletar vagas da empresa.")

        }
    }
}
