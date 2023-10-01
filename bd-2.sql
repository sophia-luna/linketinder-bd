-- tabelas

 CREATE TABLE Candidatos(
	nome VARCHAR(30) NOT NULL,
 	sobrenome VARCHAR(40) NOT NULL,
	cpf VARCHAR(30) PRIMARY KEY,
	email VARCHAR(30) NOT NULL,
 	data_nascimento DATE NOT NULL,
 	senha VARCHAR(20) NOT NULL,
 	descricao_pessoal VARCHAR(400) NOT NULL,
 	pais VARCHAR(30) NOT NULL,
 	cep VARCHAR(20) NOT NULL
 );

 CREATE TABLE Empresas(
	nome VARCHAR(30) NOT NULL,
 	cnpj VARCHAR(30) PRIMARY KEY,
 	email VARCHAR(30) NOT NULL,
 	senha VARCHAR(20) NOT NULL,
 	descricao_empresa VARCHAR(400) NOT NULL,
 	pais VARCHAR(30) NOT NULL,
 	cep VARCHAR(20) NOT NULL
 );

 CREATE TABLE Vagas(
 	nome VARCHAR(30) NOT NULL,
 	id VARCHAR(20) PRIMARY KEY,
 	descricao_vaga VARCHAR(400) NOT NULL,
 	estado VARCHAR(30) NOT NULL,
 	cidade VARCHAR(30) NOT NULL,
 	cnpj_empresa VARCHAR(30) REFERENCES Empresas(cnpj) NOT NULL
 );

 CREATE TABLE Competencias(
 	nome VARCHAR(30) PRIMARY KEY
 );


 CREATE TABLE Competencia_candidato(
 	nome_competencia VARCHAR(30) REFERENCES Competencias(nome) NOT NULL,
 	cpf_candidato VARCHAR(30) REFERENCES Candidatos(cpf) NOT NULL
 );

 CREATE TABLE Competencia_empresa(
 	nome_competencia VARCHAR(30) REFERENCES Competencias(nome) NOT NULL,
	id_vaga VARCHAR(30) REFERENCES Vagas(id) NOT NULL
 );

--insercao

-- candidatos
INSERT INTO Candidatos(nome, sobrenome, cpf, email, data_nascimento, senha, pais, cep, descricao_pessoal) VALUES ('Maria', 'Silva', '123.456.789-01', 'mariasilva@email.com', '12/04/1980', '123456', 'Brasil', '01234-567', 'Sou uma cientista da computação apaixonada pela inteligência artificial e tenho mais de duas décadas de experiência no desenvolvimento de algoritmos inovadores. Meu doutorado em Ciência da Computação pela Universidade de Stanford me deu uma base sólida para liderar projetos complexos.');

INSERT INTO Candidatos(nome, sobrenome, cpf, email, data_nascimento, senha, pais, cep, descricao_pessoal) VALUES ('Gabriel', 'Santos', '987.654.321-02', 'gabrielsantos@email.com', '01/06/1999', '123456', 'Brasil', '04567-890', 'Sou um desenvolvedor de software com cinco anos de experiência em desenvolvimento web e aplicativos móveis. Tenho um diploma em Ciência da Computação e sou especializado em linguagens como JavaScript, Python e frameworks populares como React e Django. Estou em busca de uma oportunidade desafiadora em uma empresa que valorize a inovação e ofereça espaço para crescimento profissional.');

INSERT INTO Candidatos(nome, sobrenome, cpf, email, data_nascimento, senha, pais, cep, descricao_pessoal) VALUES ('Ana', 'Oliveira', '398.456.321-17', 'anaoliveira@email.com', '04/08/2000', '123456', 'Brasil', '07890-123', 'Sou uma analista de dados com três anos de experiência em análise de dados e visualização. Possuo um diploma em Estatística e sou proficiente em ferramentas como Python, SQL e Tableau. Estou buscando uma posição que me permita aplicar minhas habilidades analíticas para resolver problemas de negócios complexos e contribuir para o sucesso da empresa.');

INSERT INTO Candidatos(nome, sobrenome, cpf, email, data_nascimento, senha, pais, cep, descricao_pessoal) VALUES ('Rafael', 'Costa', '045.359.863-59', 'rafaelcosta@email.com', '27/09/1990', '123456', 'Brasil', '56789-012', 'Sou um administrador de sistemas com experiência em gerenciamento de servidores, redes e segurança cibernética. Tenho uma certificação em Administração de Sistemas e sou proficiente em sistemas operacionais Linux e Windows. Estou interessado em uma posição que me permita aplicar minha experiência técnica para manter a infraestrutura de TI de uma empresa funcionando de forma eficiente e segura.');

