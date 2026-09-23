# Vittae — API de Agendamento de Consultas Médicas

API REST responsável por toda a regra de negócio do **Vittae**, sistema de agendamento de consultas médicas. Cuida de autenticação, cadastro de médicos e pacientes, disponibilidade de horários e o ciclo completo de uma consulta (agendar, cancelar, realizar).

Este é o back-end do projeto. A interface web (JSF) que consome esta API está em: [vittae-jsf](https://github.com/GustavoGomes-doc/vittae-jsf).

## Funcionalidades

- **Autenticação e autorização** via JWT, com controle de acesso granular por perfil (`ADMIN`, `MEDICO`, `PACIENTE`) em cada endpoint.
- **Cadastro e gestão** de médicos, pacientes e usuários (CRUD completo).
- **Disponibilidade de horários**: cada médico define os dias da semana e o intervalo em que atende.
- **Cálculo automático de horários livres**: cruza a disponibilidade cadastrada, a duração da consulta de cada médico e os horários já ocupados para gerar os slots disponíveis em uma data específica.
- **Ciclo de vida da consulta**: agendamento pelo paciente, cancelamento, marcação como realizada e histórico (`PENDENTE`, `REALIZADA`, `CANCELADA`).
- **Especialidades médicas** cadastráveis e consultáveis.
- **Painel administrativo**: listagens específicas para o perfil `ADMIN` (todas as consultas, todos os pacientes, etc.).

## Tecnologias

- **Java 17**
- **Spring Boot** (Web, Data JPA, Security, Validation)
- **Spring Security** com filtro JWT stateless (`java-jwt`, Auth0)
- **MySQL** (via Spring Data JPA / Hibernate)
- **Lombok**
- **Maven** (empacotado como `.war`)

## Arquitetura

O projeto segue a separação clássica em camadas:

```
Controller  →  Service  →  Repository  →  Model (JPA)
                 ↓
                DTO (entrada e saída da API)
```

- `security/SecurityConfig` define, endpoint a endpoint e verbo HTTP a verbo HTTP, quais perfis podem acessar o quê (ex.: só `MEDICO` cadastra disponibilidade, só `PACIENTE` agenda consulta, só `ADMIN` vê o painel geral).
- `security/JwtAuthFilter` valida o token em cada requisição antes de chegar ao controller, mantendo a API sem estado de sessão (`STATELESS`).
- DTOs específicos (`AdminConsultaDTO`, `MedicoListagemDTO`, `HorariosLivresDTO`, etc.) evitam expor as entidades JPA diretamente e adaptam a resposta a cada tela do front-end.

## Como executar

```bash
# Configure as credenciais do MySQL em application.properties
./mvnw spring-boot:run
```

A API sobe por padrão em `http://localhost:8080` e expõe os endpoints sob `/api/**` (ex.: `/api/login`, `/api/medicos`, `/api/agendamentos`).

## Projeto relacionado

- Front-end: [vittae-jsf](https://github.com/GustavoGomes-doc/vittae-jsf) — interface web em JSF que consome esta API.
