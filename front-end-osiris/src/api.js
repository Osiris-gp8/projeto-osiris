import axios from 'axios';

const api = axios.create({
    baseURL: process.env.REACT_APP_API_BASE_URL,
    headers: {
        "Access-Control-Allow-Origin": process.env.REACT_APP_API_BASE_URL,
        "Access-Control-Allow-Headers": "Authorization",
        "Access-Control-Allow-Methods": "GET, POST, OPTIONS, PUT, PATCH, DELETE"
    }
})

export default api;
