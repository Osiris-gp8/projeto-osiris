import {React, useEffect, useState, useMemo} from 'react'
import { useHistory } from 'react-router-dom'
import MenuNovo from '../Components/MenuNovo/MenuNovo'
import Input from '../Components/Input/Input'
import Icon from '@iconify/react'
import pencilIcon from '@iconify-icons/akar-icons/pencil';
import {Button, ButtonForm} from '../Components/Button'
import api from '../api';
import { ToastContainerTop } from '../Components/Toast';
import { toast } from 'react-toastify';
import { getMetas } from '../services/MetaService';

export default () =>{ 

    const history = useHistory();

    const [user, setUser] = useState({});
    const [ecommerce, setEcommerce] = useState({});
    const [meta, setMeta] = useState([{}, {}]);
    const [metaParaUpdate, setMetaParaUpdate] = useState({});

    const header = useMemo(() => {
        return {"Authorization": `${sessionStorage.getItem("tipo")} ${sessionStorage.getItem("token")}`}
    }, []);

    const filtros = useMemo(() => {
        return {
            dataInicio: sessionStorage.getItem("dataInicio"),
            dataFinal: sessionStorage.getItem("dataFinal")
        }
    });
    

    const [editUser, setEditUser] = useState({
        nome: true,
        login: true,
        button: "none"
    });

    function editUserClick(){
        setEditUser({
            nome: false,
            login: false,
            button: "block"
        })
    }

    const [editEcommerce, setEditEcommerce] = useState({
        nome: true,
        cnpj: true,
        button: "none"
    });

    function editEcommerceClick(){
        setEditEcommerce({
            nome: false,
            cnpj: false,
            button: "block"
        })
    }

    const [editMeta, setEditMeta] = useState({
        valor: true,
        tipo: true,
        button: "none"
    });

    function editMetaClick(){
        setEditMeta({
            valor: false,
            tipo: false,
            button: "block"
        })
    }    

    useEffect(() =>{
        if(!sessionStorage.getItem("token")){
            return history.push('/login');
        }

        setUser(JSON.parse(sessionStorage.getItem("usuario")));
        setEcommerce(JSON.parse(sessionStorage.getItem("usuario")).ecommerce);
        getMetas(header, filtros)
            .then(res => {
                setMeta(res);
                setMetaParaUpdate({...res[0]});
                console.log(metaParaUpdate);
            });
    }, []);

    function handleUser(e){
        const newUser = {...user};
        newUser[e.target.id] = e.target.value;
        setUser(newUser);
    }

    function handleEcommerce(e){
        const newEcommerce = {...ecommerce};
        newEcommerce[e.target.id] = e.target.value;
        setEcommerce(newEcommerce);
    }
 
    function handleMeta(e){
        const newMeta = {...metaParaUpdate};
        newMeta[e.target.id] = e.target.value == "" ? 0 : parseInt(e.target.value);
        setMetaParaUpdate(newMeta);
    }

    function trocarMeta(e){
        setMetaParaUpdate(meta[e.target.value]);
        console.log(metaParaUpdate);
    }

    function atualizarUser(e){
        e.preventDefault();
        api.post(`/usuarios/${JSON.parse(sessionStorage.getItem("usuario")).idUsuario}`, user, {headers: header })
            .then(res => {
                toast.success("Usuário alterado com sucesso");
                sessionStorage.setItem("usuario", JSON.stringify(user));
            }).catch(err => {
                console.log(err);
                toast.error("Desculpe tivemos um erro. Tente mais tarde.")
            })
    }

    function atualizarEcommerce(e){
        e.preventDefault();
        api.post(`/ecommerces/${JSON.parse(sessionStorage.getItem("usuario")).ecommerce.idEcommerce}`, ecommerce, {headers: header} )
            .then(res => {
                toast.success("Ecommerce alterado com sucesso");
                user.ecommerce = ecommerce;
                sessionStorage.setItem("usuario", JSON.stringify(user));
            }).catch(err => {
                console.log(err);
                toast.error("Desculpe tivemos um erro. Tente mais tarde.")
            })
    }

    function atualizarMeta(e){
        e.preventDefault();
        const newMeta = {...metaParaUpdate};
        newMeta["dataInicio"] = newMeta["dataInicio"].replace(" ", "T");
        newMeta["dataFim"] = newMeta["dataFim"].replace(" ", "T");
        api.post(`/metas/${newMeta.idMeta}`, newMeta, {headers: header} )
            .then(res => {
                console.log(res)
                toast.success("Meta alterada com sucesso");
            }).catch(err => {
                console.log(err);
                toast.error("Desculpe tivemos um erro. Tente mais tarde.")
            })
    }
    return (
        <>
            <MenuNovo/>
            <div className="body-config">
                <ToastContainerTop/>
                <div className="container">
                    <h1>Configuração</h1>
                    <div className="user-config">
                        <div className="configs-head">
                            <h2>Usuário</h2>
                            <span onClick={editUserClick}><Icon icon={pencilIcon}/> Editar</span>
                        </div>
                        <form onSubmit={(e) => atualizarUser(e)}>
                            <div className="row-configs">

                                <div style={{width: "28%"}} className="col-settings">
                                    <label className="label-settings">Nome:</label>
                                    <input 
                                        className="input-settings"
                                        id="nomeCompleto"
                                        type="text"
                                        value={user.nomeCompleto}
                                        defaultValue={user.nomeCompleto}
                                        disabled={editUser.nome}
                                        onChange={handleUser}
                                    />
                                </div>

                                <div style={{width: "28%"}} className="col-settings">
                                    <label className="label-settings">Login:</label>
                                    <input 
                                        className="input-settings"
                                        id="loginUsuario"
                                        type="text"
                                        value={user.loginUsuario}
                                        defaultValue={user.loginUsuario}
                                        disabled={editUser.login}
                                        onChange={handleUser}
                                    />
                                </div>

                                <ButtonForm
                                    type="btn-preenchido"
                                    style={{width: "15%", 
                                            marginTop: "20px",
                                            display: editUser.button
                                        }}>
                                    Editar
                                </ButtonForm>
                            </div>
                        </form>
                        
                    </div>

                    <div className="user-config">
                        <div className="configs-head">
                            <h2>Ecommerce</h2>
                            <span onClick={editEcommerceClick}><Icon icon={pencilIcon}/> Editar</span>
                        </div>
                        <form onSubmit={(e) => atualizarEcommerce(e)}>
                            <div className="row-configs">
                                
                                <div style={{width: "28%"}} className="col-settings">
                                    <label className="label-settings">Nome Ecommerce:</label>
                                    <input 
                                        className="input-settings"
                                        id="nome"
                                        type="text"
                                        value={ecommerce.nome}
                                        defaultValue={ecommerce.nome}
                                        disabled={editEcommerce.nome}
                                        onChange={handleEcommerce}
                                    />
                                </div>

                                <div style={{width: "28%"}} className="col-settings">
                                    <label className="label-settings">Cnpj:</label>
                                    <input 
                                        className="input-settings"
                                        id="cnpj"
                                        type="text"
                                        value={ecommerce.cnpj}
                                        defaultValue={ecommerce.cnpj}
                                        disabled={editEcommerce.cnpj}
                                        onChange={handleEcommerce}
                                    />
                                </div>

                                <ButtonForm
                                    type="btn-preenchido"
                                    style={{width: "15%", 
                                            marginTop: "20px",
                                            display: editEcommerce.button
                                        }}>
                                    Editar
                                </ButtonForm>
                            </div>
                        </form>
                        
                    </div>

                    <div className="user-config">
                        <div className="configs-head">
                            <h2>Metas</h2>
                            <span onClick={editMetaClick}><Icon icon={pencilIcon}/> Editar</span>
                        </div>
                        <form onSubmit={(e) => atualizarMeta(e)}>
                            <div className="row-configs">

                                <div style={{width: "28%"}} className="col-settings">
                                    <label className="label-settings">Valor</label>
                                    <input 
                                        className="input-settings"
                                        id="valor"
                                        value="lalal"
                                        defaultValue="lalal"
                                        value={Math.floor(metaParaUpdate.valor)}
                                        defaultValue={Math.floor(metaParaUpdate?.valor)}
                                        disabled={editMeta.valor}
                                        onChange={handleMeta}
                                    />
                                </div>

                                <div style={{width: "28%"}} className="col-settings">
                                    <label className="label-settings">Tipo: </label>
                                    <select onChange={trocarMeta} className="input-settings" disabled={editMeta.tipo}>
                                        <option value={0}>Vendas</option>
                                        <option value={1}>Clientes</option>
                                        <option value={2}>Acessos</option>
                                    </select>
                                </div>

                                <ButtonForm
                                    type="btn-preenchido"
                                    style={{width: "15%", 
                                            marginTop: "20px",
                                            display: editMeta.button
                                        }}>
                                    Editar
                                </ButtonForm>
                            </div>
                        </form>
                        
                    </div>

                    <Button
                        uri="/addCollaborator"
                        type="btn-preenchido"
                        style={{width: "30%"}}
                    >
                        Adicionar Colaborador
                    </Button>


                </div>
            </div>
            
        </>

    );
}