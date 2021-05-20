import { React, useState } from 'react';
// import MaskedInput from '../MaskedInput'
import leftblob from '../../Images/left-blob.svg'
import rightblob from '../../Images/right-blob.svg'
import { Button, Container, ContainerForm, Form , RadioBox, RadioButton } from './style';
import { useHistory } from 'react-router-dom';
import { Link } from 'react-router-dom';
import api from '../../api'


export default () => {
    const history = useHistory();

    const [next, setNext]= useState(false);
    const [cnpj, setCNPJ]= useState('')

    const [usuarioData, setUsuarioData]= useState({
        "loginUsuario": "",
        "senha": "",
        "ecommerceIdEcommerce": 0,
        "nomeCompleto": ""
    })

    function handle(e) {
        const newUsuario = { ...usuarioData }
        newUsuario[e.target.id] = e.target.value;
        setUsuarioData(newUsuario);
        console.log(newUsuario);
    }

    function enviar(e) {
        e.preventDefault();
        api.post("/usuarios/cadastro", {
            "loginUsuario": usuarioData.loginUsuario,
            "senha": usuarioData.senha,
            "ecommerceIdEcommerce": Number(usuarioData.ecommerceIdEcommerce),
            "nomeCompleto": usuarioData.nomeCompleto
        }).then((resposta) => {
            console.log("post ok", resposta);
            history.push("/login");
        })
    }

    function changeForm(e){
        setNext(e.target.id != 'first')
    }

    return (
        <Container>
            <img src={leftblob} alt="React Logo" />
            <Form onSubmit={(e) => enviar(e)} >
                <ContainerForm next={!next}>
                    <h2>Cadastro</h2>
                    <div>
                        <label>CNPJ</label>
                        {/* <MaskedInput id="cnpj" value={cnpj}
                        onChange={(e) => setCNPJ(e.target.value)} /> */}
                        <input id="cnpj" />
                    </div>
                    <div>
                        <label>Usuário</label>
                        <input id="loginUsuario" onChange={(e) => handle(e)} />
                    </div>
                    <div>
                        <label>Nome Completo</label>
                        <input id="nomeCompleto" onChange={(e) => handle(e)} />
                    </div>
                    <Link to="/">Voltar para tela de Login</Link>
                </ContainerForm>
                <ContainerForm   next={next}>
                    <h2>Cadastro</h2>
                    <div>
                        <label>Nome do Comércio</label>
                        <input id="nome" />
                    </div>
                    <div>
                        <label>Senha</label>
                        <input id="senha" type='password' onChange={(e) => handle(e)} />
                    </div>
                    <div>
                        <label>Id do ecommerce</label>
                        <input id="ecommerceIdEcommerce" type='password' onChange={(e) => handle(e)} />
                    </div>
                    <Link to="/">Voltar para tela de Login</Link>
                </ContainerForm>
                <RadioBox>
                    <input type="radio" name='radio' defaultChecked
                    checked={!next}/>
                    <RadioButton id='first'onClick={changeForm}/>
                    <input type="radio" name='radio' checked={next}/>
                    <RadioButton onClick={changeForm}/>
                </RadioBox>
                <Button>Enviar</Button>
            </Form>
            <img src={rightblob} alt="React Logo" />
        </Container>
    );
};