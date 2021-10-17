import React, { useEffect, useState } from 'react';
import {Item, ItemSair} from './Item/Item';
import bxHome from '@iconify-icons/bx/bx-home';
import lineChartOutlined from '@iconify-icons/ant-design/line-chart-outlined';
import arrowUpCircleFill from '@iconify-icons/bi/arrow-up-circle-fill';
import fileIcon from '@iconify-icons/akar-icons/file';
import personIcon from '@iconify-icons/akar-icons/person';
import signOut from '@iconify-icons/akar-icons/sign-out';
import peopleGroup from '@iconify-icons/akar-icons/people-group';
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';
import api from '../../api';

function MenuNovo(){
    const history = useHistory();
    const [nomeUser, setNomeUser] = useState("");

    useEffect(() => {
        if(sessionStorage.getItem("usuario")){
            setNomeUser(JSON.parse(sessionStorage.getItem("usuario")).nomeCompleto);
        }
    }, []);

    function logoff(){

        sessionStorage.removeItem("token");
        sessionStorage.removeItem("tipo");
        return history.push("/login");
    }
    return(
        <div className="menu">
            <span>Bem vindo, <span className="nome-user">{nomeUser}</span></span>
            <span>Veja as Informações da sua loja</span>

            <div className="itens-menu">
                <Item destino="/home" icon={bxHome} aba="Home"/>
                {/* <Item destino="/sales" icon={lineChartOutlined} aba="Vendas"/> */}
                {/* <Item destino="/" icon={arrowUpCircleFill} aba="Metas"/> */}
                <Item destino="/upload" icon={fileIcon} aba="Arquivos"/>
                <Item destino="/config" icon={personIcon} aba="Perfil"/>
                <Item destino="/access" icon={peopleGroup} aba="Acessos"/>
                <ItemSair function={logoff} id="item-sair" icon={signOut} aba="Sair"/>
            </div>
        </div>
    );
}

export default MenuNovo;