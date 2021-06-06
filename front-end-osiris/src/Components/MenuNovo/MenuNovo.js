import React from 'react';
import Item from './Item/Item';
import bxHome from '@iconify-icons/bx/bx-home';
import lineChartOutlined from '@iconify-icons/ant-design/line-chart-outlined';
import arrowUpCircleFill from '@iconify-icons/bi/arrow-up-circle-fill';
import fileIcon from '@iconify-icons/akar-icons/file';
import gearIcon from '@iconify-icons/akar-icons/gear';
import signOut from '@iconify-icons/akar-icons/sign-out';
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';
import api from '../../api';

function MenuNovo(){
    const history = useHistory();
    const nomeUser = "Patrick";
    // const nomeUser = JSON.parse(sessionStorage.getItem("usuarioLogado")).nomeUsuario;

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
        <div className="menu">
            <span>Bem vindo, <span className="nome-user">{nomeUser}</span></span>
            <span>Veja as Informações da sua loja</span>

            <div className="itens-menu">
                <Item destino="/home" icon={bxHome} aba="Home"/>
                <Item destino="/" icon={lineChartOutlined} aba="Vendas"/>
                <Item destino="/" icon={arrowUpCircleFill} aba="Metas"/>
                <Item destino="/" icon={fileIcon} aba="Arquivos"/>
                <Item destino="/config" icon={gearIcon} aba="Configuração"/>
                <Item onClick={logoff} id="item-sair" destino="/" icon={signOut} aba="Sair"/>
            </div>
        </div>
    );
}

export default MenuNovo;