import { React, useEffect, useState } from 'react';
import MenuNovo from '../Components/MenuNovo/MenuNovo';
import Metricas from '../Components/Metricas/Metricas';
import arrowUpCircleFill from '@iconify-icons/bi/arrow-up-circle-fill';
import clockCircleFilled from '@iconify-icons/ant-design/clock-circle-filled';
import ChartBar from '../Components/ChartBar/ChartBar';
import ChartPie from '../Components/ChartPie/ChartPie';
import { useHistory } from 'react-router-dom';
import api from '../api';

export default () =>{ 

    const history = useHistory();
    const [calcados, setCalcados] = useState([]);
    const [metas, setMetas] = useState([{}, {}]);
    const [cupons, setCupons] = useState([]);


    useEffect(() =>{
        if(!sessionStorage.getItem("token")){
            return history.push('/login');
        }

        api.get("/metricas/ranque-categoria").then(res => {
            console.log(res);
            let calcadosApi = [];
            calcadosApi.push(['Tipo de calçado', 'Valor']);
            res.data.forEach(e => {
                calcadosApi.push([e.nome, e.quantidades]);
            });
            setCalcados(calcadosApi);
            console.log(calcados);
        }).catch(err => {
            console.log(err);
        });

        api.get("/eventos/com-sem-cupom").then(res => {
            console.log(res);
            setCupons([
                ['cupom', 'valor'], 
                ['Vendas com cupom', res.data.contagemEventosComCupom],
                ['Vendas sem cupom', res.data.contagemEventosSemCupom]
            ]);
        }).catch(err => {
            console.log(err);
        })

    }, []);

    useEffect(() => {
        async function getMetas(){
            const resposta = await api.get("/metas");
            setMetas(resposta.data);
            console.log(resposta.data);
        }

        getMetas();
    }, []);

    const cores = ["#666BC2", "#8CA8D1", "#B3C8E1", "#D9E2F0", "#ECF0F7"];
    
    const dados = [
        ['Dia da semana', 'Acessos', 'Vendas'],
        ['Domingo', 2014, 1000],
        ['Segunda', 1568, 1170],
        ['Terça', 1233, 660],
        ['Quarta', 1852, 500],
        ['Quinta', 1233, 366],
        ['Sexta', 2888, 963],
        ['Sábado', 2600, 800],
    ];

    return (
        <>
            <MenuNovo/>
            <div className="metricas">
                <Metricas 
                    metrica={metas[0].labelTipo}
                    valor="+211" 
                    color="green"
                    meta={metas[0].valor}
                    icon={arrowUpCircleFill}
                />

                <Metricas 
                    metrica={metas[1].labelTipo} 
                    valor="+56" 
                    color="orange"
                    meta={metas[1].valor}
                    icon={arrowUpCircleFill}
                />

                <Metricas 
                    metrica="Uso dos clientes" 
                    valor="15min" 
                    color="green"
                    meta="12min"
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