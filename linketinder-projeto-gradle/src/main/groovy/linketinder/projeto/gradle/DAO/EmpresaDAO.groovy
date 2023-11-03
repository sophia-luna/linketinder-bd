package linketinder.projeto.gradle.DAO

import linketinder.projeto.gradle.BD.Factory.ConexaoBDFactory
import linketinder.projeto.gradle.BD.Factory.IConexaoBD
import linketinder.projeto.gradle.Model.Empresa
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class EmpresaDAO {

    static void cadastrar(Empresa empresa){

        IConexaoBD ConexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String inserir="INSERT INTO Empresas(nome, cnpj, email, senha, descricao_empresa, pais, cep) VALUES (?, ?, ?, ?, ?, ?, ?)"

        try{

            ConexaoBD.conectar()

            PreparedStatement salvar = ConexaoBD.conn.prepareStatement(inserir)
            salvar.setString(1, empresa.nome)
            salvar.setString(2, empresa.cnpj)
            salvar.setString(3, empresa.email)
            salvar.setString(4, empresa.senha)
            salvar.setString(5, empresa.descricaoEmpresa)
            salvar.setString(6, empresa.pais)
            salvar.setString(7, empresa.cep)

            salvar.executeUpdate()
            salvar.close()
            ConexaoBD.desconectar()


        }catch (Exception e){

            e.printStackTrace()

        }

    }

    static int buscar(String cnpj){

        IConexaoBD ConexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String buscar="SELECT * FROM Empresas WHERE cnpj=?"

        try{

            ConexaoBD.conectar()

            PreparedStatement empresa= ConexaoBD.conn.prepareStatement(
                    buscar,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            empresa.setString(1, cnpj)
            ResultSet result=empresa.executeQuery()

            result.last()
            int quantidade=result.getRow()

            ConexaoBD.desconectar()

            return quantidade

        } catch(Exception e) {

            e.printStackTrace()
        }
    }

    static void alterar(String cnpj, String email, String senha, String pais, String cep, String descricaoEmpresa){

        IConexaoBD ConexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        try{

            ConexaoBD.conectar()

            String update="UPDATE Empresas SET email=?, senha=?, descricao_empresa=?, pais=?, cep=? WHERE cnpj=?"

            PreparedStatement atualizar = ConexaoBD.conn.prepareStatement(update)
            atualizar.setString(1, email)
            atualizar.setString(2, senha)
            atualizar.setString(3, descricaoEmpresa)
            atualizar.setString(4, pais)
            atualizar.setString(5, cep)
            atualizar.setString(6, cnpj)

            atualizar.executeUpdate()
            atualizar.close()
            ConexaoBD.desconectar()


        }catch (Exception e){

            e.printStackTrace()

        }

    }

    static void deletar(String cnpj){

        IConexaoBD ConexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String deletar="DELETE FROM Empresas WHERE cnpj=?"

        VagaDAO.deletarPorEmpresa(cnpj)
        CurtidasEmpresaDAO.deletarPorCnpj(cnpj)
        MatchDAO.deletarPorCnpj(cnpj)

        try{

            ConexaoBD.conectar()

            PreparedStatement delete = ConexaoBD.conn.prepareStatement(deletar)
            delete.setString(1, cnpj)
            delete.executeUpdate()
            delete.close()
            ConexaoBD.desconectar()


        }catch (Exception e){

            e.printStackTrace()

        }
    }

    static LinkedList<Empresa> listar(){

        IConexaoBD ConexaoBD = ConexaoBDFactory.getConexaoBD("PostgreSQL")

        String buscar="SELECT * FROM Empresas"

        LinkedList<Empresa> listaEmpresas = new LinkedList<Empresa>()
        try{

            ConexaoBD.conectar()
            PreparedStatement empresas=ConexaoBD.conn.prepareStatement(
                    buscar,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            ResultSet result=empresas.executeQuery()

            result.last()
            int quantidade=result.getRow()
            result.beforeFirst()

            if(quantidade>0){

                while(result.next()){
                    Empresa empresa = new Empresa(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7) )
                    listaEmpresas.add(empresa)
                }

            }

            ConexaoBD.desconectar()
            return listaEmpresas

        }catch(Exception e){

            e.printStackTrace()

        }
    }
}
