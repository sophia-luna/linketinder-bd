package linketinder.projeto.gradle.Model

class Competencia {

    public String nome
    Competencia(String nome) {
        this.nome = nome
    }

    @Override
    String toString() {

        return """
        \n\nNome da Competencia: $nome
        """

    }
}
