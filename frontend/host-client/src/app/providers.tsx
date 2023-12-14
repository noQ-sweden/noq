import {ReactNode} from "react";
import QueryClientWrapper from "@/utilities/QueryClientWrapper";

export default function Providers({children}: { children: ReactNode }) {
  return (
      <QueryClientWrapper>
        {children}
      </QueryClientWrapper>
  );
}
