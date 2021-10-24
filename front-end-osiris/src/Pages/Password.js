import React, { useEffect, useState } from 'react';
import { ButtonForm, Button } from '../Components/Button'
import api from '../api';
import { useParams } from 'react-router-dom/cjs/react-router-dom.min';
import { ToastContainerTop } from '../Components/Toast';
import { toast } from 'react-toastify';
import { useHistory } from 'react-router-dom';

const Component = () => {

    const history = useHistory();

    const { token } = useParams();
    const [displayPassword, setDisplayPassword] = useState({ display: "none" });
    const [displayEmail, setDisplayEmail] = useState({ display: "block" });
    const [displayEmailSent, setDisplayEmailSent] = useState({
        display: "none",
        marginTop: "6vh",
        fontSize: "18px"
    });
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState({
        senha: "",
        confirmaSenha: ""
    });

    useEffect(() => {
        if (token !== undefined) {
            setDisplayPassword({ display: "block" });
            setDisplayEmail({ display: "none" });
            const newDisplaySent = { ...displayEmailSent };
            newDisplaySent.display = "none";
            setDisplayEmailSent(newDisplaySent);
        }
    }, [token, displayEmailSent]);

    const displayNone = {
        display: "none"
    };

    function valorEmail(e) {
        setEmail(e.target.value);
    }

    function enviarEmail(e) {
        e.preventDefault();
        api.get(`/usuarios/recuperar-senha/solicitacao/${email}`)
            .then(res => {
                console.log(res);
                setDisplayEmail({ display: "none" });
                const newDisplaySent = { ...displayEmailSent };
                newDisplaySent.display = "block";
                setDisplayEmailSent(newDisplaySent);
            }).catch(err => {
                toast.error(err.response.data.message);
                console.log(err.response);
            });
    }

    function handleSenha(e) {
        const newPassword = { ...password };
        newPassword[e.target.id] = e.target.value;
        setPassword(newPassword);
    }

    function enviarSenha(e) {
        e.preventDefault();
        if (password.senha !== password.confirmaSenha) {
            toast.error("As senhas não conferem.");
            return null;
        }
        api.post(`/usuarios/recuperar-senha/${token}`, { senha: password.senha })
            .then(res => {
                console.log(res);
                history.push("/login/true");
            }).catch(err => {
                console.log(err);
                if (err.response !== undefined) {
                    toast.error(err.response.data.message);
                } else {
                    toast.error("Desculpe tivemos um erro. Tente mais tarde");
                }
            });

    }

    return (
        <>
            <ToastContainerTop />
            <div className="w-100 body-password d-flex justify-content-center">
                <div className="card-password">
                    <div className="container d-flex flex-column justify-content-center">
                        <h1 className="mt-5 fonte-25">Esqueci minha senha</h1>

                        <label style={{ marginTop: "6vh", fontSize: "18px", displayEmailSent }} className="label-settings">Um e-mail foi enviado para o endereço digitado</label>
                        <Button style={{ marginTop: "6vh", displayEmailSent }} uri="/login" side="left" type="btn-preenchido">Login</Button>

                        <form style={displayEmail} onSubmit={e => enviarEmail(e)}>
                            <div className="col-settings justify-content-between" style={{ height: "15vh", marginTop: 20 }}>
                                <label className="label-settings">Digite seu e-mail cadastrado para enviarmos um link de troca de senha: </label>
                                <input
                                    className="input-settings"
                                    id="email"
                                    type="text"
                                    onChange={valorEmail} />
                                <label style={displayNone} className="error-valid">E-mail inválido</label>
                                <ButtonForm type="btn-preenchido" style={{ width: "30%" }}>Enviar</ButtonForm>
                            </div>
                        </form>

                        <form style={displayPassword} onSubmit={e => enviarSenha(e)}>
                            <div className="col-settings justify-content-between" style={{ height: "18vh", marginTop: 20 }}>
                                <label className="label-settings">Nova senha: </label>
                                <input
                                    className="input-settings"
                                    id="senha"
                                    type="password"
                                    onChange={handleSenha} />

                                <label className="label-settings">Confirme sua senha: </label>
                                <input
                                    className="input-settings"
                                    id="confirmaSenha"
                                    type="password"
                                    onChange={handleSenha} />
                                <ButtonForm type="btn-preenchido" style={{ width: "30%", marginTop: "2vh" }}>Enviar</ButtonForm>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </>
    );
};
export default Component