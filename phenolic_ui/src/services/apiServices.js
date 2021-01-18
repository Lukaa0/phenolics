import API from "./apiConfiguration";

export const getData = async (apiUrl) => {
  const response = await API.get(apiUrl);
  return response.data;
};
