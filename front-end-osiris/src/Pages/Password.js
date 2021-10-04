import React, { useEffect, useState } from 'react';
import Input from '../Components/Input/Input'
import { ButtonForm } from '../Components/Button'
import api from '../api';
import { useParams } from 'react-router-dom/cjs/react-router-dom.min';

export default () => {

    const {token} = useParams();
    const [displayPassword, setDisplayPassword] = useState({display: "none"})
    const [displayEmail, setDisplayEmail] = useState({display: "block"})
    const [displayEmailSent, setDisplayEmailSent] = useState({display: "none"})
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState({
        senha: "",
        confirmaSenha: ""
    });

    useEffect(() => {
        if(token != undefined){
            setDisplayPassword({display: "block"});
            setDisplayEmail({display: "none"})
            setDisplayEmailSent({display: "none"})
        }
    })

    const displayNone = {
        display: "none"
    }

    function valorEmail(e){
        setEmail(e.target.value);
    }

    function enviarEmail(e){
        e.preventDefault();
        api.get(`/usuarios/recuperar-senha/solicitacao/${email}`)
            .then(res => {
                console.log(res)
                setDisplayEmail({display: "none"})
                setDisplayEmailSent({display: "block"})
            }).catch(err => {
                console.log(err.response)
            })
    }

    function handleSenha(e) {
        const newPassword = {...password}
        newPassword[e.target.id] = e.target.value
        setPassword(newPassword);
    }

    function enviarSenha(e){
        e.preventDefault();
        if(password.senha !== password.confirmaSenha){
            console.log("senha errada")
            return null;
        }
        api.post(`/usuarios/recuperar-senha/${token}`, {senha: password.senha})
            .then(res => {
                console.log(res)
            }).catch(err => {
                console.log(err.response)
            })

    }

    return(
        <>
        <div className="w-100 body-password d-flex justify-content-center">
            <div className="card-password">
                <div className="container d-flex flex-column justify-content-center">
                    <h1 className="mt-5 fonte-25">Esqueci Minha senha</h1>

                    <label style={{marginTop: "5vh"}, displayEmailSent} className="label-settings">Um e-mail foi enviado para o endereço digitado</label>
                    
                    <form style={displayEmail} onSubmit={e => enviarEmail(e)}>
                        <div className="col-settings justify-content-between" style={{height: "15vh", marginTop: 20}}>
                            <label className="label-settings">Digite seu e-mail cadastrado para enviarmos um link de troca de senha: </label>
                            <input 
                                className="input-settings"
                                id="email"
                                type="text"
                                onChange={valorEmail}
                            />
                            <label style={displayNone} className="error-valid">E-mail inválido</label>
                            <ButtonForm type="btn-preenchido" style={{width: "30%"}}>Enviar</ButtonForm>
                        </div>
                    </form>

                    <form style={displayPassword} onSubmit={e => enviarSenha(e)}>
                        <div className="col-settings justify-content-between" style={{height: "18vh", marginTop: 20}}>
                            <label className="label-settings">Nova senha: </label>
                            <input 
                                className="input-settings"
                                id="senha"
                                type="password"
                                onChange={handleSenha}
                            />

                            <label className="label-settings">Confirme sua senha: </label>
                            <input 
                                className="input-settings"
                                id="confirmaSenha"
                                type="password"
                                onChange={handleSenha}
                            />
                            <ButtonForm type="btn-preenchido" style={{width: "30%", marginTop: "2vh"}}>Enviar</ButtonForm>
                        </div>
                    </form>
                </div>
            </div>
        </div>
            
        </>
    )
}