import { QueryClient, QueryClientProvider, useQuery } from "@tanstack/react-query";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";


const Test = () => {
    const queryClient = new QueryClient();
    return(
        <div>
            <p>test</p>
        </div>
    )
};

export default Test;
