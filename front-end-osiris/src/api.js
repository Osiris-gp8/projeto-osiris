import axios from 'axios';

const api = axios.create({
    baseURL: "back-loadbalance:90",
    headers: {
        "Access-Control-Allow-Origin": "back-loadbalance:90",
        "Access-Control-Allow-Headers": "Authorization",
        "Access-Control-Allow-Methods": "GET, POST, OPTIONS, PUT, PATCH, DELETE",
        "Authorization": `${sessionStorage.getItem("tipo")} ${sessionStorage.getItem("token")}`
    }
})

export default api;
