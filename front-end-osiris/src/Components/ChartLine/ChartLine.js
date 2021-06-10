import React from 'react';
import { Chart } from 'react-google-charts';

function ChartLine(props){
    const options = {
        title: props.title,
        titleTextStyle: {
            fontSize: 20
        },
        legend: {
            position: "top",
            alignment: "center"
        },
        colors: props.colors,
        backgroundColor: "#ECF0F7",
        chartArea: {
            width: "90%",
        },
        hAxis: {
            title: props.titleX
        },
        vAxis: {
            title: props.titleV
        }
    }
    return(
        <Chart
            width={props.width}
            height={props.height}
            chartType="LineChart"
            loader={<div>Carregando gráfico</div>}
            data={props.data}
            options={options}
            rootProps={{ 'data-testid': '1' }}
        />
    );
}

export default ChartLine;