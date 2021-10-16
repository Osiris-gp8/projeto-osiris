import axios from 'axios';

const api = axios.create({
    baseURL: "http://34.197.240.214",
    headers: {
        "Access-Control-Allow-Origin": "http://34.197.240.214",
        "Access-Control-Allow-Headers": "Authorization",
        "Access-Control-Allow-Methods": "GET, POST, OPTIONS, PUT, PATCH, DELETE"
    }
})

export default api;
