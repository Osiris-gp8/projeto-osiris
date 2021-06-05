import React from 'react'
import {Chart} from 'react-google-charts'

function ChartPie(props){
    const options={
        title: props.title,
        pieHole: props.pieHole,
        pieSliceText: "none",
        colors: props.colors,
        chartArea: {
            width: "100%",
            height: "100%"
        }
    }
    return (
        <Chart
            id={props.id}
            width={props.width}
            height={props.height}
            chartType="PieChart"
            loader={<span className="load">Carregando Dados</span>}
            data={props.data}
            options={options}
        />
    );
}

export default ChartPie;