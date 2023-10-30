const port = process.env.BACKEND_URL_ENDPOINT
const requestMapping = "api/host/requests"

export async function GET(request: Request) {
  const authorization = request.headers.get("authorization");
  if (!authorization) return Response.json({message: "No authorization header provided"});

  const res = await fetch(`${port}/${requestMapping}`, {
    method: "GET",
    headers: {Authorization: authorization},
  }).then(res => res.json());
  return Response.json(res)
}
