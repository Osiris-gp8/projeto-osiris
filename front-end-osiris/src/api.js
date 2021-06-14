import axios from 'axios';

const api = axios.create({
    baseURL: "http://localhost:8080",
    headers: {
        Authorization: `${sessionStorage.getItem("tipo")} ${sessionStorage.getItem("token")}`
    }
})

export default api;