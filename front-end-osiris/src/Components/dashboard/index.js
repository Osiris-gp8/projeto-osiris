import { Container, Dashs, Metrics } from './style'
import { Chart } from 'react-google-charts'
import { Icon, InlineIcon } from '@iconify/react';

import clockCircleFilled from '@iconify-icons/ant-design/clock-circle-filled';
import arrowUpCircleFill from '@iconify-icons/bi/arrow-up-circle-fill';
import { useEffect, useState } from 'react';
import api from '../../api.js';


const DonutChart = (props) => {
    return (
        <Chart
            width={'70%'}
            height={'100%'}
            chartType="PieChart"
            loader={<div>Carregando Dash</div>}
            data={props.data}
            options={{
                titleTextStyle: {
                    fontSize: 20
                },
                title: props.title,
                legend: { textStyle: { fontSize: 20 } },
                // Just add this option
                pieHole: 0.4,
                pieSliceText: "none",
                chartArea: { width: '100%',height:'80%' },
                colors: props.colors,

            }}
            rootProps={{ 'data-testid': '3' }}
        />
    )
}

function MetricsFinal(props) {
    return (
        <Metrics >
            <div>
                <span>
                    {props.label}
                </span>
                <Icon icon={props.icon} />
                <h3>
                    {props.value}
                </h3>
            </div>
        </Metrics>)
}

async function getVendas(){
            const resposta = await api.get("/eventos");
            return resposta;
        }

async function getCountVendas() {
        const resposta = await getVendas();
        return resposta.status == 204 ? 0 : resposta.data.length;
}

export default () => {
    
    const [totalVendas, setTotalVendas] = useState(0);

    useEffect(async ()=> {
        setTotalVendas(await getCountVendas());
    }, [])

    return (
        <Container>
            <Dashs>
                <MetricsFinal
                    icon={clockCircleFilled} 
                    label="Vendas"
                    value={"+"+totalVendas}
                />
                <MetricsFinal
                    icon={clockCircleFilled} 
                    label="Clientes"
                    value="+56"
                />
                <MetricsFinal
                    icon={arrowUpCircleFill} 
                    label="Tempo de uso dos clientes"
                    value="15min"
                />
            </Dashs>
            <Dashs>
                <Chart
                    width={'100%'}
                    height={'90%'}
                    chartType="Bar"
                    loader={<div>Carregando Dash</div>}
                    data={[
                        ['Dia da semana', 'Acessos', 'Vendas'],
                        ['Domingo', 2014, 1000],
                        ['Segunda', 2015, 1170],
                        ['Terça', 2016, 660],
                        ['Quarta', 2017, 1030],
                        ['Quinta', 2017, 1030],
                        ['Sexta', 2017, 1030],
                        ['Sábado', 2017, 1030],
                    ]}
                    options={{
                        // Material design options
                        legend: { position: 'top', textStyle: { color: 'blue', fontSize: 16 } },
                        chart: {
                            title: 'Acessos da semana'
                        },
                        colors: ['#666BC2', '#8CA8D1'],

                    }}
                    // For tests
                    controls={[
                        {
                            controlEvents: [
                                {
                                    eventName: 'statechange',
                                    callback: ({ chartWrapper, controlWrapper }) => {
                                        return
                                    },
                                },
                            ],
                            controlType: 'CategoryFilter',
                            options: {
                                filterColumnIndex: 0,
                                ui: {
                                    labelStacking: 'vertical',
                                    label: 'Dia da semana',
                                    allowTyping: false,
                                    allowMultiple: false,
                                    caption: "Escolha",
                                    sortValues: false
                                },
                            },
                        },
                    ]}
                    rootProps={{ 'data-testid': '2' }}
                />
            </Dashs>
            <Dashs>
                <DonutChart
                    title={'Tipo de venda'}
                    data={[
                        ['Tipo de venda', 'Valor'],
                        ['Vendas com cupom', 200],
                        ['Vendas sem cupom', 300],
                        ['Expirou o cupom', 200]]
                    }
                    colors={
                        ["#666BC2", "#8CA8D1", "#D9E2F0"]
                    }
                />
                <DonutChart
                    title={'Tipo de calçados vendidos'}
                    data={
                        [
                            ['Categoria', 'Valor'],
                            ['Vendas com cupom', 200],
                            ['Vendas sem cupom', 300],
                            ['Expirou o cupom', 200],
                            ['Expirou o cupom', 200],
                        ]
                    }
                    colors={
                        ["#666BC2", "#0067FF", "#8CA8D1", "#071393"]
                    }
                />

            </Dashs>
        </Container>
    );
}