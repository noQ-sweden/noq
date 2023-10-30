docker run --rm -it --network host -v ${PWD}:/workdir -w /workdir node:18 bash

echo .env.local
local dev CLIENT_DOMAIN=http://localhost:3000
BACKEND_URL_ENDPOINT=http://localhost:8080
