package linketinder.projeto.gradle.Model

class Match {

    String cpfCandidato
    String cnpjEmpresa

    Match(String cpfCandidato, String cnpjEmpresa){
        this.cpfCandidato = cpfCandidato
        this.cnpjEmpresa = cnpjEmpresa
    }

    @Override
    String toString(){

        return """
        CPF do candidato: $cpfCandidato
        CNPJ da empresa: $cnpjEmpresa
        """

    }
}
