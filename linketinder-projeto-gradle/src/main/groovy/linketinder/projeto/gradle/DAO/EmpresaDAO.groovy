package linketinder.projeto.gradle.DAO
import linketinder.projeto.gradle.Utils.ConexaoBD
import linketinder.projeto.gradle.Model.Empresa
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class EmpresaDAO {

    static void cadastrar(Empresa empresa){

        String inserir="INSERT INTO Empresas(nome, cnpj, email, senha, descricao_empresa, pais, cep) VALUES (?, ?, ?, ?, ?, ?, ?)"

        try{

            Connection conn=ConexaoBD.conectar()

            PreparedStatement salvar = conn.prepareStatement(inserir)
            salvar.setString(1, empresa.nome)
            salvar.setString(2, empresa.cnpj)
            salvar.setString(3, empresa.email)
            salvar.setString(4, empresa.senha)
            salvar.setString(5, empresa.descricaoEmpresa)
            salvar.setString(6, empresa.pais)
            salvar.setString(7, empresa.cep)

            salvar.executeUpdate()
            salvar.close()
            ConexaoBD.desconectar(conn)


        }catch (Exception e){

            e.printStackTrace()

        }

    }

    static int buscar(String cnpj){

        String buscar="SELECT * FROM Empresas WHERE cnpj=?"

        Connection conn=ConexaoBD.conectar()

        PreparedStatement empresa= conn.prepareStatement(
                buscar,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        )
        empresa.setString(1, cnpj)
        ResultSet result=empresa.executeQuery()

        result.last()
        int quantidade=result.getRow()

        ConexaoBD.desconectar(conn)

        return quantidade
    }

    static void alterar(String cnpj, String email, String senha, String pais, String cep, String descricaoEmpresa){

        try{

            Connection conn=ConexaoBD.conectar()

            String update="UPDATE Empresas SET email=?, senha=?, descricao_empresa=?, pais=?, cep=? WHERE cnpj=?"

            PreparedStatement atualizar = conn.prepareStatement(update)
            atualizar.setString(1, email)
            atualizar.setString(2, senha)
            atualizar.setString(3, descricaoEmpresa)
            atualizar.setString(4, pais)
            atualizar.setString(5, cep)
            atualizar.setString(6, cnpj)

            atualizar.executeUpdate()
            atualizar.close()
            ConexaoBD.desconectar(conn)


        }catch (Exception e){

            e.printStackTrace()

        }

    }

    static void deletar(String cnpj){

        String deletar="DELETE FROM Empresas WHERE cnpj=?"

        VagaDAO.deletarPorEmpresa(cnpj)
        CurtidasEmpresaDAO.deletarPorCnpj(cnpj)
        MatchDAO.deletarPorCnpj(cnpj)

        try{

            Connection conn=ConexaoBD.conectar()

            PreparedStatement delete = conn.prepareStatement(deletar)
            delete.setString(1, cnpj)
            delete.executeUpdate()
            delete.close()
            ConexaoBD.desconectar(conn)


        }catch (Exception e){

            e.printStackTrace()

        }
    }

    static LinkedList<Empresa> listar(){

        String buscar="SELECT * FROM Empresas"

        LinkedList<Empresa> listaEmpresas = new LinkedList<Empresa>()
        try{

            Connection conn= ConexaoBD.conectar()
            PreparedStatement empresas=conn.prepareStatement(
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

            ConexaoBD.desconectar(conn)
            return listaEmpresas

        }catch(Exception e){

            e.printStackTrace()

        }
    }
}
