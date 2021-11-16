import api from '../api';
import {getIntervalMonthDays, getIntervalWeekDays} from './utils'

export async function getData(endpoint, header){
    
        return await api.get(endpoint, {headers:header})
}

export async function getDataMonth(endpoint, header){
    // const intervalDays = getIntervalMonthDays()
    const intervalDays = ['2021-07-01', '2021-07-30'];
    return await getData(`${endpoint}?dataInicio=${intervalDays[0]}&dataFinal=${intervalDays[1]}`, header)
}

export async function getDataWeek(endpoint, header){
    const intervalDays = getIntervalWeekDays()
    return await getData(`${endpoint}?dataInicio=${intervalDays[0]}&dataFinal=${intervalDays[1]}`, header)
}

export  async  function getAllEvents(header){
    return (await getData("/eventos",header))
}

export async function getCountUser(header, dataInicio, dataFinal){
    let data = getData(`/eventos/countClientes?dataFinal=${dataFinal}&dataInicio=${dataInicio}`, header)
    return (await data).data
}

export async function getCountAccess(header){
    let data = getDataMonth("/acessos/contagem", header)
    return (await data).data
}