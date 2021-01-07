import { useLocation } from "react-router-dom";

const useQuery = () => {
  const params = new URLSearchParams(useLocation().search);
  return params.get("id");
};

export default useQuery;
