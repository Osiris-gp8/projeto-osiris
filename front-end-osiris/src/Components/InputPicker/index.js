import { useState } from "react"
import {DateContainer} from "./style"

const DatePicker = (props) => {

    const [options, setOptions] = useState(["Ambos", "Vendas", "Cupom"])

    const insertOptions = (value, index) => (<option value={index}>{value}</option>)
    
    const Options = () => 
        <>
            {options.map(insertOptions)}
        </>
            

    return(
        <DateContainer>
            <label for={props.id}>{props.label}</label>
            {props.select ? (<select onChange={props.event} id={props.id}><Options/></select>): (<input onChange={props.event} id={props.id} type="date" />)}
        </DateContainer>
    )
}


export default DatePicker

