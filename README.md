# 🚀 Produto API - Testes de Integração

Este projeto contém testes de integração para a API de gerenciamento de produtos, utilizando **Spring Boot** e **JUnit 5**. Os testes são realizados usando o `MockMvc` para simular requisições HTTP e garantir que os endpoints da API funcionem conforme o esperado.

## 📑 Sumário

- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Requisitos](#-requisitos)
- [Como Executar os Testes](#-como-executar-os-testes)
  - [Passo 1: Clonar o Repositório](#-passo-1-clonar-o-repositório)
  - [Passo 2: Instalar as Dependências](#-passo-2-instalar-as-dependências)
  - [Passo 3: Executar os Testes](#-passo-3-executar-os-testes)
- [Endpoints Testados](#-endpoints-testados)
  - [`POST /api/produtos`](#post-apiprodutos-)
  - [`GET /api/produtos`](#get-apiprodutos-)
  - [`GET /api/produtosid`](#get-apiprodutosid-)
  - [Erro Esperado](#-erro-esperado)
- [Como Funciona](#-como-funciona)
- [Dependências](#-dependências)
- [Licença](#-licença)

## 📂 Estrutura do Projeto

- **ProdutoControllerIntegrationTest**: Contém os testes de integração da API de produtos.
  - **POST**: Criação de um novo produto.
  - **GET**: Listagem de produtos e busca por ID.
  - **Erro 404**: Teste para retornar erro quando o produto não for encontrado.

## ⚙️ Requisitos

Certifique-se de ter os seguintes requisitos instalados:

- **Java 11+** ☕
- **Maven** ou **Gradle** 📦
- **Spring Boot** (já configurado no projeto) 🛠️
- **JUnit 5** 🧪
- **MockMvc** 🧑‍💻

## 🏃‍♂️ Como Executar os Testes

### 1️⃣ Passo 1: Clonar o Repositório

Clone o repositório para a sua máquina local:

```bash
git clone https://github.com/seu-usuario/produto-api.git
cd produto-api
```
### 2️⃣ Passo 2: Instalar as Dependências

Se estiver usando **Maven**, execute:

```bash
mvn clean install
```

Se estiver usando **Gradle**, execute:

```bash
./gradlew build
```

### 3️⃣ Passo 3: Executar os Testes

Para executar os testes de integração, basta rodar:

* Com **Maven**:

```bash
mvn test
```

* Com **Gradle**:

```bash
./gradlew test
```

O Spring Boot iniciará um servidor de testes na porta aleatória, e os testes serão executados. Você verá os resultados diretamente no console.

## 🌐 Endpoints Testados

### `POST /api/produtos` ➕
- **Descrição**: Cria um novo produto.
- **Exemplo de corpo da requisição**:

```json
{
  "nome": "Produto Teste",
  "preco": 99.99
}
```

---

### `GET /api/produtos` 📜
- **Descrição**: Retorna a lista de produtos.

---

### `GET /api/produtos{id}` 🔍
- **Descrição**: Retorna o produto com o ID especificado.

---

### ⚠️ Erro Esperado:
- **404**: Caso o produto não seja encontrado.

## 💡 Como Funciona

O teste de integração cria um produto via requisição POST, em seguida realiza uma requisição GET para garantir que o produto foi adicionado com sucesso. Ele também verifica a resposta de erro quando tenta buscar um produto que não existe.

## 📦 Dependências

Este projeto utiliza as seguintes dependências:

- `spring-boot-starter-web`: Para criar a API.
- `spring-boot-starter-test`: Para testes, incluindo `MockMvc` e `JUnit`.
- `jackson-databind`: Para conversão entre objetos Java e JSON.
- `h2`: Banco de dados em memória para testes.

## 📝 Licença

Este projeto está sob a licença **MIT**. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
