import {React, useState} from 'react';
import MaskedInput from '../MaskedInput'
import leftblob from '../../Images/left-blob.svg'
import rightblob from '../../Images/right-blob.svg'
import { Container, Form, ContainerForm, Button } from './style';
import {Link,useHistory} from 'react-router-dom';



export default () => {
    const [cnpj, setCNPJ] = useState('');
    const [senha, setSenha] = useState('');
    const history = useHistory()

    function onSubmit(e){
        e.preventDefault()
        if(cnpj == '' && senha == '1234'){
            return history.push('/home')
        }
    }


    return (
    <Container>
        <img src={leftblob} alt="Blob a esquerda" />
            <Form onSubmit={onSubmit}>
            <ContainerForm>
                    <h2>Login</h2>
                    <div>
                        <label for='cnpj'>CNPJ/Usuário</label>
                        <MaskedInput mask="99.999.999/9999-99" id="cnpj" value={cnpj}
                        onChange={(e) => setCNPJ(e.target.value)} />
                    </div>
                    <div>
                        <label for='senha'>Senha</label>
                        <input
                        value={senha}
                        onChange={(e) => setSenha(e.target.value)}
                        id='senha' type='password'/>
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