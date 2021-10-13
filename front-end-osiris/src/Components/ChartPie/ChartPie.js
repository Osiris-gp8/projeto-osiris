import React from 'react'
import {Chart} from 'react-google-charts'

function ChartPie(props){
    const data = props.data
    console.log(data)
    const options={
        title: props.title,
        pieHole: props.pieHole,
        colors: props.colors,
        backgroundColor: "#ECF0F7",
        chartArea: {
            width: "100%",
            height: "100%"
        },
        legend: {
            position: "right",
            alignment: "center",
            textStyle: {
                fontSize: 18
            }
        }
    }
    return (
        <>
        {
        data?.length > 1 ?
        <Chart
            id={props.id}
            width={props.width}
            height={props.height}
            chartType="PieChart"
            loader={<span className="load">Carregando Dados</span>}
            data={props.data}
            options={options}
        />
        :
        <span className="load">Carregando Dados</span>
        }
        </>
    );
}

export default ChartPie;