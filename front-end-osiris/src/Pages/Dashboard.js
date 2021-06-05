import MenuNovo from '../Components/MenuNovo/MenuNovo';
import Metricas from '../Components/Metricas/Metricas';
import arrowUpCircleFill from '@iconify-icons/bi/arrow-up-circle-fill';
import clockCircleFilled from '@iconify-icons/ant-design/clock-circle-filled';
import ChartBar from '../Components/ChartBar/ChartBar';
import ChartPie from '../Components/ChartPie/ChartPie';
import { useHistory } from 'react-router-dom';
import { useEffect } from 'react';

export default () =>{ 
    // const history = useHistory();
    // useEffect(() => {
    //     if(sessionStorage.getItem("usuarioLogado") == null || !sessionStorage.getItem("usuarioLogado")){
    //         console.log("volta")
    //         return history.push("/login"); 
    //     }
    // }, []);

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

    return (
        <>
            <MenuNovo/>
            <div className="metricas">
                <Metricas 
                    metrica="Vendas" 
                    valor="+1,569" 
                    meta="3,600"
                    icon={arrowUpCircleFill}
                />

                <Metricas 
                    metrica="Clientes" 
                    valor="+56" 
                    meta="300"
                    icon={arrowUpCircleFill}
                />

                <Metricas 
                    metrica="Tempo de uso dos clientes" 
                    valor="15min" 
                    meta="12min"
                    icon={clockCircleFilled}
                />
            </div>

            <div className="chart-area">
                <ChartBar
                    id="chart-acessos"
                    width="95%"
                    data={dados}
                    title="Acessos da Semana"
                    colors={[cores[1], cores[2]]}
                    titleX="Dias da semana"
                    titleV="Acessos x Vendas"
                />
            </div>

            <div className="chart-area">
                <div className="charts-pie">
                    <ChartPie
                        width="50%"
                        data={dadosPie1}
                        title="Tipo de Venda"
                        pieHole= {0.4}
                        colors={cores}
                    />
                </div>
            </div>
        </>
    );
}