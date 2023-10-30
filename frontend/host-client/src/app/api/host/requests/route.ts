const port = process.env.BACKEND_URL_ENDPOINT
const requestMapping = "api/host/requests"

export async function GET(request: Request) {
  try {
    const authorization = request.headers.get("authorization");
    if (!authorization) {
      return new Response(JSON.stringify({ message: "No authorization header provided" }), {
        status: 401,
        headers: { "Content-Type": "application/json" }
      });
    }

    const res = await fetch(`${port}/${requestMapping}`, {
      method: "GET",
      headers: { Authorization: authorization },
    });

    const responseBody = res.ok ? await res.json() : { error: `Request failed with status ${res.status}`, message: res.statusText };
    const status = res.ok ? 200 : res.status;

    return new Response(JSON.stringify(responseBody), {
      status,
      headers: { "Content-Type": "application/json" }
    });
  } catch (error) {
    console.error("Error:", error);
    return new Response(JSON.stringify({
      error: "Internal Server Error",
      message: "An unexpected error occurred"
    }), {
      status: 500,
      headers: { "Content-Type": "application/json" }
    });
  }
}
