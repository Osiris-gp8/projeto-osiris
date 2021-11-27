import axios from 'axios';

const api = axios.create({
    baseURL: "172.31.24.78:82",
    headers: {
        "Access-Control-Allow-Origin": "172.31.24.78:82",
        "Access-Control-Allow-Headers": "Authorization",
        "Access-Control-Allow-Methods": "GET, POST, OPTIONS, PUT, PATCH, DELETE"
    }
})

export default api;
