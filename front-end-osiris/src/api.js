import axios from 'axios';

const api = axios.create({
    baseURL: "http://osiris-network:85",
    headers: {
        "Access-Control-Allow-Origin": "http://osiris-network:85",
        "Access-Control-Allow-Headers": "Authorization",
        "Access-Control-Allow-Methods": "GET, POST, OPTIONS, PUT, PATCH, DELETE",
        "Authorization": `${sessionStorage.getItem("tipo")} ${sessionStorage.getItem("token")}`
    }
})

export default api;
