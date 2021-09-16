import axios from 'axios';

const api = axios.create({
    baseURL: "54.160.69.199:8080",
    headers: {
        "Access-Control-Allow-Origin": "54.160.69.199:8080",
        "Access-Control-Allow-Headers": "Authorization",
        "Access-Control-Allow-Methods": "GET, POST, OPTIONS, PUT, PATCH, DELETE",
        "Authorization": `${sessionStorage.getItem("tipo")} ${sessionStorage.getItem("token")}`
    }
})

export default api;
