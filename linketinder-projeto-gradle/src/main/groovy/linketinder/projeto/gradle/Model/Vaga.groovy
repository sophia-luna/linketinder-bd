package linketinder.projeto.gradle.Model

class Vaga {

    String nome
    String id
    String descricao
    String estado
    String cidade
    String cnpjEmpresa

    Vaga (String nome, String id, String descricao, String estado, String cidade, String cnpjEmpresa){
        this.nome=nome
        this.id=id
        this.descricao=descricao
        this.estado=estado
        this.cidade=cidade
        this.cnpjEmpresa=cnpjEmpresa
    }

    @Override
    String toString() {

        return """
        \n\nNome: $nome
        ID: $id
        Descricao da vaga: $descricao
        CNPJ da emepresa: $cnpjEmpresa
        Estado: $estado
        Cidade: $cidade
        """

    }
}