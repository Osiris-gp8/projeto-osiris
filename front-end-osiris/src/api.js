import axios from 'axios';

const api = axios.create({
    baseURL: OSIRIS_BACK_URL,
    headers: {
        "Access-Control-Allow-Origin": OSIRIS_BACK_URL,
        "Access-Control-Allow-Headers": "Authorization",
        "Access-Control-Allow-Methods": "GET, POST, OPTIONS, PUT, PATCH, DELETE"
    }
})

export default api;
