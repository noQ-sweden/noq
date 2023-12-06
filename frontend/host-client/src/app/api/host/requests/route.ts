const port = process.env.BACKEND_URL_ENDPOINT
const requestMapping = "api/host/bookings"

const handleResponse = async (response: Response, successStatus = 200) => {
  try {
    if (response.status === 401) {
      return new Response(
          JSON.stringify({error: "Unauthorized", message: "Authorization header missing or invalid"}),
          {status: 401, headers: {"Content-Type": "application/json"}}
      );
    }

    const responseBody = await response.json();
    console.log(responseBody)
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



 //får inte den här till att visa sig?
export async function GET(request: Request) {

const hostId = "b118ae60-f08e-4277-8f5e-1e98a33625df"

  try {
    const authorization = request.headers.get("authorization");
    if (!authorization) {
      return handleResponse(new Response(null), 401);
    }

    const res = await fetch(`${port}/${requestMapping}/${hostId}`, {
      method: "GET",
      headers: {Authorization: authorization},
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

    const res = await fetch(`${port}/${requestMapping}/update-reservation-status-field`, {
      method: "PUT",
      headers: {Authorization: authorization, "Content-Type": "application/json"},
      body: JSON.stringify(requestBody),
    });

    return await handleResponse(res);
  } catch (error) {
    return handleResponse(new Response(null), 500);
  }
}