INSERT INTO Candidatos(nome, sobrenome, cpf, email, data_nascimento, senha, pais, cep, descricao_pessoal) VALUES ('Lucia', 'Mendes', '879.467.358-76', 'luciamendes@email.com', '21/07/1998', '123456', 'Brasil', '12345-123', 'Sou uma designer de interface de usuário (UI) com experiência em criar interfaces intuitivas e atraentes para aplicativos e sites. Tenho um diploma em Design de Interface e sou hábil em ferramentas como Adobe XD e Sketch. Estou em busca de uma oportunidade que me permita colaborar com equipes criativas e trabalhar em projetos desafiadores de design.');
					
																												  
--empresas
INSERT INTO Empresas(nome, cnpj, email, senha, descricao_empresa, pais, cep) VALUES ('InovaTech Solutions', '12.345.678/0001-01', 'inovatech@email.com', '123456', 'A InovaTech Solutions é uma empresa de tecnologia focada em desenvolver soluções inovadoras para impulsionar negócios. Nossa equipe apaixonada por tecnologia está comprometida em criar produtos que transformam indústrias e melhoram a vida das pessoas.', 'Brasil',  '01234-567');

INSERT INTO Empresas(nome, cnpj, email, senha, descricao_empresa, pais, cep) VALUES ('GlobalTrade Inc.', '98.765.432/0001-02', 'globaltrade@email.com', '123456', 'A GlobalTrade Inc. é uma empresa líder em comércio internacional, conectando empresas de todo o mundo. Nossa missão é facilitar negócios globais por meio de soluções de logística avançadas e parcerias estratégicas.', 'Brasil',  '65868-657');

INSERT INTO Empresas(nome, cnpj, email, senha, descricao_empresa, pais, cep) VALUES ('CreativeMinds Agency', '23.456.789/0001-03', 'creativeminds@email.com', 'A CreativeMinds Agency é uma agência de marketing criativo que oferece soluções inovadoras para impulsionar marcas. Nossa equipe talentosa de designers, redatores e estrategistas colabora para criar campanhas que cativam audiências e geram resultados tangíveis.', '123456', 'Brasil',  '34564-908');

INSERT INTO Empresas(nome, cnpj, email, senha, descricao_empresa, pais, cep) VALUES ('GreenTech Co.', '34.567.890/0001-04', 'greentech@email.com', '123456', 'A GreenTech Innovations é uma empresa comprometida com a sustentabilidade e a inovação. Estamos dedicados a desenvolver tecnologias eco-friendly que contribuam para um futuro mais verde e saudável.', 'Brasil',  '01234-567');

INSERT INTO Empresas(nome, cnpj, email, senha, descricao_empresa, pais, cep) VALUES ('HealthCure Solutions', '45.678.901/0001-05', 'healthcure@email.com', '123456', 'A HealthCure Solutions é uma empresa pioneira em soluções de saúde digital. Nossa missão é melhorar o acesso e a qualidade dos cuidados de saúde por meio de plataformas tecnológicas inovadoras, beneficiando pacientes e profissionais da área.', 'Brasil',  '35890-546');


--vagas
INSERT INTO Vagas(nome, id, descricao_vaga, estado, cidade, cnpj_empresa) VALUES ('Desenvolvedor Full Stack', 'VAGA123', 'Estamos em busca de um desenvolvedor de software talentoso para ajudar a criar soluções inovadoras de tecnologia.', 'São Paulo', 'São Paulo', '12.345.678/0001-01');

INSERT INTO Vagas(nome, id, descricao_vaga, estado, cidade, cnpj_empresa) VALUES ('Analista de Logística Internacional', 'VAGA101', 'Procuramos um analista de logística internacional para gerenciar operações de comércio global.', 'Rio de Janeiro', 'Rio de Janeiro', '98.765.432/0001-02');

INSERT INTO Vagas(nome, id, descricao_vaga, estado, cidade, cnpj_empresa) VALUES ('Designer Gráfico', 'VAGA201', 'Estamos contratando um designer gráfico criativo para desenvolver conteúdo visual envolvente.', 'Minas Gerais', 'Belo Horizonte', '23.456.789/0001-03');

INSERT INTO Vagas(nome, id, descricao_vaga, estado, cidade, cnpj_empresa) VALUES ('Engenheiro de Software', 'VAGA303', 'Procuramos um engenheiro dedicado à sustentabilidade para liderar projetos eco-friendly.', 'São Paulo', 'São Paulo', '34.567.890/0001-04');

INSERT INTO Vagas(nome, id, descricao_vaga, estado, cidade, cnpj_empresa) VALUES ('Desenvolvedor Backend', 'VAGA411', 'Estamos em busca de um desenvolvedor experiente para contribuir para soluções de saúde digital inovadoras.', 'São Paulo', 'Campinas', '45.678.901/0001-05');


--competencias
INSERT INTO Competencias(nome) VALUES ('C++');
INSERT INTO Competencias(nome) VALUES ('Python');
INSERT INTO Competencias(nome) VALUES ('Cloud Computing');
INSERT INTO Competencias(nome) VALUES ('Assembly');
INSERT INTO Competencias(nome) VALUES ('Java');
INSERT INTO Competencias(nome) VALUES ('Javascript');
INSERT INTO Competencias(nome) VALUES ('Typescript');
INSERT INTO Competencias(nome) VALUES ('Html/Css');

