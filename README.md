# Exchange Rate Service

Hi hi, welcome to one of my early projects. I'm mostly using these to practice the languages involved so pardon the inefficiencies and such that may be present. Hopefully these will be useful to people as well but that's just well wishing I guess🤷‍♂️

## Brief Intro
A simple Java Spring Boot backend service that converts provided amounts from one currency to another using live currency conversion rates from the external api of [exchangerate.host](https://exchangerate.host).

## Features

- Java 17 + Spring Boot
- PostgreSQL database
- REST endpoint example: `/api/convert?from=GHS&to=USD&amount=100`
- Calls exchangerate.host API and returns converted amount (If you want to change the external api being used you'll have to make changes in the restclient config and properties files to start)

## Getting Started

1. Clone this repo
2. Create a `.env` file:
    ```
    EXCHANGE_API_KEY=your_key
    SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/exchangeratedb
    SPRING_DATASOURCE_USERNAME=postgres (or whatever other username you create the db with)
    SPRING_DATASOURCE_PASSWORD=yourpassword
    ```

3. Run the application:
    ```bash
    ./mvnw spring-boot:run
    ```

4. Test endpoint:
    ```
    GET http://localhost:8080/api/convert?from=GHS&to=USD&amount=100
    ```

5. Note: To further understand how the external api works you can read their documentation here [exchange.host documentation](https://exchangerate.host/documentation)
---