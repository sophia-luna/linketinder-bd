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

    static LinkedList<Vaga> listar(){

        String buscar="SELECT * FROM Vagas"

        LinkedList<Vaga> listaVagas = new LinkedList<Vaga>()

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

                while(result.next()){
                    Vaga vaga = new Vaga(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6),)
                    listaVagas.add(vaga)
                }

            }else{
                println("Nenhuma vaga cadastrada.")
            }

            ConexaoBD.desconectar(conn)
            return listaVagas

        }catch(Exception e){

            e.printStackTrace()
            println("Erro ao buscar vagas.")
        }
    }

    static void deletarPorEmpresa(String cnpj){

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
