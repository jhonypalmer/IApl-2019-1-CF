# AS03 - Documentação de Integração

## Instruções para realizar a integração

### Requisição HTTP

Seguimos a estrutura padrão do estilo [RESTful](https://en.wikipedia.org/wiki/Representational_state_transfer)

- GET: lista ou consulta dados
- POST: criação de dados
- PUT: atualização de dados
- DELETE: remoção de dados

### Padrão de endpoints

    Para listagem, use GET: /endpoint/
    Para inserção, use POST: /endpoint/
    Para visualização, use GET: /endpoint/{id}
    Para atualização, use PUT: /endpoint/{id}
    Para exclusão, use DELETE: /endpoint/

### Retorno

* Atualmente temos 3 tipos diferentes de retornos, são eles, JSON, XML e Layout com Posição Fixa.

Por default, sempre irá retornar JSON, caso queira alterar o tipo de retorno, basta inserir o parâmetro 'mediaType' nas consultas, conforme exemplo abaixo:

JSON: GET /holiday?mediaType=json

+ Response (application/json)

    ```js
        {
			"holidays": [
				{
					"description": "Aniversário de Goiânia",
					"date": "24-10-2019",
					"city": "Goiânia",
					"state": "Goiás",
					"country": "Brazil"
				},
				{
					"description": "Paixão de Cristo",
					"date": "19-04-2019",
					"city": null,
					"state": null,
					"country": "Brazil"
				},
				{
					"description": "Tiradentes",
					"date": "21-04-2019",
					"city": null,
					"state": null,
					"country": "Brazil"
				}
			]
		}
    ```
	
XML: GET /holiday?mediaType=xml

+ Response (application/xml)

    ```xml
		<holidays>
			<holiday>
				<description>Aniversário de Goiânia</description>
				<date>24-10-2019</date>
				<city>Goiânia</city>
				<state>Goiás</state>
				<country>Brazil</country>
			</holiday>
			<holiday>
				<description>Paixão de Cristo</description>
				<date>19-04-2019</date>
				<city/>
				<state/>
				<country>Brazil</country>
			</holiday>
			<holiday>
				<description>Tiradentes</description>
				<date>21-04-2019</date>
				<city/>
				<state/>
				<country>Brazil</country>
			</holiday>
		</holidays>
    ```

Posição Fixa: GET /holiday?mediaType=fixedPosition

+ Response (application/fixedPosition)

    ```text
		Holidays29-04-2019
		Aniversário de Goiânia                                      24-10-2019Goiânia                                                     Goiás                                                       Brazil                                                      
		Paixão de Cristo                                            19-04-2019                                                                                                                        Brazil                                                      
		Tiradentes                                                  21-04-2019                                                                                                                        Brazil                                                      
		3         
    ```
  
## Listando Feriados

GET /holiday

### Consultando Feriado Por ID

GET /holiday/{id}

## Adicionando Feriados

POST /holiday

* É possível fazer a inserção utilizando os layouts JSON, XML e Posição Fixa. Para isso, basta identificar qual o layout desejado através do Header Content-Type. Ex.:

JSON: Content-Type: application/json

XML: Content-Type: application/xml

Posição Fixa: Content-Type: application/fixedPosition