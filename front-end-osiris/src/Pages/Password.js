import React from 'react';
import Input from '../Components/Input/Input'
import { ButtonForm } from '../Components/Button'

export default () => {

    const displayNone = {
        display: "none"
    }

    return(
        <>
        <div className="w-100 body-password d-flex justify-content-center">
            <div className="card-password">
                <div className="container d-flex flex-column justify-content-center">
                    <h1 className="mt-5 fonte-25">Esqueci Minha senha</h1>

                    <label style={{marginTop: "5vh"}, displayNone} className="label-settings">Um e-mail foi enviado para o endereço digitado</label>
                    
                    <form style={displayNone}>
                        <div className="col-settings justify-content-between" style={{height: "15vh", marginTop: 20}}>
                            <label className="label-settings">Digite seu e-mail cadastrado para enviarmos um link de troca de senha: </label>
                            <input 
                                className="input-settings"
                                id="email"
                                type="text"
                            />
                            <label style={{display: 'none'}} className="error-valid">E-mail inválido</label>
                            <ButtonForm type="btn-preenchido" style={{width: "30%"}}>Enviar</ButtonForm>
                        </div>
                    </form>

                    <form>
                        <div className="col-settings justify-content-between" style={{height: "18vh", marginTop: 20}}>
                            <label className="label-settings">Nova senha: </label>
                            <input 
                                className="input-settings"
                                id="newPassword"
                                type="text"
                            />

                            <label className="label-settings">Confirme sua senha: </label>
                            <input 
                                className="input-settings"
                                id="newPasswordConfirm"
                                type="text"
                            />
                            <label style={{display: 'none'}} className="error-valid">E-mail inválido</label>
                            <ButtonForm type="btn-preenchido" style={{width: "30%", marginTop: "2vh"}}>Enviar</ButtonForm>
                        </div>
                    </form>
                </div>
            </div>
        </div>
            
        </>
    )
}