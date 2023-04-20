import axios from "axios";
import { getAccessToken } from "utils/localStorage";

const axiosInstance = axios.create({
  baseURL: process.env.REACT_APP_API_URL,
  timeout: 25000,
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true,
});

axiosInstance.interceptors.request.use((request) => {
  const accessToken = getAccessToken();
  const accessHeader = `Bearer ${accessToken}`;
  if (request.headers) { 
    request.headers["Authorization"] = accessHeader;
  }
  return request;
});

export default axiosInstance;
