import axios from 'axios';

const api = axios.create({
    baseURL: "http://backend-osiris.ddns.net",
    headers: {
        "Access-Control-Allow-Origin": "http://backend-osiris.ddns.net",
        "Access-Control-Allow-Headers": "Authorization",
        "Access-Control-Allow-Methods": "GET, POST, OPTIONS, PUT, PATCH, DELETE"
    }
})

export default api;
