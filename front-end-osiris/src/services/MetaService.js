import api from "../api";

export function getMetas(headers, params){
    return api.get('/metas', { headers, params })
        .then(result => result.data )
        .catch(error => {
            console.log(error)
            throw error;
        });
}