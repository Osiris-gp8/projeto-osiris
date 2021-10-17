import axios from 'axios';

const api = axios.create({
    baseURL: "http://54.196.128.247",
    headers: {
        "Access-Control-Allow-Origin": "http://54.196.128.247",
        "Access-Control-Allow-Headers": "Authorization",
        "Access-Control-Allow-Methods": "GET, POST, OPTIONS, PUT, PATCH, DELETE"
    }
})

export default api;
