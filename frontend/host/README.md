docker run --rm -it -p 70:70 -v ${PWD}:/workdir -w /workdir node:18 bash

echo .env.local > VITE_BACKEND_URL_ENDPOINT=http://localhost:8080/

