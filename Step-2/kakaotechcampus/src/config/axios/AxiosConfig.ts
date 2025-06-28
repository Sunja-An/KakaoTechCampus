import axios from "axios";

const SERVER_URL = process.env.NEXT_PUBLIC_SERVER_URL as string;

export const KTC_API = axios.create({
  baseURL: SERVER_URL,
  withCredentials: true,
});
