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
            println("A empresa $empresa.nome foi inserida.")

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao cadastrar empresa.")
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

            println("Os dados da empresa foram atualizados.")


        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao atualizar empresa.")

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

            println("A empresa foi deletada.")

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao deletar empresa.")

        }
    }

    static void listar(){

        String buscar="SELECT * FROM Empresas"

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

                println("Listando todas as empresas...\n")

                while(result.next()){
                    println("\n\nNome: " + result.getString(1))
                    println("CNPJ: " + result.getString(2))
                    println("Email: " + result.getString(3))
                    println("Senha: " + result.getString(4))
                    println("Descrição da Empresa: " + result.getString(5))
                    println("País: " + result.getString(6))
                    println("CEP: " + result.getString(7))
                }
                ConexaoBD.desconectar(conn)

            }else{
                println("Nenhuma empresa cadastrada.")
            }

        }catch(Exception e){

            e.printStackTrace()
            println("Erro ao buscar empresas.")
        }
    }
}
