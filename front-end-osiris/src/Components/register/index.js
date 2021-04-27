import { React, useState } from 'react';
import ReactInputMask from "react-input-mask";
import leftblob from '../../Images/left-blob.svg'
import rightblob from '../../Images/right-blob.svg'
import { Button, Container, ContainerForm, Form , RadioBox, RadioButton } from './style';
import { Link } from 'react-router-dom';


export default () => {
    const [next, setNext]= useState(false);
    const [cnpj, setCNPJ]= useState('')

    function changeForm(e){
        setNext(e.target.id != 'first')
    }

    return (
        <Container>
            <img src={leftblob} alt="React Logo" />
            <Form>
                <ContainerForm next={!next}>
                    <h2>Cadastro</h2>
                    <div>
                        <label>CNPJ</label>
                        <ReactInputMask mask="99.999.999/9999-99" id="cnpj" value={cnpj}
                        onChange={(e) => setCNPJ(e.target.value)} 
                        placeholder='00.000.000/0000-00' />
                    </div>
                    <div>
                        <label>Usuário</label>
                        <input/>
                    </div>
                    <div>
                        <label>Nome Completo</label>
                        <input/>
                    </div>
                    <Link to="/">Voltar para tela de Login</Link>
                </ContainerForm>
                <ContainerForm   next={next}>
                    <h2>Cadastro</h2>
                    <div>
                        <label>Nome do Comércio</label>
                        <input/>
                    </div>
                    <div>
                        <label>Senha</label>
                        <input type='password'/>
                    </div>
                    <div>
                        <label>Confirmar senha</label>
                        <input type='password'/>
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