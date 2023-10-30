import {Metadata} from "next";
import Requests from "@/components/requests/Requests";
import {RequestsViewModel, requestsViewModelMock} from "@/components/requests/RequestsViewModel";

export const metadata: Metadata = {
  title: 'noq',
}

const CLIENT_DOMAIN = process.env.CLIENT_DOMAIN

const getData = async (token: string): Promise<RequestsViewModel> => {
  return fetch(`${CLIENT_DOMAIN}/api/host/requests`, {
    method: "GET",
    cache: "no-store",
    headers: {Authorization: "Bearer " + token},
  }).then(value => value.json());
};

export default async function Page() {
  const token = "fakeToken";
  const data = await getData(token);

  return (
      <>
        <Requests data={data ? data : requestsViewModelMock}/>
      </>
  )
}
