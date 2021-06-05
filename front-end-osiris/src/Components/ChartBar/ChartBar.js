import React from 'react';
import { Chart } from 'react-google-charts'

function ChartBar(props){
    const options = {
        title: props.title,
        legend: {
            position: "top",
            alignment: "center"
        },
        colors: props.colors,
        chartArea: {
            width: "100%",
            backgroundColor: "#ECF0F7"
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
            id={props.id}
            width={props.width}
            height={props.height}
            chartType="Bar"
            loader={<span className="load">Carregando Dados</span>}
            data={props.data}
            options={options}
        />
    );
}

export default ChartBar;