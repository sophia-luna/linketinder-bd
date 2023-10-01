package DAO

import Utils.ConexaoBD
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import Model.Candidato
import java.util.concurrent.ExecutionException

class CandidatoDAO {

     static void cadastrar(Candidato candidato){

        String inserir="INSERT INTO Candidatos(nome, sobrenome, cpf, email, data_nascimento, senha, descricao_pessoal, pais, cep) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"

        try{

            Connection conn=ConexaoBD.conectar()

            PreparedStatement salvar = conn.prepareStatement(inserir)
            salvar.setString(1, candidato.nome)
            salvar.setString(2, candidato.sobrenome)
            salvar.setString(3, candidato.cpf)
            salvar.setString(4, candidato.email)
            salvar.setString(5, candidato.dataNascimento)
            salvar.setString(6, candidato.senha)
            salvar.setString(7, candidato.descricaoPessoal)
            salvar.setString(8, candidato.pais)
            salvar.setString(9, candidato.cep)

            salvar.executeUpdate()
            salvar.close()
            ConexaoBD.desconectar(conn)
            println("O candidato $candidato.nome foi inserido.")

        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao cadastrar candidato.")
        }

    }

    static int buscar(String cpf){

        String buscar="SELECT * FROM Candidatos WHERE cpf=?"

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

        ConexaoBD.desconectar(conn)

        return quantidade
    }

    static void alterar(String email, String senha, String descricaoPessoal, String pais, String cep, String cpf){


        try{

            Connection conn=ConexaoBD.conectar()

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


        }catch (Exception e){

            e.printStackTrace()
            println("Erro ao atualizar candidato.")

        }

    }

    static void deletar(String cpf){

        String deletar="DELETE FROM Candidatos WHERE cpf=?"

        CompetenciaCandidatoDAO.deletarPorCandidato(cpf)
        CurtidasCandidatoDAO.deletarPorCpf(cpf)
        CurtidasEmpresaDAO.deletarPorCpf(cpf)
        MatchDAO.deletarPorCpf(cpf)

        try{

            Connection conn=ConexaoBD.conectar()

            PreparedStatement delete = conn.prepareStatement(deletar)
            delete.setString(1, cpf)
            delete.executeUpdate()
            delete.close()
            ConexaoBD.desconectar(conn)

            println("O candidato foi deletado.")


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
