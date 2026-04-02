# Olimpíada de Questões — Refatoração SOLID

Atividade prática da disciplina de **Programação Orientada a Objetos (POO)**, com o objetivo de refatorar um sistema legado aplicando os cinco princípios do SOLID, sem alterar o comportamento funcional original.

---

## Como executar

**Requisitos:** Java 17 ou superior e Maven instalados.

```bash
git clone <url-do-repositorio>
cd <nome-do-repositorio>
mvn compile
mvn exec:java -Dexec.mainClass="br.com.ucsal.olimpiadas.App"
```

---

## Funcionalidades do sistema

- Cadastrar participantes
- Cadastrar provas
- Cadastrar questões (múltipla escolha A–E) em uma prova
- Aplicar prova a um participante (modo simples ou cronometrado)
- Listar tentativas com nota

---

## Princípios SOLID aplicados

### S — Single Responsibility Principle (SRP)
Cada classe passou a ter uma única responsabilidade. A lógica de negócio foi extraída do `App` para classes de serviço dedicadas:

| Classe | Responsabilidade |
|---|---|
| `ParticipanteService` | Gerenciar cadastro de participantes |
| `ProvaService` | Gerenciar cadastro de provas |
| `QuestaoService` | Gerenciar cadastro de questões |
| `TentativaService` | Gerenciar tentativas e cálculo de nota |

O `App` passou a ter apenas a responsabilidade de orquestrar o menu e delegar as ações aos services.

---

### O — Open/Closed Principle (OCP)
Foi criada a interface `IProvaStrategy` para representar os tipos de prova. Isso permite adicionar novos tipos de prova sem modificar o código existente.

```
IProvaStrategy
├── ProvaSimples       → prova sem restrição de tempo
└── ProvaCronometrada  → prova com tempo limite por questão
```

Para adicionar um novo tipo de prova no futuro, basta criar uma nova classe que implemente `IProvaStrategy`, sem tocar em nenhuma classe já existente.

---

### L — Liskov Substitution Principle (LSP)
`ProvaSimples` e `ProvaCronometrada` implementam `IProvaStrategy` e podem ser usadas de forma intercambiável no `App`, sem quebrar o comportamento esperado:

```java
IProvaStrategy strategy = new ProvaSimples(...);
// ou
IProvaStrategy strategy = new ProvaCronometrada(...);

strategy.executar(tentativa, questoesDaProva); // funciona com qualquer uma
```

---

### I — Interface Segregation Principle (ISP)
A interface `ITentativaService` foi dividida em interfaces menores e específicas:

| Interface | Responsabilidade |
|---|---|
| `ICalculadoraNota` | Declara `calcularNota()` |
| `IRegistradorResposta` | Declara `registrarResposta()` |
| `ITentativaService` | Estende as duas e adiciona `iniciar()` e `listarTodas()` |

`ProvaSimples` e `ProvaCronometrada` recebem apenas as interfaces que de fato utilizam, sem depender de métodos desnecessários.

---

### D — Dependency Inversion Principle (DIP)
O `App` passou a depender de abstrações (interfaces) e não de implementações concretas:

```java
// antes — dependia da implementação concreta
static TentativaService tentativaService = new TentativaService(...);

// depois — depende da abstração
static ITentativaService tentativaService = new TentativaService(...);
```

Isso garante que qualquer implementação de `ITentativaService` pode ser utilizada sem alterar o `App`.

---

## Estrutura de arquivos

```
src/main/java/br/com/ucsal/olimpiadas/
├── App.java
├── IProvaStrategy.java
├── ITentativaService.java
├── ICalculadoraNota.java
├── IRegistradorResposta.java
├── Participante.java
├── ParticipanteService.java
├── Prova.java
├── ProvaService.java
├── ProvaSimples.java
├── ProvaCronometrada.java
├── Questao.java
├── QuestaoService.java
├── Resposta.java
├── Tentativa.java
└── TentativaService.java
```

---

## Histórico de commits

| Hash | Mensagem | O que foi feito |
|---|---|---|
| `359475a` | Initial commit | Código original sem refatoração |
| `ef588aa` | implementa services seguindo o SOLID | Aplicação do SRP — extração da lógica do `App` para `ParticipanteService`, `ProvaService`, `QuestaoService` e `TentativaService` |
| `17b6ff3` | implementa Open/Closed | Aplicação do OCP e DIP — criação de `IProvaStrategy`, `ProvaSimples`, `ProvaCronometrada` e `ITentativaService` |
| `38816d5` | implementa princípio Interface Segregation | Aplicação do ISP e LSP — criação de `ICalculadoraNota` e `IRegistradorResposta`, atualização de `ProvaSimples` e `ProvaCronometrada` para depender apenas das interfaces necessárias |

