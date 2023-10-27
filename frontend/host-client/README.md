docker run --rm -it -p 3000:3000 -v ${PWD}:/workdir -w /workdir node:18 bash

echo .env.local > NEXT_PUBLIC_BACKEND_URL_ENDPOINT=http://localhost:8080/

