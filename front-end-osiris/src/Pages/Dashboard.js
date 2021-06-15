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
    useEffect(() =>{
        if(!sessionStorage.getItem("token")){
            return history.push('/login');
        }

        api.get("/metas").then(res => {
            console.log(res);
        }).catch(err => {
            console.log(err);
        });

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

    }, []);

    const cores = ["#666BC2", "#8CA8D1", "#B3C8E1", "#D9E2F0", "#ECF0F7"];
    
    const dados = [
        ['Dia da semana', 'Acessos', 'Vendas'],
        ['Domingo', 2014, 1000],
        ['Segunda', 2015, 1170],
        ['Terça', 2016, 660],
        ['Quarta', 2017, 1030],
        ['Quinta', 2017, 1030],
        ['Sexta', 2017, 1030],
        ['Sábado', 2017, 1030],
    ];

    const dadosPie1 = [
        ['Tipo de venda', 'Valor'],
        ['Vendas com cupom', 200],
        ['Vendas sem cupom', 300],
        ['Expirou o cupom', 200]];
    
    const dadosPie2 = [
        ['Tipo de calçado', 'Valor'],
        ['Skate', 200],
        ['Social', 300],
        ['Esportivo', 450],
        ['Casual', 100]
    ];

    return (
        <>
            <MenuNovo/>
            <div className="metricas">
                <Metricas 
                    metrica="Vendas" 
                    valor="+1,569" 
                    color="red"
                    meta="3,600"
                    icon={arrowUpCircleFill}
                />

                <Metricas 
                    metrica="Clientes" 
                    valor="+56" 
                    color="yellow"
                    meta="300"
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
                            data={dadosPie1}
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