# 🏍️ Mottu API - Spring Boot

Este projeto é uma API desenvolvida em Java com o framework Spring Boot, que tem como objetivo atual gerenciar usuários e motos da plataforma **Mottu**. A aplicação permite:

- ✅ Cadastro completo (CRUD) de usuários;
- 🏍️ Cadastro de motos, vinculado a um usuário previamente autenticado;
- ✏️ Edição e exclusão de motos;
- 🔐 Controle de acesso e segurança básica via endpoints.

---

## 👨‍💻 Desenvolvedores

- Eduardo Henrique Strapazzon Nagado  
- Felipe Silva Maciel
- Gustavo Ramires Lazzuri

---

## 🚀 Como Executar

A API roda localmente, por padrão, na porta `8080`. Use uma ferramenta como **Postman** ou **Insomnia** para testar os endpoints.

### 📦 Endpoints de Usuário

#### 🔸 Cadastrar Usuário
**POST** `http://localhost:8080/auth/cadastrar`  
**JSON:**
{
  "username": "Teste1",
  "password": "EWdde521",
  "email": "Edu@nagado.com"
}

#### 🔸  Listar Usuários
**GET** `http://localhost:8080/auth/listar`  

#### 🔸 Atualizar Usuário
**PUT** `http://localhost:8080/auth/atualizar`  
**JSON:**
{
  "id": "E4C868A8-E908-4EDF-9E5F-33544F8E26AF",
  "username": "EduDelete",
  "email": "Edet@Super.com"
}

#### 🔸 Atualizar Usuário
**DELETE** `http://localhost:8080/auth/E4C868A8-E908-4EDF-9E5F-33544F8E26AF` 


### 📦 Endpoints de Moto

#### 🔸 Cadastrar Moto
**POST** `http://localhost:8080/moto/cadastrar?usuarioId=c8d467ed-b7fc-41c7-bf32-48def2d0e876`  
**JSON:**
{
  "modelo": "MOTTU_SPORT",
  "cor": "PRETO",
  "placa": "SSE2E92",
  "status": "DISPONIVEL"
}

#### 🔸  Listar Moto
**GET** `http://localhost:8080/moto/listar`  

#### 🔸 Atualizar Moto
**PUT** `http://localhost:8080/moto/atualizar`  
**JSON:**
{
  "id": "E5E66181-76F7-4BD4-A7D3-980F2A93FB36",
  "cor": "amarelo"
}

#### 🔸 Atualizar Moto
**DELETE** `http://localhost:8080/moto/40E697F0-A43F-4226-9B67-16A8A0FE0F7E` 

## 🚀🐳 Como rodar o container Docker

### faça o pull com:
#### sudo docker pull edunagado/gef-api:v1

### Rode o container:
#### sudo docker run -d --name gef-api -p 8080:8080 --user 1000:1000 --restart always edunagado/gef-api:v1

### Verifique se o container está rodando:
#### sudo docker ps

### Acesse a API pelo navegador ou ferramentas como Postman no endereço:
#### http://localhost:8080 ou se tiver dentro da vm curl http://SEU_IP_PUBLICO:8080

### Para parar e remover o container quando não precisar mais:
#### sudo docker stop container-api
#### sudo docker rm container-api
