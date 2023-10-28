package linketinder.projeto.gradle.BD.Factory

import linketinder.projeto.gradle.BD.Postgres.ConexaoBDPostgres


class ConexaoBDFactory {

    static IConexaoBD getConexaoBD(String tipoBD) {

        if(tipoBD == "PostgreSQL") {
            return ConexaoBDPostgres.getInstance()
        }

        println("tipo de BD nao suportado.")
        return null
    }

}
