const port = process.env.BACKEND_URL_ENDPOINT
const requestMapping = "api/user/booking"

const handleResponse = async (response: Response, successStatus = 200) => {
  try {
    if (response.status === 401) {
      return new Response(
          JSON.stringify({error: "Unauthorized", message: "Authorization header missing or invalid"}),
          {status: 401, headers: {"Content-Type": "application/json"}}
      );
    }

    const responseBody = await response.json();
    return new Response(JSON.stringify(responseBody), {
      status: response.ok ? successStatus : response.status,
      headers: {"Content-Type": "application/json"}
    });
  } catch (error) {
    console.error("Error:", error);
    return new Response(
        JSON.stringify({error: "Internal Server Error", message: "An unexpected error occurred"}),
        {status: 500, headers: {"Content-Type": "application/json"}}
    );
  }
};

export async function GET(request: Request) {
  try {
    const authorization = request.headers.get("authorization");
    if (!authorization) {
      return handleResponse(new Response(null), 401);
    }

    const res = await fetch(`${port}/${requestMapping}`, {
      method: "GET",
    });

    return await handleResponse(res);
  } catch (error) {
    return handleResponse(new Response(null), 500);
  }
}

export async function PUT(request: Request) {
  try {
    const requestBody = await request.json();
    const authorization = request.headers.get("authorization");
    if (!authorization) {
      return handleResponse(new Response(null), 401);
    }
    console.log(requestBody)
    const res = await fetch(`${port}/${requestMapping}/send-host-request`, {
      method: "PUT",
      body: requestBody
    });

    return await handleResponse(res, 201);
  } catch (error) {
    return handleResponse(new Response(null), 500);
  }
}

