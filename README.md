# Loan Decision Engine

A Spring Boot application that implements a loan decision engine to determine the maximum approved loan amount for a given person profile, regardless of the requested amount.

## Features

- Calculates maximum approved loan amount based on credit score and period
- REST API endpoint for loan decisions
- Simple web frontend for testing
- Unit tests covering all credit segments

## Prerequisites

- Java 21
- Maven (or use the included Maven wrapper)

## Running the Application

1. Clone or navigate to the project directory
2. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
3. Open your browser and go to `http://localhost:8080` to use the web interface

## API Usage

### POST /api/loan/decision

Calculate the maximum approved loan amount for a person.

**Request Body:**
```json
{
  "personId": "person1",
  "loanAmount": 5000,
  "loanPeriod": 24
}
```

**Response:**
```json
{
  "approved": true,
  "approvedAmount": 10000,
  "approvedPeriod": 60
}
```

**Parameters:**
- `personId`: Identifier for the person profile
- `loanAmount`: Requested loan amount (ignored in calculation)
- `loanPeriod`: Requested loan period (ignored in calculation)

The engine always returns the maximum possible approved amount and optimal period.

## Testing

Run the unit tests:
```bash
./mvnw test
```

## Project Structure

- `src/main/java/com/loancalc/loancalc/`: Main application code
  - `controller/`: REST controllers
  - `model/`: Data models
  - `service/`: Business logic services
- `src/main/resources/static/`: Static web files
- `src/test/`: Unit tests</content>
