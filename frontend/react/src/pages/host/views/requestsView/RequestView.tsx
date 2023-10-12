import React from 'react';
import {RequestsViewProvider} from "./RequestsViewModelContext";
import {useParams} from "react-router-dom";
import Requests from "./components/Requests";

const RequestView = () => {
  const {hostId} = useParams() as { hostId: string }

  return (
      <RequestsViewProvider hostId={hostId}>
        <Requests/>
      </RequestsViewProvider>
  );
};

export default RequestView;
