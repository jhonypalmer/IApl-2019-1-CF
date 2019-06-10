# Atividade Supervisionada (AS04) – 2019-1

## I. Objetivo Geral

- **Construir um sistema de informação web visando a integração entre sistemas para os requisitos funcionais já modelados (operações de inclusão, alteração, remoção e consulta dos objetos dessas classes) e, adicionalmente, produzir e consumir serviços web.**.

## II. Descrição

### I. Definições Gerais

- [ ] 1.1. Reutilizar todas as definições, modelagem e implementações das atividades supervisionadas nº 1, 2, e 3.
- [ ] 1.2. Construir sistema de informação Web implementando os requisitos funcionais já definidos.
- [ ] 1.3. O sistema deverá apresentar um menu com as opções de acesso às funcionalidades.
- [ ] 1.4. O sistema deverá ser hospedado no servidor de apoio da disciplina cujas tecnologias recomendadas são JAVA EE e PostgreSql. Caso utilize tecnologias diferentes dessas recomendadas, a equipe de alunos fica responsável por criar toda a infraestrutura de hospedagem do sistema Web nesse servidor.

### II. Requisitos Funcionais:

- [ ] 2.1. Acesso ao sistema por meio de login informando um endereço de e-mail e senha.
- [ ] 2.2. Cadastros:
    * Todas as classes modeladas deverão ter funcionalidades para consultas em forma de lista de item e, no nível de item, possibilitar a inclusão, alteração, remoção e consulta detalhada.
- [ ] 2.3. Integração entre Sistemas de Informação (Aplicações):
  - [ ] 2.3.1. Serviços Web
    * Obter lista completa com todos os objetos (listão) persistidos de todas as classes.
    * Obter objeto específico.
    * Incluir novo objeto.
    * Alterar objeto.
  - [ ] 2.3.2. Produtor
    * Todos os serviços deverão estar disponíveis para qualquer outro sistema utilizá-los.
    * Caso haja erros na execução dos serviços, um objeto genérico de erro deverá ser enviado como resposta.
    * Elaborar mecanismo para informar às equipes a forma de se consumir os serviços web.
  - [ ] 2.3.3. Consumidor 
    * Todos os serviços web disponíveis pelo produtor deverão ser consumidos (utilizados) sendo necessário construir todas as interfaces homem-computador relativas aos serviços web disponibilizando ao usuário.
    * Caso haja erros na execução dos serviços, um objeto genérico de erro deverá ser recebido como resposta e apresentado ao usuário.
    * NOTA: O sistema a ser integrado por cada equipe está definido na seção “Sistemas a serem integrados”.

### III. Requisitos Não-Funcionais:

- [ ] 3.1. O sistema de informação deve estar preparado para apresentar sua interface homemcomputador
em vários idiomas, isto é, deve ser implementada a internacionalização.
- [ ] 3.2. Todas as situações que ocorrerem interação com usuário deverão ter mensagens de erro,
advertência ou informativa.
- [ ] 3.3. Todas as situações possíveis deverão ter tratamento de exceção.
- [ ] 3.4. Todo o código fonte deverá ser escrito no idioma inglês, inclusive os comentários.
- [ ] 3.5. O projeto do sistema deverá estar estruturado em pacotes (pastas) contendo as entidades,
serviços, persistência, interface homem-computador, utilitários, dentre outros.
- [ ] 3.6. Persistência utilizando JDBC ou framework específico.
- [ ] 3.7. Todas as transposições de chaves primárias deverão implementar integridade
referencial.
- [ ] 3.8. Os dados dos serviços web deverão ser trafegados em formato JSON, sem preocupação
com aspectos de segurança (criptografia).
- [ ] 3.9. O protocolo do serviço web a ser utilizado é o RESTFul (REpresentational State Transfer).

## III. Cronograma

|Status|Tarefa| Data da Entrega ou Avaliação|
|:---:|:---|:---:|
| |1. Entrega com 1. Observação dos requisitos funcionais nr. 2.1, 2.2 implementados nas tecnologias escolhidas (linguagem de programação e SGBD).2. Observação dos requisitos não-funcionais nr. 3.1, 3.2, 3.3, 3.4, 3.5, 3.6 e 3.7 implementados nas tecnologias escolhidas (linguagem e SGBD).apresentação ao professor| 10/06/2019 |
| |3. Avaliação das tarefas 1 e 2 para todas as equipes.| 17/06/2019 |
| |4. Observação dos requisitos funcionais nr. 2.3 (2.3.1, 2.3.2 e 2.3.3)implementados nas tecnologias escolhidas (linguagem de programação e SGBD). 5. Observação dos requisitos não-funcionais nr. 3.8 e 3.9 implementados nas tecnologias escolhidas (linguagem e SGBD).| 24/06/2019 |
| |6. Avaliação das tarefas 4 e 5 para todas as equipes.| 01/07/2019 |

## IV. Documentos
* [1.Documentos](1.Documentos)

> Link da [Atividade Supervisionada 04](https://github.com/jhonypalmer/IApl-2019-1-CF/blob/master/AS04/2019-1%20-%20IApl%20-%20Atividade%20Supervisionada%20nr.%2004.pdf)
