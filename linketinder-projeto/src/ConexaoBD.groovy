import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException


class ConexaoBD {

    static Connection conectar(){

        Properties props = new Properties()
        props.setProperty("user", "postgres")
        props.setProperty("password", "postgres")
        props.setProperty("ssl", "false")

        String url_servidor = "jdbc:postgresql://localhost:5432/linketinder_bd"

        try{

            return DriverManager.getConnection(url_servidor, props)

        } catch(Exception e){

            e.printStackTrace()
            if(e instanceof  ClassNotFoundException){
                println("Verifique o driver de conexao.")
            } else {
                println("Verifique se o servidor esta ativo.")
            }
            System.exit(-42)
            return null

        }
    }

    static void desconectar(Connection conn){

        if(conn!=null){
            try{
                conn.close()
            }
            catch (SQLException e){
                e.printStackTrace()
            }
        }

    }

}
