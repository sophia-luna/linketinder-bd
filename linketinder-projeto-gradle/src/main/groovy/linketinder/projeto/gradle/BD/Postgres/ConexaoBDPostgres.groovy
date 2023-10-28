package linketinder.projeto.gradle.BD.Postgres

import linketinder.projeto.gradle.BD.Factory.IConexaoBD

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException


class ConexaoBDPostgres implements IConexaoBD{

    private static ConexaoBDPostgres instance = new ConexaoBDPostgres()

    private ConexaoBDPostgres(){}

    static ConexaoBDPostgres getInstance() {
        return instance
    }

    Connection conn

    void conectar(){

        Properties props = new Properties()
        props.setProperty("user", "postgres")
        props.setProperty("password", "postgres")
        props.setProperty("ssl", "false")

        String url_servidor = "jdbc:postgresql://localhost:5432/linketinder_bd"

        try{

            this.conn = DriverManager.getConnection(url_servidor, props)

        } catch(Exception e){

            e.printStackTrace()
            if(e instanceof  ClassNotFoundException){
                println("Verifique o driver de conexao.")
            } else {
                println("Verifique se o servidor esta ativo.")
            }
            System.exit(-42)

        }
    }

    void desconectar(){

        if(this.conn!=null){
            try{
                this.conn.close()
            }
            catch (SQLException e){
                e.printStackTrace()
            }
        }

    }

}
