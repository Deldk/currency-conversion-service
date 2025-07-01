# Exchange Rate Service

Hi hi, welcome to one of my early projects. I'm mostly using these to practice the languages involved so pardon the inefficiencies and such that may be present. Hopefully these will be useful to people as well but that's just well wishing I guess🤷‍♂️

## Brief Intro
A simple Java Spring Boot backend service that converts provided amounts from one currency to another using live currency conversion rates from the external api of [exchangerate.host](https://exchangerate.host).

## Features

- Converts one currency to another using public api through the '/convert' endpoint
- Stores past conversions in a postgresql db
- Fetches filtered and unfiltered past conversions using the '/history' endpoint
- Calls exchangerate.host API for up-to-date conversions (If you want to change the external api being used you'll have to make changes in the restclient config and properties files to start)

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

4. Test endpoint (See below for more detailed endpoints documentation):
    ```
    GET http://localhost:8080/api/convert?from=GHS&to=USD&amount=100
    ```

5. Note: To further understand how the external api works you can read their documentation here [exchange.host documentation](https://exchangerate.host/documentation)

## API Endpoints

### 1. Convert Currency
**Request**
```http request
GET /api/convert?from=USD&to=GHS&amount=100
```
**Response**
```json
{
   "from": "USD",
   "to": "GHS",
   "amount": 100.0,
   "rate": 1035.0959,
   "conversion": 10.350959,
   "timestamp": "2025-07-01T12:46:36.3666974"
}
```

### 2. Fetch Conversion History
**Request**
```http request
GET /api/history?from=USD&to=GHS&date=2025-06-30T00:00:00&page=0&size=5
```
**Response**
```json
{
    "content": [
        {
            "from": "USD",
            "to": "GHS",
            "amount": 100.0,
            "rate": 1035.0959,
            "conversion": 10.350959,
            "timestamp": "2025-07-01T12:46:36.361694"
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 5,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalElements": 1,
    "totalPages": 1,
    "size": 5,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 1,
    "empty": false
}
```
Note: You can exclude the date to filter just by the two currencies or exclude the date and currencies to get all past conversions

---