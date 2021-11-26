import api from '../api';

export function countEventos(headers, params){
    return api.get("/eventos/contagemEvento", {headers, params})
        .then(result => result.data)
        .catch(err => {
            console.log(err)
            throw err;
        });
}
