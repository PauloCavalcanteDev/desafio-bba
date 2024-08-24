# desafio-bba
Desafio engenharia de software

# Para Rodar a aplicação:

<h4>Pré requisitos:</h4>

 - Docker
 - Java 17
 - maven 3.8 ou superior


Claro! Aqui está a documentação para os quatro endpoints mencionados:

# Endpoints:

### Consulta Cliente:

**Descrição:** Consulta os dados do cliente pelo CPF.

**Endpoint:**
```
GET: http://localhost:8080/api/v1/clientes/{cpf}
```

**Parâmetros:**
- `cpf` (String): CPF do cliente (11 dígitos).

**Exemplos de CPF:**
- 12345678901
- 23456789012
- 34567890123
- 45678901234
- 56789012345

**Exemplo de Requisição:**
```
GET: http://localhost:8080/api/v1/clientes/12345678901
```

**Exemplo de Resposta de Sucesso (200 OK):**
```json
{
    "cpf": "12345678901",
    "nome": "João Silva",
    "endereco": {
        "rua": "Rua Exemplo",
        "numero": "123",
        "cidade": "São Paulo",
        "estado": "SP"
    }
}
```

**Exemplo de Resposta de Erro (404 Not Found):**
```json
{
    "mensagem": "Cliente Não Encontrado."
}
```

### Consulta Endereço:

**Descrição:** Consulta os dados do endereço pelo CEP.

**Endpoint:**
```
GET: http://localhost:8080/api/v1/enderecos/{cep}
```

**Parâmetros:**
- `cep` (String): CEP do endereço (8 dígitos).

**Exemplos de CEP:**
- 01001000
- 02002000
- 03003000
- 04004000
- 05005000

**Exemplo de Requisição:**
```
GET: http://localhost:8080/api/v1/enderecos/01001000
```

**Exemplo de Resposta de Sucesso (200 OK):**
```json
{
    "cep": "01001000",
    "logradouro": "Praça da Sé",
    "bairro": "Sé",
    "cidade": "São Paulo",
    "estado": "SP"
}
```

**Exemplo de Resposta de Erro (404 Not Found):**
```json
{
    "mensagem": "Endereço não encontrado."
}
```

### Listar Estados:

**Descrição:** Lista todos os estados brasileiros.

**Endpoint:**
```
GET: http://localhost:8080/api/v1/estados
```

**Exemplo de Requisição:**
```
GET: http://localhost:8080/api/v1/estados
```

**Exemplo de Resposta de Sucesso (200 OK):**
```json
[
    {
        "sigla": "SP",
        "nome": "São Paulo"
    },
    {
        "sigla": "RJ",
        "nome": "Rio de Janeiro"
    },
    {
        "sigla": "MG",
        "nome": "Minas Gerais"
    }
    // Outros estados...
]
```

**Exemplo de Resposta de Erro (500 Internal Server Error):**
```json
{
    "mensagem": "Erro ao consultar IBGE."
}
```

### Listar Municípios:

**Descrição:** Lista todos os municípios de um estado brasileiro.

**Endpoint:**
```
GET: http://localhost:8080/api/v1/municipios/{siglaUf}
```

**Parâmetros:**
- `siglaUf` (String): Sigla do estado (2 letras).

**Exemplos de Sigla de Estado:**
- SP
- RJ
- MG
- RS
- BA

**Exemplo de Requisição:**
```
GET: http://localhost:8080/api/v1/municipios/SP
```

**Exemplo de Resposta de Sucesso (200 OK):**
```json
[
    {
        "nome": "São Paulo"
    },
    {
        "nome": "Campinas"
    },
    {
        "nome": "Santos"
    }
    // Outros municípios...
]
```

**Exemplo de Resposta de Erro (500 Internal Server Error):**
```json
{
    "mensagem": "Erro ao consultar IBGE."
}
```