--competencias candidato
INSERT INTO Competencia_candidato(nome_competencia, cpf_candidato) VALUES ('C++','123.456.789-01');
INSERT INTO Competencia_candidato(nome_competencia, cpf_candidato) VALUES ('Python','123.456.789-01');
INSERT INTO Competencia_candidato(nome_competencia, cpf_candidato) VALUES ('Cloud Computing','123.456.789-01');
INSERT INTO Competencia_candidato(nome_competencia, cpf_candidato) VALUES ('Java','987.654.321-02');
INSERT INTO Competencia_candidato(nome_competencia, cpf_candidato) VALUES ('Javascript','398.456.321-17');
INSERT INTO Competencia_candidato(nome_competencia, cpf_candidato) VALUES ('Typescript','398.456.321-17');
INSERT INTO Competencia_candidato(nome_competencia, cpf_candidato) VALUES ('Java','045.359.863-59');
INSERT INTO Competencia_candidato(nome_competencia, cpf_candidato) VALUES ('Html/Css','879.467.358-76');

--competencias empresa
INSERT INTO Competencia_empresa(nome_competencia, id_vaga) VALUES ('Python','VAGA123');
INSERT INTO Competencia_empresa(nome_competencia, id_vaga) VALUES ('Javascript','VAGA123');
INSERT INTO Competencia_empresa(nome_competencia, id_vaga) VALUES ('Python','VAGA101');
INSERT INTO Competencia_empresa(nome_competencia, id_vaga) VALUES ('Html/Css','VAGA201');
INSERT INTO Competencia_empresa(nome_competencia, id_vaga) VALUES ('Java','VAGA303');
INSERT INTO Competencia_empresa(nome_competencia, id_vaga) VALUES ('Javascript','VAGA303');
INSERT INTO Competencia_empresa(nome_competencia, id_vaga) VALUES ('C++','VAGA303');
INSERT INTO Competencia_empresa(nome_competencia, id_vaga) VALUES ('Java','VAGA411');

--ADD CURTIDAS E MATCHES

CREATE TABLE Candidato_curte_vaga(
	cpf_candidato VARCHAR(30) REFERENCES Candidatos(cpf) NOT NULL,
	id_vaga VARCHAR(20) REFERENCES Vagas(id) NOT NULL
);

CREATE TABLE Empresa_curte_candidato(
	cnpj_empresa VARCHAR(30) REFERENCES Empresas(cnpj) NOT NULL,
	cpf_candidato VARCHAR(30) REFERENCES Candidatos(cpf) NOT NULL
);

CREATE TABLE Matches(
	cpf_candidato VARCHAR(30) REFERENCES Candidatos(cpf) NOT NULL,
	cnpj_empresa VARCHAR(30) REFERENCES Empresas(cnpj) NOT NULL
);

-- insercao nas novas tabelas

--candidato curte vaga
INSERT INTO Candidato_curte_vaga(cpf_candidato, id_vaga) VALUES ('123.456.789-01', 'VAGA123');
INSERT INTO Candidato_curte_vaga(cpf_candidato, id_vaga) VALUES ('123.456.789-01', 'VAGA101');
INSERT INTO Candidato_curte_vaga(cpf_candidato, id_vaga) VALUES ('123.456.789-01', 'VAGA303');
INSERT INTO Candidato_curte_vaga(cpf_candidato, id_vaga) VALUES ('987.654.321-02', 'VAGA123');
INSERT INTO Candidato_curte_vaga(cpf_candidato, id_vaga) VALUES ('398.456.321-17', 'VAGA411');
INSERT INTO Candidato_curte_vaga(cpf_candidato, id_vaga) VALUES ('398.456.321-17', 'VAGA201');
INSERT INTO Candidato_curte_vaga(cpf_candidato, id_vaga) VALUES ('045.359.863-59', 'VAGA411');


--empresa curte candidato
INSERT INTO Empresa_curte_candidato(cnpj_empresa, cpf_candidato) VALUES ('12.345.678/0001-01', '123.456.789-01');
INSERT INTO Empresa_curte_candidato(cnpj_empresa, cpf_candidato) VALUES ('98.765.432/0001-02', '123.456.789-01');
INSERT INTO Empresa_curte_candidato(cnpj_empresa, cpf_candidato) VALUES ('23.456.789/0001-03', '123.456.789-01');
INSERT INTO Empresa_curte_candidato(cnpj_empresa, cpf_candidato) VALUES ('23.456.789/0001-03', '398.456.321-17');
INSERT INTO Empresa_curte_candidato(cnpj_empresa, cpf_candidato) VALUES ('34.567.890/0001-04', '045.359.863-59');
INSERT INTO Empresa_curte_candidato(cnpj_empresa, cpf_candidato) VALUES ('45.678.901/0001-05', '045.359.863-59');


--matches
INSERT INTO Matches(cpf_candidato, cnpj_empresa) VALUES ('123.456.789-01', '12.345.678/0001-01');
INSERT INTO Matches(cpf_candidato, cnpj_empresa) VALUES ('123.456.789-01', '98.765.432/0001-02');
INSERT INTO Matches(cpf_candidato, cnpj_empresa) VALUES ('398.456.321-17', '23.456.789/0001-03');
INSERT INTO Matches(cpf_candidato, cnpj_empresa) VALUES ('045.359.863-59', '45.678.901/0001-05');



