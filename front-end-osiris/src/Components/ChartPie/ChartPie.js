import React from 'react'
import {Chart} from 'react-google-charts'
import Loader from 'react-loader-spinner'

function ChartPie(props){
    const data = props.data
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
    
    if (data === undefined){
        return (
            <Loader type="Oval" color="#00BFFF" height={80} width={80}/>
        );
    }else if (data.length <= 1 || data === 0){
        return ( <span> Dados não foram encontrados no período </span> );
    } else{
        return (
            <>
                <Chart
                    id={props.id}
                    width={props.width}
                    height={props.height}
                    chartType="PieChart"
                    loader={<Loader type="Oval" color="#00BFFF" height={80} width={80}/>}
                    data={props.data}
                    options={options}
                    />
            </>
        );
    }
    
}

export default ChartPie;