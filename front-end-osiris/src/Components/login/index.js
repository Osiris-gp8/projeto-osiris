import {React, useState} from 'react';
// import MaskedInput from '../MaskedInput'
import leftblob from '../../Images/left-blob.svg'
import rightblob from '../../Images/right-blob.svg'
import { Container, Form, ContainerForm, Button } from './style';
import {Link,useHistory} from 'react-router-dom';

import api from '../../api';

export default () => {

    const [usuarioData, setUsuarioData] = useState({
        "login": "",
        "senha": ""
    });
    const history = useHistory();

    function handle(e){
        const user = {...usuarioData};
        user[e.target.id] = e.target.value;
        setUsuarioData(user);
        console.log(user);
    }

    function onSubmit(e){
        e.preventDefault()
        if(usuarioData.login == '' || usuarioData.senha == ''){
            return history.push('/');
        }

        api.post("/usuarios/login", {
            "login": usuarioData.login,
            "senha": usuarioData.senha
        }).then( response => {
            console.log("foi", response);
            localStorage.setItem("idUsuario", response.data.idUsuario);
            history.push('/home');
        }).catch( error => {
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
                        <label for='cnpj'>CNPJ/Usuário</label>
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