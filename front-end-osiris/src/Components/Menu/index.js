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

    function logoff(){

        let uri = "";
        if(sessionStorage.getItem("usuarioLogado")){
            uri = "/usuarios/logoff?idUsuario=" + JSON.parse(sessionStorage.getItem("usuarioLogado")).idUsuario;
        }

        api.get(uri).then( response => {
            sessionStorage.removeItem("usuarioLogado");
            return history.push("/login");
        }).catch(error => {
            console.log(error);
        })
    }

    return(
        <Container>
            <WelcomeText children={Text}>
             Bem vindo, <Contrast>Patrick</Contrast> 
             <br/>Veja as Informações da sua loja
            </WelcomeText>
            <div>
            <Link>
                <Item first active={useLocation().pathname === '/home'} as={Link} to="/home" >
                    <IconChildren icon={bxHome} />
                    <p>Home</p>
                </Item>
            </Link>
            <Link to="/relation"> 
                <Item active={useLocation().pathname === '/relation'} to="/relation" >
                    <IconChildren icon={lineChartOutlined}  />
                    <p>Vendas</p>
                </Item>
            </Link>
            
            <Link to="/cluster-cliente">
                <Item active={useLocation().pathname === '/cluster-cliente'} >
                    <IconChildren icon={peopleIcon} />
                    <p>Cliente</p>
                </Item>
            </Link>
            <Link to="/config">
                <Item active={useLocation().pathname === '/config'} >
                    <IconChildren icon={gearFill} />
                    <p>Configurações</p>
                </Item>
            </Link>
            </div>
            <Item as={Link} onClick={logoff}>
                <IconChildren icon={accountLogout} />
                <p>Sair</p>
            </Item>
        </Container>
    )
}