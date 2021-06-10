import {React, useEffect, useState} from 'react';
// import MaskedInput from '../MaskedInput'
import leftblob from '../../Images/left-blob.svg'
import rightblob from '../../Images/right-blob.svg'
import { Container, Form, ContainerForm, Button } from './style';
import {Link,useHistory} from 'react-router-dom';

import api from '../../api';

export default () => {

    const history = useHistory();

    useEffect(() =>{
        if(sessionStorage.getItem("token")){
            return history.push('/home');
        }
    }, []);

    const [usuarioData, setUsuarioData] = useState({
        "login": "",
        "senha": ""
    });

    function handle(e){
        const user = {...usuarioData};
        user[e.target.id] = e.target.value;
        setUsuarioData(user);
        console.log(user);
    }

    function onSubmit(e){
        e.preventDefault()
        if(usuarioData.login == '' || usuarioData.senha == ''){
            {/* 
                TODO CRIAR COMPONENTE DE RETORNO DE ERRO
            */}
            return;
        }

        api.post("/auth", {
            "login": usuarioData.login,
            "senha": usuarioData.senha
        }).then( async response => {
            sessionStorage.setItem("token", response.data.token);
            sessionStorage.setItem("tipo", response.data.tipo);
            history.push('/home');
        }).catch( error => {
            {/* 
                TODO CRIAR COMPONENTE DE RETORNO DE ERRO
            */}
            console.log(error);
        })
    }


    return (
    <Container>
        <img src={leftblob} alt="Blob a esquerda" />
            <Form onSubmit={onSubmit}>
            <ContainerForm>
                    <label></label>
                    <h2>Login</h2>
                    <div>
                        <label for='cnpj'>Login usuário</label>
                        <input id="login" type="text" onChange={handle}/>
                    </div>
                    <div>
                        <label for='senha'>Senha</label>
                        <input
                        id='senha' type='password' onChange={handle}/>
                    </div>
                    <div>
                        <Link>Esqueceu sua senha?</Link>
                        <Link to="/register">Não possui cadastro?</Link>
                    </div>
                </ContainerForm>
                <Button>Logar</Button>
            </Form>
        <img src={rightblob} alt="Blov a direita" />
    </Container>
    );
};