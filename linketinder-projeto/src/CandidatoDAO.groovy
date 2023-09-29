import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.util.concurrent.ExecutionException

class CandidatoDAO {

     static void cadastrar(){

        print("Nome do Candidato: ")
        String nome=System.in.newReader().readLine()

        print("Sobrenome do Candidato: ")
        String sobrenome=System.in.newReader().readLine()

        print("CPF do Candidato: ")
        String cpf=System.in.newReader().readLine()

        print("Data de nascimento do Candidato: ")
        String dataNascimento=System.in.newReader().readLine()

        print("Email do Candidato: ")
        String email=System.in.newReader().readLine()

        print("Senha:")
        String senha=System.in.newReader().readLine()

        print("Descrição Pessoal: ")
        String descricaoPessoal=System.in.newReader().readLine()

        print("País: ")
        String pais=System.in.newReader().readLine()

        print("CEP: ")
        String cep=System.in.newReader().readLine()

        String inserir="INSERT INTO Candidatos(nome, sobrenome, cpf, email, data_nascimento, senha, descricao_pessoal, pais, cep) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"

        try{

            Connection conn=ConexaoBD.conectar()

            PreparedStatement salvar = conn.prepareStatement(inserir)
            salvar.setString(1, nome)
            salvar.setString(2, sobrenome)
            salvar.setString(3, cpf)
            salvar.setString(4, email)
            salvar.setString(5, dataNascimento)
            salvar.setString(6, senha)
            salvar.setString(7, descricaoPessoal)
            salvar.setString(8, pais)
            salvar.setString(9, cep)

            salvar.executeUpdate()
            salvar.close()
            ConexaoBD.desconectar(conn)
            println("O candidato $nome foi inserido.")

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao cadastrar candidato.")
        }

    }

    static void alterar(){

        print("CPF do Candidato que deseja alterar: ")
        String cpf=System.in.newReader().readLine()

        String buscar="SELECT * FROM Candidatos WHERE cpf=?"

        try{

            Connection conn=ConexaoBD.conectar()

            PreparedStatement candidato= conn.prepareStatement(
                    buscar,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            candidato.setString(1, cpf)
            ResultSet result=candidato.executeQuery()

            result.last()
            int quantidade=result.getRow()
            result.beforeFirst()

            if(quantidade){

                print("Email do Candidato: ")
                String email=System.in.newReader().readLine()

                print("Senha:")
                String senha=System.in.newReader().readLine()

                print("Descrição Pessoal: ")
                String descricaoPessoal=System.in.newReader().readLine()

                print("País: ")
                String pais=System.in.newReader().readLine()

                print("CEP: ")
                String cep=System.in.newReader().readLine()

                String update="UPDATE Candidatos SET email=?, senha=?, descricao_pessoal=?, pais=?, cep=? WHERE cpf=?"

                PreparedStatement atualizar = conn.prepareStatement(update)
                atualizar.setString(1, email)
                atualizar.setString(2, senha)
                atualizar.setString(3, descricaoPessoal)
                atualizar.setString(4, pais)
                atualizar.setString(5, cep)
                atualizar.setString(6, cpf)

                atualizar.executeUpdate()
                atualizar.close()
                ConexaoBD.desconectar(conn)
                println("Os dados do candidato foram atualizados.")

            }else{
                println("Candidato não encontrado.")
            }

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao atualizar candidato.")

        }

    }

    static void deletar(){

        String deletar="DELETE FROM Candidatos WHERE cpf=?"
        String buscar="SELECT * FROM Candidatos WHERE cpf=?"

        println("Insira o CPF do candidato que deseja deletar: ")
        String cpf=System.in.newReader().readLine()

        CompetenciaCandidatoDAO.deletarPorCandidato(cpf)

        try{

            Connection conn=ConexaoBD.conectar()
            PreparedStatement candidato=conn.prepareStatement(
                    buscar,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            candidato.setString(1, cpf)
            ResultSet result=candidato.executeQuery()

            result.last()
            int quantidade=result.getRow()
            result.beforeFirst()

            if(quantidade) {

                PreparedStatement delete = conn.prepareStatement(deletar)
                delete.setString(1, cpf)
                delete.executeUpdate()
                delete.close()
                ConexaoBD.desconectar(conn)

                println("O candidato foi deletado.")

            }else{
                println("Candidato não encontrado.")
            }

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao deletar candidato.")

        }
    }

    static void listar(){

        String buscar="SELECT * FROM Candidatos"

        try{

            Connection conn= ConexaoBD.conectar()
            PreparedStatement candidatos=conn.prepareStatement(
                    buscar,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            ResultSet result=candidatos.executeQuery()

            result.last()
            int quantidade=result.getRow()
            result.beforeFirst()

            if(quantidade>0){

                println("Listando todos os candidatos...\n")

                while(result.next()){
                    println("\n\nNome: " + result.getString(1))
                    println("Sobrenome: " + result.getString(2))
                    println("CPF: " + result.getString(3))
                    println("Email: " + result.getString(4))
                    println("Data de nascimento: " + result.getString(5))
                    println("Senha: " + result.getString(6))
                    println("Descrição Pessoal: " + result.getString(7))
                    println("País: " + result.getString(8))
                    println("CEP: " + result.getString(9))
                }
                ConexaoBD.desconectar(conn)

            }else{
                println("Nenhum candidato cadastrado.")
            }

        }catch(Exception e){

            e.printStackTrace()
            println("Erro ao buscar candidatos.")
        }
    }
}
