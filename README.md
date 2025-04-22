# 🎬 TheMovieDb Server

API backend desenvolvida em Kotlin com Ktor, responsável por fornecer dados de filmes e séries consumidos pelo aplicativo Android [TheMovieDbApp](https://github.com/juanunix/TheMoviedbApp).

## 🔧 Tecnologias Utilizadas

- **Kotlin** – Linguagem principal do projeto ✅
- **Ktor** – Framework assíncrono para desenvolvimento de servidores HTTP ✅
- **Gradle com Kotlin DSL** – Sistema de build moderno e tipado ✅
- **TMDb API v3** – Integração com a API oficial do The Movie Database ✅

## 📁 Estrutura de Diretórios

```
themoviedb-server/
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   └── ...          # Código-fonte principal do servidor
│   │   └── resources/
│   │       └── ...          # Arquivos de configuração e recursos
├── build.gradle.kts         # Script de build do Gradle em Kotlin DSL
├── settings.gradle.kts      # Configurações do projeto Gradle
├── gradle.properties        # Propriedades do projeto
├── gradlew                  # Script para executar o Gradle (Unix)
├── gradlew.bat              # Script para executar o Gradle (Windows)
└── README.md                # Este arquivo
```

## 🚀 Como Iniciar

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/juanunix/themoviedb-server.git
   cd themoviedb-server
   ```

2. **Execute o servidor:**

   Utilize o Gradle para compilar e iniciar o servidor:

   ```bash
   ./gradlew run
   ```

   O servidor estará disponível em `http://localhost:8080`.

## 📌 Funcionalidades

- **Listagem de filmes populares:** Endpoint para obter os filmes mais populares do momento.
- **Detalhes de filmes:** Informações detalhadas sobre um filme específico.
- **Busca por título:** Permite buscar filmes pelo título.
- **Integração com o TMDb:** Todos os dados são obtidos diretamente da API oficial do The Movie Database.

## 🧪 Testes

> Atualmente, o projeto **não possui testes implementados**. Há espaço para adicionar:

- **Testes unitários** com JUnit e MockK
- **Testes de integração** para validar os endpoints da API
- **Testes de ponta a ponta (E2E)** para simular fluxos completos de uso

## 🚧 Melhorias Futuras

- 🔐 **Implementação de autenticação:** Adicionar mecanismos de autenticação e autorização para proteger os endpoints.
- 📊 **Paginação e filtros avançados:** Melhorar os endpoints para suportar paginação e filtros por gênero, data, etc.
- 📝 **Documentação da API:** Utilizar ferramentas como Swagger ou Ktor OpenAPI para documentar os endpoints disponíveis.
- 📦 **Dockerização:** Criar um `Dockerfile` para facilitar o deploy e a execução em ambientes containerizados.
- 📈 **Monitoramento e logs:** Integrar soluções de monitoramento e logging para acompanhar o desempenho e erros do servidor.

## 📄 Licença

Este projeto está licenciado sob os termos da Licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais informações.
