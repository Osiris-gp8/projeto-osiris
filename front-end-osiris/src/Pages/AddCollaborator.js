import {React, useState, useEffect} from 'react';
import { ButtonForm } from '../Components/Button'
import MenuNovo from '../Components/MenuNovo/MenuNovo';
import { Table } from 'react-bootstrap';
import { useHistory } from 'react-router-dom';
import api from '../api';

export default () => {

    const history = useHistory();
    const [idEcommerce, setIdEcommerce] = useState(JSON.parse(sessionStorage.getItem("usuario")).ecommerce.idEcommerce);
    const [header, setHeader] = useState({});
    const [rows, setRows] = useState([]);

    useEffect( async () => {
        if(!sessionStorage.getItem("token")){
            return history.push('/login');
        }

        setHeader(`Authorization: ${sessionStorage.getItem("tipo")} ${sessionStorage.getItem("token")}`)

        await api.get("/usuarios/ecommerce/"+idEcommerce, {headers: { header }}).then(res => {
            console.log(res)
            setRows(res.data);
        }).catch(err => {
            console.log(err)
        })
    }, [])

    

    const [collaboratorData, setCollaboratorData]= useState({
        idUsuario: "",
        loginUsuario: "",
        senha: "12345",
        nomeCompleto: "",
        ecommerce: {
            idEcommerce: idEcommerce
        }
    })

    function addCollaborator(e){
        const newCollaborator = { ...collaboratorData}
        newCollaborator[e.target.id] = e.target.value
        setCollaboratorData(newCollaborator)
    }

    function addInList(e){
        e.preventDefault();
        api.post("/usuarios", collaboratorData, {headers: {header}}).then( res => {
            console.log(res)
            const newCollaborator = {...collaboratorData};
            newCollaborator.idUsuario = res.data.idUsuario
            setRows([...rows, newCollaborator])
        }).catch(err => {
            console.log(err);
        })
    }

    return (

        <>
            <MenuNovo/>
            <div className="body-config">
                <div className="container">
                    <h1>Adicionar Colaborador</h1>
                    <div className="user-config">
                        <form onSubmit={e => addInList(e)}>
                            <div className="row-configs">

                                <div style={{width: "35%"}} className="col-settings">
                                    <label className="label-settings">Nome:</label>
                                    <input 
                                        className="input-settings"
                                        id="nomeCompleto"
                                        type="text"
                                        onBlur={(e) => addCollaborator(e)}
                                    />
                                </div>

                                <div style={{width: "35%"}} className="col-settings">
                                    <label className="label-settings">Email:</label>
                                    <input 
                                        className="input-settings"
                                        id="loginUsuario"
                                        type="text"
                                        onBlur={(e) => addCollaborator(e)}
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

                    <Table striped bordered hover>
                        <thead>
                            <tr>
                                <th></th>
                                <th>Nome</th>
                                <th>Email</th>
                            </tr>
                        </thead>
                        <tbody>
                            {rows.map((item) => {
                                return (
                                    <tr>
                                        <td>{item.idUsuario}</td>
                                        <td>{item.nomeCompleto}</td>
                                        <td>{item.loginUsuario}</td>
                                    </tr>
                                )
                            })}
                            
                        </tbody>
                    </Table>

                </div>
            </div>
        </>
    );
}