package linketinder.projeto.gradle.Model

class Candidato extends Pessoa {

    public String cpf
    public String dataNascimento
    public String descricaoPessoal
    public String sobrenome

    Candidato(String nome, String sobrenome, String cpf, String email, String dataNascimento, String senha, String descricaoPessoal, String pais, String cep) {
        this.nome = nome
        this.sobrenome = sobrenome
        this.cpf = cpf
        this.dataNascimento = dataNascimento
        this.email = email
        this.senha = senha
        this.pais = pais
        this.cep = cep
        this.descricaoPessoal = descricaoPessoal
    }

    @Override
    String toString() {

        return """
        \n\nNome: $nome
        Sobrenome: $sobrenome 
        CPF: $cpf
        Email: $email
        Data de Nascimento: $dataNascimento
        Senha: $senha
        Descricao Pessoal: $descricaoPessoal
        Pais: $pais
        CEP: $cep
        """

    }

}