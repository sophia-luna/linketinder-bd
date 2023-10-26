package linketinder.projeto.gradle.Model

class Empresa extends Pessoa {

    public String cnpj
    public String descricaoEmpresa

    Empresa(String nome, String cnpj, String email, String senha, String descricaoEmpresa, String pais, String cep) {
        this.nome=nome
        this.email=email
        this.cnpj=cnpj
        this.senha=senha
        this.pais=pais
        this.cep=cep
        this.descricaoEmpresa=descricaoEmpresa
    }

    @Override
    String toString() {

        return """
        \n\n
        Nome da Empresa: $nome
        CNPJ $cnpj
        Email: $email
        Senha: $senha
        Descricao da Empresa: $descricaoEmpresa
        Pais: $pais
        CEP: $cep
        """

    }
}
