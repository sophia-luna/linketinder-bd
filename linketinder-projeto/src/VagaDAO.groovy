import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class VagaDAO {

    static void cadastrar(){

        print("Nome da vaga: ")
        String nome=System.in.newReader().readLine()

        print("ID da Vaga: ")
        String id=System.in.newReader().readLine()

        print("Descrição da Vaga: : ")
        String descricao=System.in.newReader().readLine()

        print("Estado: ")
        String estado=System.in.newReader().readLine()

        print("Cidade: ")
        String cidade=System.in.newReader().readLine()

        print("CNPJ da empresa: ")
        String cnpj=System.in.newReader().readLine()

        String inserir="INSERT INTO Vagas(nome, id, descricao_vaga, estado, cidade, cnpj_empresa) VALUES (?, ?, ?, ?, ?, ?)"

        try{

            Connection conn=ConexaoBD.conectar()

            PreparedStatement salvar = conn.prepareStatement(inserir)
            salvar.setString(1, nome)
            salvar.setString(2, id)
            salvar.setString(3, descricao)
            salvar.setString(4, estado)
            salvar.setString(5, cidade)
            salvar.setString(6, cnpj)

            salvar.executeUpdate()
            salvar.close()
            ConexaoBD.desconectar(conn)
            println("A vaga $nome foi inserida.")

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao cadastrar empresa.")
        }

    }

    static void alterar(){

        print("ID da vaga que deseja alterar: ")
        String id=System.in.newReader().readLine()

        String buscar="SELECT * FROM Vagas WHERE id=?"

        try{

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
            result.beforeFirst()

            if(quantidade){

                print("Descrição da Vaga: : ")
                String descricao=System.in.newReader().readLine()

                print("Estado: ")
                String estado=System.in.newReader().readLine()

                print("Cidade: ")
                String cidade=System.in.newReader().readLine()

                String update="UPDATE Vagas SET descricao=?, estado=?, cidade=? WHERE id=?"

                PreparedStatement atualizar = conn.prepareStatement(update)
                atualizar.setString(1, descricao)
                atualizar.setString(2, estado)
                atualizar.setString(3, cidade)
                atualizar.setString(4, id)

                atualizar.executeUpdate()
                atualizar.close()
                ConexaoBD.desconectar(conn)
                println("Os dados da vaga foram atualizados.")

            }else{
                println("Vaga não encontrada.")
            }

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao atualizar vaga.")

        }

    }

    static void deletar(id){

        String deletar="DELETE FROM Vagas WHERE id=?"
        String buscar="SELECT * FROM Vagas WHERE id=?"


        CompetenciaVagaDAO.deletarPorVaga(id)

        try{

            Connection conn=ConexaoBD.conectar()
            PreparedStatement vaga=conn.prepareStatement(
                    buscar,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            vaga.setString(1, id)
            ResultSet result=vaga.executeQuery()

            result.last()
            int quantidade=result.getRow()
            result.beforeFirst()

            if(quantidade) {

                PreparedStatement delete = conn.prepareStatement(deletar)
                delete.setString(1, id)
                delete.executeUpdate()
                delete.close()
                ConexaoBD.desconectar(conn)

                println("A vaga foi deletada.")

            }else{
                println("Vaga não encontrada.")
            }

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

                while(result.next()){
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
