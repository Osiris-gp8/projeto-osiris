import { AccessByUFChart } from "../Components/AccessByUFChart"
import { useState, useEffect } from "react";
import { useHistory } from "react-router";
import MenuNovo from "../Components/MenuNovo/MenuNovo";

export default () =>{

    const history = useHistory();

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
                <AccessByUFChart/>
            </div>
            </div>
        </div>
    )
}