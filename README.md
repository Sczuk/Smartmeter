# SmartMeter API

## 📌 Sobre o projeto

A **SmartMeter API** será responsável por receber, processar e armazenar os dados enviados pelo firmware do ESP32, utilizando comunicação MQTT com o Mosquitto como broker.

O backend também será responsável por disponibilizar endpoints HTTP para consulta dos dados, controle dos dispositivos e integração com futuras interfaces do sistema.

## 🎯 Objetivo

* Receber dados enviados pelo ESP32 via MQTT
* Processar leituras de corrente e tensão
* Armazenar medições no banco de dados
* Gerenciar tomadas e interruptores cadastrados
* Enviar comandos para os dispositivos via MQTT
* Disponibilizar endpoints para consumo dos dados

## 🧰 Tecnologias utilizadas

* **[Java]()**
* **[Spring Boot](https://spring.io/projects/spring-boot)**
* **[Spring Security](https://spring-io.translate.goog/projects/spring-security?_x_tr_sl=en&_x_tr_tl=pt&_x_tr_hl=pt&_x_tr_pto=tc&_x_tr_hist=true)**
* **[JWT](https://auth0.com/resources/ebooks/jwt-handbook-pt?utm_source=google&utm_campaign=amer_latam-pt_bra_all_ciam-all_dg-ao_auth0_search_google_text_kw_pt-generic-authentication_utm2&utm_medium=cpc&utm_id=aNK4z000000UCviGAG&gad_source=1&gad_campaignid=12973797247&gclid=CjwKCAjwuuPRBhAnEiwA2Ji8enAr2LDNCCJ6UJfjNwrqQKlXpwDXcwGQXBp-ULkIxYX15P47as7l5RoC7J8QAvD_BwE)**
* **[Mosquitto](https://mosquitto.org/)**
* **[MQTT](https://mqtt.org/)**
* **[Docker](https://www.docker.com/)**
* **[Postgres](https://www.postgresql.org/)**
* **[MongoDB](https://www.mongodb.com/)**
* **[Swagger / OpenAPI ](https://swagger.io/)**

## 🏗️ Arquitetura

O projeto será desenvolvido seguindo a arquitetura Hexagonal, separando responsabilidades entre domínio, aplicação, infraestrutura e entrada de dados.

```txt
src/
└── main/
    └── java/
        └── com/smartmeter/api/
            ├── domain/
            ├── application/
            ├── infrastructure/
            └── config/
```

## 📡 Comunicação com o firmware

O backend se comunica com o ESP32 através do protocolo MQTT.

O firmware publica dados em tópicos específicos, e o backend assina esses tópicos para receber as medições.

Exemplo de tópico:

```txt
smartmeter/{smartmeterId}/measurements
```

Exemplo de payload:

```json
{
  "smartmeterId": "8323-4559-3633",
  "devices": [
    {
      "deviceId": "2043-6465-3633",
      "releState": "ONLINE",
      "current_a": 2.5,
      "voltage_v": 127,
      "power_w": 317.5
    },
    {
      "deviceId": "3622-4476-3633",
      "releState": "OFFLINE",
      "current_a": "0",
      "voltage_v": "0",
      "power_w": "0"
    }
  ]
}
```

O backend também poderá publicar comandos para o ESP32.

Exemplo:

```txt
smartmeter/{smartmeterId}/device/command
```

```json
{
  "deviceId": "6540-9186-7349", "command": "TURN_OFF"
}
```

## 🗄️ Banco de dados

Como sera feita a utilizaçao dos bancos de dados. Os bancos de dados serao separados em dois, onde ficarao separados por:

Postegres:

* Usuarios
* Dispositivos


MongoDB:

* Tomada
* Interruptor
* Leituras de corrente
* Leituras de tensão
* Histórico de comandos

## 🔐 Variáveis de ambiente

As variáveis necessárias estarão disponíveis no arquivo `.env.example`.

```env
POSTGRES_DATABASE=
POSTGRES_USERNAME=
POSTGRES_PASSWORD=
POSTGRES_PORT=

MONGO_URI=

JWT_SECRET=

MQTT_HOST=
MQTT_PORT=
MQTT_USERNAME=
MQTT_PASSWORD=
```

## 📚 Documentação da API

A documentação da API será disponibilizada via Swagger/OpenAPI.

```txt
http://localhost:8080/swagger-ui.html
```

## 👨‍💻 Autor

Henrique Sczuk