# Linketinder
Linketinder é o backend de um projeto que permite que super usuários criem, atualizem, listem e removam candidatos, empresas e vagas. Além disso, candidatos podem curtir vagas e empresas podem curtir candidatos. Quando uma empresa curte um candidato que já curtiu a vaga da empresa, ou vice-versa, o sistema identifica um "match". 
## Funcionalidades
- Cadastre, atualize, liste e remova candidatos.
- Cadastre, atualize, liste e remova empresas.
- Cadastre, atualize, liste e remova vagas.
- Cadastre competências.
- Adicione competências aos candidatos e vagas (facilitando a percepção de compatibilidade).
- Candidatos podem curtir vagas.
- Empresas podem curtir candidatos.
- Identificação de "matches" entre candidatos e empresas.
## Arquitetura
O projeto segue o padrão de arquitetura MVC (Model-View-Controller) para organizar o código. Os design patterns Singleton e Factory são utilizados para gerenciar a conexão com o banco de dados, fornecendo uma estrutura flexível e eficiente.
