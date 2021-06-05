import React from 'react';
import Item from './Item/Item';
import bxHome from '@iconify-icons/bx/bx-home';
import lineChartOutlined from '@iconify-icons/ant-design/line-chart-outlined';
import arrowUpCircleFill from '@iconify-icons/bi/arrow-up-circle-fill';
import fileIcon from '@iconify-icons/akar-icons/file';
import gearIcon from '@iconify-icons/akar-icons/gear';
import signOut from '@iconify-icons/akar-icons/sign-out';

function MenuNovo(){
    return(
        <div className="menu">
            <span>Bem vindo, <span className="nome-user">Patrick</span></span>
            <span>Veja as Informações da sua loja</span>

            <div className="itens-menu">
                <Item destino="/" icon={bxHome} aba="Home"/>
                <Item destino="/" icon={lineChartOutlined} aba="Vendas"/>
                <Item destino="/" icon={arrowUpCircleFill} aba="Metas"/>
                <Item destino="/" icon={fileIcon} aba="Arquivos"/>
                <Item destino="/" icon={gearIcon} aba="Configuração"/>
                <Item id="item-sair" destino="/" icon={signOut} aba="Sair"/>
            </div>
        </div>
    );
}

export default MenuNovo;