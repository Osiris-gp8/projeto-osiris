import { useState } from "react"
import {DateContainer} from "./style"

const DatePicker = (props) => {

    const [options, setOptions] = useState(["Vendas"])

    const insertOptions = (value) => (<option value={value}>{value}</option>)
    
    const Options = () => 
        <>
            {options.map(insertOptions)}
        </>
            

    return(
        <DateContainer>
            <label for={props.id}>{props.label}</label>
            {props.select ? (<select id={props.id}><Options/></select>): (<input id={props.id} type="date" />)}
        </DateContainer>
    )
}


export default DatePicker

