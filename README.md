# noQ

Webbapp for people in need of housing for the night at shelters. 
Written in React with Typescript, Java Spring Boot.


## Setup

1. Open a new terminal.
2. Navigate to the `frontend` directory: `cd frontend/react`
3. Install dependencies: `npm install`
4. Start the Vite development server:
```
   npm run dev
```
5. The frontend will be available at `http://localhost:80`.
6. Install [Docker](https://docs.docker.com/engine/install/) 
7. Open terminal at correct location and run
```
   docker build . -t noq
   docker-compose up
```
8. The backend will run at `http://localhost:8080`.
9. In file frontend project go to `src/api/ApiRootURl.ts` and change patch from ROOT to local


## Contributing

1. Fork the repository.
2. Create a new branch: `git checkout -b feature/your-feature-name`
3. Make your changes and commit them: `git commit -m 'Add some feature'`
4. Push to the branch: `git push origin feature/your-feature-name`
5. Create a pull request.


## License

This project is licensed under the [MIT License](LICENSE).
