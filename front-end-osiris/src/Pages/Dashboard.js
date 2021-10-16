import { React, useEffect, useState } from 'react';
import MenuNovo from '../Components/MenuNovo/MenuNovo';
import Metricas from '../Components/Metricas/Metricas';
import arrowUpCircleFill from '@iconify-icons/bi/arrow-up-circle-fill';
import clockCircleFilled from '@iconify-icons/ant-design/clock-circle-filled';
import ChartBar from '../Components/ChartBar/ChartBar';
import ChartPie from '../Components/ChartPie/ChartPie';
import { useHistory } from 'react-router-dom';
import api from '../api';
import {getDataWeek,getAllEvents,getCountUser, getCountAccess} from '../services/textData'
import {getRankingSell, getCountSell} from '../services/dashData'

export default () =>{ 

    const history = useHistory();
    const [calcados, setCalcados] = useState([]);
    const [metas, setMetas] = useState([{}, {}]);
    const [weekData, setWeekData] = useState([]);
    const [cupons, setCupons] = useState([]);
    const [eventos, setEventos] = useState(0);
    const [countUsers, setCountUsers] = useState(0);
    const [countAccess, setCountAccess] = useState(0);
    const [header, setHeader] = useState({
        "Authorization": `${sessionStorage.getItem("tipo")} ${sessionStorage.getItem("token")}`
    });

    useEffect(async () =>{
        if(!sessionStorage.getItem("token")){
            return history.push('/login');
        }

        setCalcados(getRankingSell("/metricas/ranque-categoria", header))
        
        
        const eventos = (await getAllEvents(header));
        setEventos(eventos.data.length);
        
        

    }, []);

    useEffect(async() => {
        const dateNow = new Date()
        const dateFormat = `${dateNow.getFullYear()}-${dateNow.getMonth() + 1}-${dateNow.getDate()}`
        const intervalDays = [dateFormat, `2021-${dateNow.getMonth() + 1}-01`]
        async function getMetas(){
            const resposta = await api.get(`/metas?dataFinal=${intervalDays[0]}&dataInicio=${intervalDays[1]}`,
             {headers: header});
            setMetas(resposta.data);
            console.log(resposta.data);
        }
        getMetas();
        setCupons(await getCountSell(header))
        setCountUsers(await getCountUser(header))
        setCountAccess(await getCountAccess(header))
    }, []);

    useEffect(async () => {
        const data = (await getDataWeek("/dash/contagem-acessos-vendas", header)).data
        const arrayData = []
        for (let index in data){
                let value = data[index]
                arrayData.push([translate_day[value['diaDaSemana']],value['vendas'], value['acessos']])
            }
                setWeekData(arrayData)
    }, []);

    const cores = ["#666BC2", "#8CA8D1", "#B3C8E1", "#D9E2F0", "#ECF0F7"];
    
    const translate_day = {
        'MONDAY': 'Segunda',
        'TUESDAY': 'Terça',
        'WEDNESDAY':'Quarta',
        'THURSDAY':'Quinta',
        'FRIDAY':'Sexta',
        'SATURDAY':'Sabádo',
        'SUNDAY':'Domingo',

    }

    const dados = [
        ['Dia da semana', 'Acessos', 'Vendas'],
        ...weekData
    ];

    return (
        <>
            <MenuNovo/>
            <div className="metricas">
                <Metricas 
                    metrica={metas[0].labelTipo}
                    valor={eventos > 0 ? eventos : "Carregando"} 
                    meta={metas[0].valor}
                    icon={arrowUpCircleFill}
                />

                <Metricas 
                    metrica={metas[1].labelTipo} 
                    valor={countUsers > 0 ? countUsers : "Carregando"}
                    meta={metas[1].valor}
                    icon={arrowUpCircleFill}
                />

                <Metricas 
                    metrica="Acessos" 
                    valor={countAccess > 0 ? countAccess : "Carregando"}
                    meta={metas[2] == null ? "Carregando" : metas[2].valor}
                    icon={clockCircleFilled}
                />
            </div>

            <div className="chart-area">
                <ChartBar
                    id="chart-acessos"
                    width="95%"
                    height="30vh"
                    data={dados}
                    title="Acessos da Semana"
                    colors={[cores[1], cores[2]]}
                    titleX="Dias da semana"
                    titleY="Acessos x Vendas"
                />
            </div>

            <div className="chart-area">
                <div className="charts-pie">
                    <div>
                        <h2>Vendas com Cupons</h2>
                        <ChartPie
                            width="100%"
                            data={cupons}
                            title="Tipo de Venda"
                            pieHole= {0.4}
                            colors={cores}
                        />
                    </div>
                    <div>
                        <h2>Calçados mais vendidos</h2>
                        <ChartPie
                            title="Tipo de Venda"
                            width="100%"
                            data={calcados}
                            pieHole= {0.4}
                            colors={cores}
                        />
                    </div>
                </div>
            </div>
        </>
    );
}