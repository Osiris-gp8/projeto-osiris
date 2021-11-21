import api from '../api';

export function getRankingSell(endpoint, header){
    const rankList = []

    api.get(endpoint, {headers:header, params: { dataInicio: '2021-04-01', dataFinal: '2021-09-11' }}).then(res => {
        rankList.push(['Nome calçado', 'Quantidade']);
        res.data.forEach(e => {
            rankList.push([e.produto, e.quantidade]);
        });
        
    });
    return rankList
}

export async function getCountSell( header, dataInicio, dataFinal){
    let data
    await api.get(`/eventos/com-sem-cupom?dataFinal=${dataInicio}&dataInicio=${dataFinal}`, {headers:header}).then(res => {
        data = ([
            ['cupom', 'valor'], 
            ['Vendas com cupom', res.data.contagemEventosComCupom],
            ['Vendas sem cupom', res.data.contagemEventosSemCupom]
        ]);
    }).catch(erro => console.log(erro));
    
    return data
}



