import axios from 'axios';

const api = axios.create({
    baseURL: "http://back-loadbalance:80",
    headers: {
        "Access-Control-Allow-Origin": "http://back-loadbalance:80",
        "Access-Control-Allow-Headers": "Authorization",
        "Access-Control-Allow-Methods": "GET, POST, OPTIONS, PUT, PATCH, DELETE",
        "Authorization": `${sessionStorage.getItem("tipo")} ${sessionStorage.getItem("token")}`
    }
})

export default api;
