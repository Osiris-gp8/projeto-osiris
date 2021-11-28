import api from '../api'

export function count(headers, params){
    return api.get('/acessos/contagem', {headers, params})
        .then(res => res.data)
        .catch(err => {
            console.error(err);
            throw err;
        })
}