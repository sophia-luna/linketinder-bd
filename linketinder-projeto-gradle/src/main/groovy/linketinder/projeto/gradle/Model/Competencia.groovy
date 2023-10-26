package linketinder.projeto.gradle.Model

class Competencia {

    public String nome
    Competencia(String nome) {
        this.nome = nome
    }

    @Override
    String toString() {

        return """
        \n\n
        Nome da Competencia: $nome
        """

    }
}
