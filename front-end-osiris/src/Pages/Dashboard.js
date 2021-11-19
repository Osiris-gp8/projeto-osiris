import { React, useEffect, useState, useMemo } from 'react';
import MenuNovo from '../Components/MenuNovo/MenuNovo';
import Metricas from '../Components/Metricas/Metricas';
import arrowUpCircleFill from '@iconify-icons/bi/arrow-up-circle-fill';
import clockCircleFilled from '@iconify-icons/ant-design/clock-circle-filled';
import ChartBar from '../Components/ChartBar/ChartBar';
import ChartPie from '../Components/ChartPie/ChartPie';
import { useHistory } from 'react-router-dom';
import api from '../api';
import {getAllEvents,getCountUser, getCountAccess} from '../services/textData'
import {getRankingSell, getCountSell} from '../services/dashData'
import {getIntervalMonthDays, getIntervalSixMonths} from '../services/utils'

const Dashboard = () =>{ 

    const [loading, setLoading] = useState({
        clientes: true,
        vendas: true,
        acessos: true
    });

    const history = useHistory();
    const [calcados, setCalcados] = useState([]);
    const [metas, setMetas] = useState([{}, {}]);
    const [weekData, setWeekData] = useState([]);
    const [cupons, setCupons] = useState([]);
    const [eventos, setEventos] = useState(0);
    const [countUsers, setCountUsers] = useState(0);
    const [countAccess, setCountAccess] = useState(0);
    const header = useMemo(() => {
        return {"Authorization": `${sessionStorage.getItem("tipo")} ${sessionStorage.getItem("token")}`}
    }, []);
    const translate_day = useMemo(() => {
        return {
            'MONDAY': 'Segunda',
            'TUESDAY': 'Terça',
            'WEDNESDAY':'Quarta',
            'THURSDAY':'Quinta',
            'FRIDAY':'Sexta',
            'SATURDAY':'Sabádo',
            'SUNDAY':'Domingo',
        };
    }, []); 

    useEffect(() =>{
        if(!sessionStorage.getItem("token")){
            return history.push('/login');
        }

        setCalcados(getRankingSell("/metricas/ranque-produtos", header))
        
        getAllEvents(header).then(
            data => setEventos(data.data.length)
        );
    }, [header, history]);

    useEffect(() => {
        function getMetas(){
            // const interval = getIntervalMonthDays();
            const interval = ['2021-07-01', '2021-07-30'];
            return api.get('/metas', {
                headers: header,
                params: { dataInicio: interval[0], dataFinal: interval[1] }
            });
        }
        getMetas()
            .then(resposta => {
                setLoading({
                    vendas: false, clientes: false, acessos: false
                })
                setMetas(resposta.data)
            });

        getCountSell(header)
            .then(data => {
                setLoading({vendas: false})
                setCupons(data)
            });

        getCountUser(header)
            .then(data => {
                setLoading({clientes: false})
                setCountUsers(data)
            })

        getCountAccess(header)
            .then(data => {
                setLoading({acessos: false})
                setCountAccess(data)
            })

    }, [header]);

    useEffect(() => {
        const interval = getIntervalSixMonths()

        api.get("/dash/contagem-acessos-vendas", { 
            headers: header,
            params: interval
        }).then(data => {
            const arrayData = []
            data.data.forEach((value) => {
                arrayData.push([translate_day[value['diaDaSemana'].toUpperCase()],value['vendas'], value['acessos']])
            })
            setWeekData(arrayData)
        })
    }, [header, translate_day]);

    const cores = ["#666BC2", "#8CA8D1", "#B3C8E1", "#D9E2F0", "#ECF0F7"];
    const dados = [
        ['Dia da semana', 'Acessos', 'Vendas'],
        ...weekData
    ];

    return (
        <>
            <MenuNovo/>
            <div className="metricas">
                <Metricas 
                    metrica="Vendas"
                    valor={eventos} 
                    meta={Math.floor(metas[0]?.valor)}
                    icon={arrowUpCircleFill}
                    isLoading={loading.vendas}
                />

                <Metricas 
                    metrica="Clientes"
                    valor={countUsers}
                    meta={Math.floor(metas[1]?.valor)}
                    icon={arrowUpCircleFill}
                    isLoading={loading.clientes}
                />

                <Metricas 
                    metrica="Acessos" 
                    valor={countAccess}
                    meta={Math.floor(metas[2]?.valor)}
                    icon={clockCircleFilled}
                    isLoading={loading.acessos}
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

export default Dashboard;