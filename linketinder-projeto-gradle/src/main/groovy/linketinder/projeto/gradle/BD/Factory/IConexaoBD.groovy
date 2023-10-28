package linketinder.projeto.gradle.BD.Factory

import java.sql.Connection

interface IConexaoBD {

    Connection conn

    void conectar()
    void desconectar()

}
