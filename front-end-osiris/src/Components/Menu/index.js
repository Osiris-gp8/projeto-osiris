import { Container, Contrast, Item, WelcomeText } from './style'
// npm install --save-dev @iconify/react @iconify-icons/bx
import { Icon } from '@iconify/react';
import bxHome from '@iconify-icons/bx/bx-home';
import lineChartOutlined from '@iconify-icons/ant-design/line-chart-outlined';
import peopleIcon from '@iconify-icons/bi/people';
import gearFill from '@iconify-icons/bi/gear-fill';
// npm install --save-dev @iconify/react @iconify-icons/cil
import accountLogout from '@iconify-icons/cil/account-logout';






export default () =>{

    return(
        <Container>
            <WelcomeText children={Text}>
             Bem vindo, <Contrast children="Patrick"/> 
             <br/>Veja as Informações da sua loja
            </WelcomeText>
            <div>
            <Item first>
                <Icon icon={bxHome} />
                <p>Home</p>
            </Item>
            <Item>
                <Icon icon={lineChartOutlined} />
                <p>Vendas</p>
            </Item>
            <Item>
                <Icon icon={peopleIcon} />
                <p>Cliente</p>
            </Item>
            <Item>
                <Icon icon={gearFill} />
                <p>Configurações</p>
            </Item>
            </div>
            <Item>
                <Icon icon={accountLogout} />
                <p>Sair</p>
            </Item>
        </Container>
    )
}