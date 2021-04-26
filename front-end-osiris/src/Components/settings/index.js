import {Container, Title, BoxInput, Button} from './style'
import bxHelpCircle from '@iconify-icons/bx/bx-help-circle';
import {InlineIcon } from '@iconify/react';

function BoxForm(props){
    return(
        <BoxInput>
            <span>{props.titulo}</span>
            {props.children}
        </BoxInput>
    )
}

export default () =>{
    return(
    <Container>
        <Title>Configuração</Title>
        <BoxForm titulo="Geral">
            <label>Nome:</label>
            <label>Email:</label>
            <label>Loja:</label>
            <input type="text"></input>
            <input type="text"></input>
            <input type="text"></input>
        </BoxForm>
        <BoxForm  titulo="Segurança">
            <label>senha:</label>
            <input type="password"></input>
            <a href="#">Trocar senha</a>
        </BoxForm>
        <BoxForm  titulo="Conexão">
            <label>URL:</label>
            <input type="password"></input>
            <a href="#"><InlineIcon icon={bxHelpCircle}/>
             Obter ajuda</a>
        </BoxForm>
        <Button>
            Salvar
        </Button>
    </Container>
    )
}