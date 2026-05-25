# Expense Tracker

A simple, full-stack expense tracking app I built to help keep an eye on daily spending. It lets you log transactions, set a monthly budget, and see where your money is actually going through some clean charts.

## What it does
- **Accounts:** Basic user signup and login.
- **Dashboard:** A feed of all your recent transactions.
- **Budget Tracking:** You set a monthly limit, and the app calculates your current "run rate" to warn you if you're on track to spend too much by the end of the month.
- **Analytics:** Uses Chart.js to break down expenses by category so you can see if you're spending too much on on any one category.

## Tech Stack
- **Frontend:** Plain HTML, CSS, and vanilla JavaScript.
- **Backend:** Java 17 and Spring Boot.
- **Database:** PostgreSQL (using Spring Data JPA/Hibernate).

## How to run it locally

If you want to pull this down and run it on your own machine, you'll need Java 17, Maven, and PostgreSQL installed.

### 1. Database
Create a local Postgres database named `Expenses`. The app defaults to looking for a user `postgres` with password `postgres` on port `5432`. If your local setup is different, just update the variables in `src/main/resources/application.properties`.

### 2. Backend
Open your terminal in the root directory and run the Spring Boot app:

The API will start up on `http://localhost:8080`.

### 3. Frontend
Open `Frontend/config.js` and make sure the API URL points to your local server:
```javascript
const API_BASE_URL = "http://localhost:8080";
```
Then just open the `Frontend/index.html` file in your browser. (Using an extension like VS Code Live Server works best).

## API Endpoints
If you want to hit the API directly via Postman or curl, here are the main routes:
- `POST /api/user` (Signup)
- `POST /api/user/login` (Auth)
- `POST /api/expenses/{userId}` (Add an expense)
- `GET /api/expenses/{userId}` (Get all expenses for a user)
- `DELETE /api/expenses/{expId}` (Remove an expense)
- `GET /api/expenses/analytics/{userId}` (Get category breakdown data)
- `GET /api/expenses/analytics/runrate/{userId}` (Get current month run-rate math)
