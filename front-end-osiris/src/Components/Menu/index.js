import { Container, Contrast, Item, WelcomeText, IconChildren } from './style'
// npm install --save-dev @iconify/react @iconify-icons/bx
// import { IconChildren } from '@iconify/react';
import bxHome from '@iconify-icons/bx/bx-home';
import lineChartOutlined from '@iconify-icons/ant-design/line-chart-outlined';
import peopleIcon from '@iconify-icons/bi/people';
import gearFill from '@iconify-icons/bi/gear-fill';
// npm install --save-dev @iconify/react @iconify-icons/cil
import accountLogout from '@iconify-icons/cil/account-logout';
import { Link,useLocation, useHistory } from 'react-router-dom';
import {useState} from 'react'

import api from '../../api';



export default () =>{

    const history = useHistory();

    let uri = "";
    //console.log(JSON.parse(sessionStorage.getItem("usuarioLogado")));
    if(sessionStorage.getItem("usuarioLogado")){
        uri = "/usuarios/logoff?idUsuario=" + JSON.parse(sessionStorage.getItem("usuarioLogado")).idUsuario;
    }

    function logoff(){
        api.get(uri).then( response => {
            console.log(response);
            sessionStorage.removeItem("usuarioLogado");
            return history.push("/login");
        }).catch(error => {
            console.log(error);
        })
    }

    return(
        <Container>
            <WelcomeText children={Text}>
             Bem vindo, <Contrast children="Patrick"/> 
             <br/>Veja as Informações da sua loja
            </WelcomeText>
            <div>
            <Item first active={useLocation().pathname === '/home'} as={Link} to="/home" >
                <IconChildren icon={bxHome} />
                <p>Home</p>
            </Item>
            <Item as={Link} active={useLocation().pathname === '/relation'} to="/relation" >
                <IconChildren icon={lineChartOutlined}  />
                <p>Vendas</p>
            </Item>
            <Item as={Link} active={useLocation().pathname === '/cluster-cliente'} to="/cluster-cliente" >
                <IconChildren icon={peopleIcon} />
                <p>Cliente</p>
            </Item>
            <Item as={Link} active={useLocation().pathname === '/config'} to="/config" >
                <IconChildren icon={gearFill} />
                <p>Configurações</p>
            </Item>
            </div>
            <Item as={Link}>
                <IconChildren icon={accountLogout} />
                <p onClick={logoff}>Sair</p>
            </Item>
        </Container>
    )
}