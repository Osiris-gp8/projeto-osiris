import {React, useEffect, useState} from 'react';
// import MaskedInput from '../MaskedInput'
import leftblob from '../../Images/left-blob.svg'
import rightblob from '../../Images/right-blob.svg'
import { Container, Form, ContainerForm, Button } from './style';
import {Link,useHistory, useParams} from 'react-router-dom';
import { ToastContainerTop } from '../Toast';
import { toast } from 'react-toastify';

import api from '../../api';

const Component = () => {

    const history = useHistory();
    const setSenha = useParams();

    useEffect(() =>{
        if(sessionStorage.getItem("token")){
            return history.push('/home');
        }

        if(setSenha.setSenha){
            toast.success("Senha trocada com sucesso.")
        }
    });

    const [usuarioData, setUsuarioData] = useState({
        "login": "",
        "senha": ""
    });

    function handle(e){
        const user = {...usuarioData};
        user[e.target.id] = e.target.value;
        setUsuarioData(user);
    }

    function onSubmit(e){
        e.preventDefault()
        if(usuarioData.login === '' || usuarioData.senha === ''){
            toast.error("Campo de e-mail ou senha está vazio")
            return;
        }

        api.post("/auth", {
            "login": usuarioData.login,
            "senha": usuarioData.senha
        }).then( response => {
            sessionStorage.setItem("token", response.data.token);
            sessionStorage.setItem("tipo", response.data.tipo);
            sessionStorage.setItem("usuario", JSON.stringify(response.data.usuario));
            history.push('/home');
        }).catch( error => {
            if(error.response !== undefined){
                if(error.response.status === 400){
                    toast.error("Usuário ou senha inválidos.")
                }
            }else{
                toast.error("Desculpe tivemos um erro. Tente mais tarde.")
            }
        })
    }


    return (
    <Container>
        
        <img src={leftblob} alt="Blob a esquerda" />
            <Form onSubmit={onSubmit}>
                <ContainerForm>
                    <ToastContainerTop/>
                    <label></label>
                    <h2>Login</h2>
                    <div>
                        <label for='cnpj'>Login usuário</label>
                        <input id="login" type="text" onBlur={handle}/>
                    </div>
                    <div>
                        <label for='senha'>Senha</label>
                        <input
                        id='senha' type='password' onBlur={handle}/>
                    </div>
                    <div>
                        <Link to="/password">Esqueceu sua senha?</Link>
                        <Link to="/register">Não possui cadastro?</Link>
                    </div>
                </ContainerForm>
                <Button>Logar</Button>
            </Form>
        <img src={rightblob} alt="Blov a direita" />
    </Container>
    );
};

export default Component;