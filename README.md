# noQ

Webbapp for people in need of housing for the night at shelters. 
Written in React with Typescript, Java Spring Boot and Azure Cosmos db.

## Backend Setup

1. Clone this repository: `git clone https://github.com/your-username/your-repo.git`
2. Navigate to the `backend` directory: `cd noq-backend`
3. Build and run the backend application:

```
   ./mvnw clean package
   java -jar target/noq-backend-app.jar
```
4. The backend will run at `http://localhost:8080`.

## Frontend Setup

1. Open a new terminal.
2. Navigate to the `frontend` directory: `cd frontend/react`
3. Install dependencies: `npm install`
4. Start the Vite development server:
```
   npm run dev
```
5. The frontend will be available at `http://localhost:80`.

## Accessing the Application

- The frontend can be accessed at: [http://localhost:80](http://localhost:3000)
- The backend API is available at: [http://localhost:8080](http://localhost:8080)


## Contributing

1. Fork the repository.
2. Create a new branch: `git checkout -b feature/your-feature-name`
3. Make your changes and commit them: `git commit -m 'Add some feature'`
4. Push to the branch: `git push origin feature/your-feature-name`
5. Create a pull request.


## License

This project is licensed under the [MIT License](LICENSE).