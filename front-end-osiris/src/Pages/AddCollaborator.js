import React from 'react';
import Input from '../Components/Input/Input'
import { ButtonForm } from '../Components/Button'
import MenuNovo from '../Components/MenuNovo/MenuNovo';

export default () => {
    return (

        <>
            <MenuNovo/>
            <div className="body-config">
                <div className="container">
                    <h1>Adicionar Colaborador</h1>
                    <div className="user-config">
                        <form>
                            <div className="row-configs">

                                <div style={{width: "35%"}} className="col-settings">
                                    <label className="label-settings">Nome:</label>
                                    <input 
                                        className="input-settings"
                                        id="nomeCompleto"
                                        type="text"
                                    />
                                </div>

                                <div style={{width: "35%"}} className="col-settings">
                                    <label className="label-settings">Login:</label>
                                    <input 
                                        className="input-settings"
                                        id="loginUsuario"
                                        type="text"
                                    />
                                </div>

                            </div>

                            <div className="row-configs" style={{marginTop: "3vh"}}>

                                <div style={{width: "35%"}} className="col-settings">
                                    <label className="label-settings">Email:</label>
                                    <input 
                                        className="input-settings"
                                        id="email"
                                        type="text"
                                    />
                                </div>

                            </div>

                            <div className="row-configs" style={{marginTop: "3vh"}}>
                                <ButtonForm
                                    type="btn-preenchido"
                                    style={{width: "15%", 
                                            marginTop: "20px"
                                        }}>
                                    Adicionar
                                </ButtonForm>
                            </div>
                        </form>
                        
                    </div>

                </div>
            </div>
        </>
    );
}