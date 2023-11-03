package linketinder.projeto.gradle.DAO

import linketinder.projeto.gradle.BD.Factory.ConexaoBDFactory
import linketinder.projeto.gradle.BD.Factory.IConexaoBD

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import linketinder.projeto.gradle.Model.Vaga

class VagaDAO {

    static void cadastrar(Vaga vaga){

        IConexaoBD ConexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String inserir="INSERT INTO Vagas(nome, id, descricao_vaga, estado, cidade, cnpj_empresa) VALUES (?, ?, ?, ?, ?, ?)"

        try{

            ConexaoBD.conectar()

            PreparedStatement salvar = ConexaoBD.conn.prepareStatement(inserir)
            salvar.setString(1, vaga.nome)
            salvar.setString(2, vaga.id)
            salvar.setString(3, vaga.descricao)
            salvar.setString(4, vaga.estado)
            salvar.setString(5, vaga.cidade)
            salvar.setString(6, vaga.cnpjEmpresa)

            salvar.executeUpdate()
            salvar.close()
            ConexaoBD.desconectar()

        }catch (Exception e){

            e.printStackTrace()

        }

    }

    static int buscar(String id){

        IConexaoBD ConexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String buscar="SELECT * FROM Vagas WHERE id=?"

        try{

            ConexaoBD.conectar()

            PreparedStatement vaga= ConexaoBD.conn.prepareStatement(
                    buscar,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            vaga.setString(1, id)
            ResultSet result=vaga.executeQuery()

            result.last()
            int quantidade=result.getRow()

            ConexaoBD.desconectar()

            return quantidade

        } catch(Exception e) {

            e.printStackTrace()
        }

    }


    static void alterar(String id, String descricao, String estado, String cidade){

        IConexaoBD ConexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        try{

            ConexaoBD.conectar()

            String update="UPDATE Vagas SET descricao_vaga=?, estado=?, cidade=? WHERE id=?"

            PreparedStatement atualizar = ConexaoBD.conn.prepareStatement(update)
            atualizar.setString(1, descricao)
            atualizar.setString(2, estado)
            atualizar.setString(3, cidade)
            atualizar.setString(4, id)

            atualizar.executeUpdate()
            atualizar.close()
            ConexaoBD.desconectar()


        }catch (Exception e){

            e.printStackTrace()

        }

    }

    static void deletar(String id){

        IConexaoBD ConexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String deletar="DELETE FROM Vagas WHERE id=?"

        CompetenciaVagaDAO.deletarPorVaga(id)
        CurtidasCandidatoDAO.deletarPorId(id)

        try{

            ConexaoBD.conectar()

            PreparedStatement delete = ConexaoBD.conn.prepareStatement(deletar)
            delete.setString(1, id)
            delete.executeUpdate()
            delete.close()
            ConexaoBD.desconectar()


        }catch (Exception e){

            e.printStackTrace()

        }
    }

    static LinkedList<Vaga> listar(){

        IConexaoBD ConexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String buscar="SELECT * FROM Vagas"

        LinkedList<Vaga> listaVagas = new LinkedList<Vaga>()

        try{

            ConexaoBD.conectar()
            PreparedStatement vagas=ConexaoBD.conn.prepareStatement(
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

            }

            ConexaoBD.desconectar()
            return listaVagas

        }catch(Exception e){

            e.printStackTrace()

        }
    }

    static void deletarPorEmpresa(String cnpj){

        IConexaoBD ConexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String buscar="SELECT id FROM Vagas WHERE cnpj_empresa=?"

        try{

            ConexaoBD.conectar()
            PreparedStatement vaga=ConexaoBD.conn.prepareStatement(
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
            }

        }catch (Exception e){

            e.printStackTrace()

        }
    }
}
