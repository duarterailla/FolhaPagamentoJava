# FolhaPagamentoJava

# Sistema de Folha de Pagamento em Java

Este projeto Java simula um **sistema de folha de pagamento**, contemplando funcionalidades como cadastro de funcionários, dependentes, cálculo de impostos (INSS e IRRF), leitura e gravação de arquivos CSV e conexão com banco de dados.

## 🧩 Estrutura do Projeto

O sistema está organizado em diversos arquivos Java que se distribuem em diferentes responsabilidades:

### 📦 Entidades e Modelos

- `Pessoa.java` – Classe base com atributos comuns a `Funcionario` e `Dependente`.
- `Funcionario.java` – Representa um funcionário, incluindo salário e lista de dependentes.
- `Dependente.java` – Representa um dependente com vínculo de parentesco.
- `Parentesco.java` – Enum que define os tipos de parentesco aceitos.

### 📄 Regras de Negócio

- `CalculoGeral.java`, `CalculoService.java` – Realizam os cálculos de folha de pagamento e descontos.
- `INSS.java` – Calcula o desconto de INSS conforme faixas.
- `IR.java` – Calcula o desconto de IRRF conforme dependentes e faixas salariais.
- `CPF.java`, `VerificacaoCPF.java` – Validam CPF conforme regras formais.

### ⚠️ Tratamento de Exceções

- `FuncionarioException.java`
- `DependenteException.java`
- `FolhaDePagamentoException.java`

### 🗂️ Persistência (DAO)

- `FuncionarioDao.java`
- `DependenteDao.java`
- `FolhaDePagamentoDao.java`

Essas classes acessam e manipulam dados, provavelmente usando JDBC para comunicação com banco.

### 🔌 Conexão com Banco

- `ConnectionFactory.java` – Centraliza a criação de conexões JDBC.
- `TesteConnection.java` – Classe de teste para validar conexão.

### 🧪 Testes e Execução

- `Main.java` – Executa o sistema principal.
- `Confirmação.java` – Interface simples para confirmar ações no fluxo.
- `TestGravacaoCSV.java`, `TestLeituraCSV.java` – Testes de escrita/leitura de arquivos CSV.
- `GravacaoArquivo.java`, `LeituraCSV.java` – Implementações para persistência e recuperação via arquivos.

## 🛠️ Requisitos

- **Java 17+**
- Editor recomendado: IntelliJ IDEA (estrutura padrão `.idea`, `modules.xml`)
- JDBC configurado para acesso a banco de dados
- Biblioteca padrão Java (nenhuma biblioteca externa identificada)

## 🚀 Como Executar

1. **Abra no IntelliJ IDEA** ou outro IDE compatível com Java.
2. **Compile o projeto** com Java 17+.
3. **Execute `Main.java`** para testar o fluxo principal.
4. Para testar funcionalidades específicas:
   - Execute `TesteConnection` para testar o banco.
   - Execute `TestLeituraCSV` ou `TestGravacaoCSV` para manipulação de arquivos CSV.

## 🧮 Funcionalidades

- Cadastro de funcionários com seus dependentes
- Cálculo automático de:
  - INSS
  - IRRF
  - Salário líquido
- Leitura e gravação de dados em arquivos CSV
- Armazenamento de informações no banco via DAO
- Validação formal de CPF
