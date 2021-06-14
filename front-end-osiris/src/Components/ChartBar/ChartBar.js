import React from 'react';
import { Chart } from 'react-google-charts'

function ChartBar(props){
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
            title: props.titleY
        }
    }
    return(
        <Chart
            id={props.id}
            width={props.width}
            height={props.height}
            chartType="ColumnChart"
            loader={<span className="load">Carregando Dados</span>}
            data={props.data}
            options={options}
        />
    );
}

export default ChartBar;