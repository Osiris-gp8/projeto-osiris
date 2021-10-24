import { React, useState } from 'react';
import MaskedInput from '../MaskedInput'
import leftblob from '../../Images/left-blob.svg'
import rightblob from '../../Images/right-blob.svg'
import { Button, Container, ContainerForm, Form , RadioBox, RadioButton } from './style';
import { useHistory } from 'react-router-dom';
import { Link } from 'react-router-dom';
import api from '../../api'

const Component = () => {
    const history = useHistory();

    const [next, setNext]= useState(false);
    const [cnpj, setCNPJ]= useState('');
    const [nomeEcommerce, setNomeEcommerce]= useState('');
    const [id, setId]= useState('');

    const [usuarioData, setUsuarioData]= useState({
        loginUsuario: "",
        senha: "",
        nomeCompleto: "",
        ecommerce: {
            idEcommerce: id
        }
    });

    function handle(e) {
        const newUsuario = { ...usuarioData }
        newUsuario[e.target.id] = e.target.value;
        setUsuarioData(newUsuario);
        console.log(newUsuario);
    }

    function enviar(e) {
        e.preventDefault();
        let usuarioPost = {
            loginUsuario: usuarioData.loginUsuario,
            senha: usuarioData.senha,
            nomeCompleto: usuarioData.nomeCompleto,
            ecommerce: {
                idEcommerce: id
            }
        }
        console.log(usuarioPost)
        api.post("/usuarios", usuarioPost).then((resposta) => {
            console.log("teste")
            console.log("post ok", resposta);
            history.push("/login");
        });
    }

    function receberEcommerce(cnpj, nomeEcommerce) {    
        console.log(cnpj);
        api.get(`/ecommerces/id`, { params: { cnpj: cnpj, nomeEcommerce: nomeEcommerce}}).then((resposta) => {
            console.log(resposta);
            setId(resposta.data);
            console.log(id);
            return resposta.data;
        }).catch((error) => { 
            console.log(error);
            setNext(false); 
            console.log(error)    
        });
    }

    function changeForm(e){
        receberEcommerce(cnpj, nomeEcommerce)
        console.log(next);
        setNext(e.target.id !== 'first')
        console.log(next);
    }

    return (
        <Container>
            <img src={leftblob} alt="React Logo" />
            <Form onSubmit={(e) => enviar(e)} >
                <ContainerForm next={!next}>
                    <h2>Cadastro</h2>
                    <div className="col-settings">
                        <label 
                            className="label-settings">Nome do Comércio:</label>
                        <input 
                            mask= "99.999.999/9999-99"
                            className="input-settings" 
                            id="nomeEcommerce"
                            type="text"
                            value={nomeEcommerce}
                            onChange={(e) => setNomeEcommerce(e.target.value)} 
                        />
                    </div>
                    <div className="col-settings">
                        <label 
                            className="label-settings">CNPJ:</label>
                        <MaskedInput 
                            id="cnpj" 
                            value={cnpj}
                            onChange={(e) => setCNPJ(e.target.value)} 
                        />
                    </div>
                    <Link to="/login">Voltar para tela de Login</Link>
                </ContainerForm>
                <ContainerForm   next={next}>
                    <h2>Cadastro</h2>
                    <div className="col-settings">
                        <label 
                            className="label-settings">Nome Completo:</label>
                        <input 
                        className="input-settings" 
                        id="nomeCompleto" 
                        type="text"
                        onChange={(e) => handle(e)} />
                    </div>
                    <div className="col-settings">
                        <label
                            className="label-settings">Email:</label>
                        <input 
                            className="input-settings"
                            id="loginUsuario" 
                            type="text"
                            onChange={(e) => handle(e)} />
                    </div>
                    <div className="col-settings">
                        <label 
                            className="label-settings">Senha:</label>
                        <input 
                            className="input-settings"
                            id="senha" 
                            type='password' 
                            onChange={(e) => handle(e)} />
                    </div>
                    <Link to="/login">Voltar para tela de Login</Link>
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


export default Component;