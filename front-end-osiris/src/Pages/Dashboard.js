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
import {countEventos} from '../services/EventoService';
import { getMetas } from '../services/MetaService';

const Dashboard = () =>{ 
    const dateNow = new Date()

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
    const [filtroInicio, setFiltroInicio] = useState(`${dateNow.getFullYear()}-01-01`);
    const [filtroFinal, setFiltroFinal] = useState(`${dateNow.getFullYear()}-${dateNow.getMonth() + 1}-${dateNow.getDate() < 10 ? "0" + dateNow.getDate() : dateNow.getDate()}`);
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


    useEffect(() => {
        if(!sessionStorage.getItem("token")){
            return history.push('/login');
        }
        const filters = { dataInicio: filtroInicio, dataFinal: filtroFinal };
        sessionStorage.setItem("dataInicio", filtroInicio);
        sessionStorage.setItem("dataFinal", filtroFinal);
        setCalcados(getRankingSell("/metricas/ranque-categoria", header, filters))
        
        countEventos(header, filters)
            .then(result => {
                setEventos(result)
            });

        getMetas(header, filters)
            .then(resposta => {
                setLoading({
                    vendas: false, clientes: false, acessos: false
                })
                setMetas(resposta)
            });

        getCountSell(header, filtroInicio, filtroFinal)
            .then(data => {
                setLoading({vendas: false})
                setCupons(data)
            });

        getCountUser(header, filtroInicio, filtroFinal)
            .then(data => {
                setLoading({clientes: false})
                setCountUsers(data)
            })

        getCountAccess(header)
            .then(data => {
                setLoading({acessos: false})
                setCountAccess(data)
            })

    }, [filtroFinal, filtroInicio, header]);

    useEffect(() => {
        const interval = { 
            dataInicio: filtroInicio, 
            dataFinal: filtroFinal 
        }

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
    }, [filtroFinal, filtroInicio, header, translate_day]);

    const cores = ["#666BC2", "#8CA8D1", "#B3C8E1", "#D9E2F0", "#ECF0F7"];
    const dados = [
        ['Dia da semana', 'Acessos', 'Vendas'],
        ...weekData
    ];

    function definirDataInicio(e){
        setFiltroInicio(e.target.value);
        sessionStorage.setItem("dataInicio", filtroInicio);
    }

    function definirDataFinal(e){
        setFiltroFinal(e.target.value);
        sessionStorage.setItem("dataFinal", filtroFinal);
    }

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

            <div className="chart-area position-relative">
                <div className="filtro d-flex flex-colum justify-content-between position-absolute">
                    <div className="d-flex flex-column">
                        <label>Data inicial:</label>
                        <input type="date" onChange={definirDataInicio} value={filtroInicio} className="date-picker"/>
                    </div>

                    <div className="d-flex flex-column">
                        <label>Data final:</label>
                        <input type="date" onChange={definirDataFinal} value={filtroFinal} className="date-picker"/>
                    </div>

                </div>
                
                <ChartBar
                    id="chart-acessos"
                    width="100%"
                    height="30vh"
                    data={dados}
                    title="Acessos da Semana"
                    colors={[cores[1], cores[2]]}
                    titleX="Dias da semana"
                    titleY="Acessos x Vendas"
                />

                <div className="charts-pie">
                    <div>
                        <h2>Vendas com Cupons</h2>
                        <ChartPie
                            width="98%"
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