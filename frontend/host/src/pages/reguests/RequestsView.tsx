import React from 'react';
import RequestsViewProvider from "./RequestsViewContext";
import Requests from "./componens/Requests";

const RequestsView = () => {
  return (
      <RequestsViewProvider>
        <Requests/>
      </RequestsViewProvider>
  );
};

export default RequestsView;
