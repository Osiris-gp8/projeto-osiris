import axios from 'axios';

const api = axios.create({
    baseURL: "springboot:8080",
    headers: {
        "Access-Control-Allow-Origin": "springboot:8080",
        "Access-Control-Allow-Headers": "Authorization",
        "Access-Control-Allow-Methods": "GET, POST, OPTIONS, PUT, PATCH, DELETE"
    }
})

export default api;
