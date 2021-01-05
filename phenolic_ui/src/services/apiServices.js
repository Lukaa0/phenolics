import API from './apiClient';

export const fetchData = async (apiUrl) => {
  return await API.get(apiUrl);
};
