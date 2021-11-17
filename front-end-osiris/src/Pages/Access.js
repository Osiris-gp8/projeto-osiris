import { AccessByUFChart } from "../Components/AccessByUFChart"
import { useState, useEffect } from "react";
import { useHistory } from "react-router";
import MenuNovo from "../Components/MenuNovo/MenuNovo";
import MapChart from "../Components/MapChart";
import ReactToolTip from 'react-tooltip';

export default () =>{

    const history = useHistory();

    const [content, setContent] = useState('');

    useEffect(() => {
        if(!sessionStorage.getItem("token")){
            return history.push('/login');
        }
    }, [])
    return (
        <div>
            <MenuNovo />
            <div className="body-config">
            <div className="container">
                <h1>Acessos</h1>
                <MapChart setTooltipContent={setContent}/>
                <ReactToolTip  multiline={true} html={true}>{content}</ReactToolTip>
            </div>
            </div>
        </div>
    )
}