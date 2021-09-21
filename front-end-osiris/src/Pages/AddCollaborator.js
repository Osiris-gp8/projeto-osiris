import {React, useState, useEffect} from 'react';
import { ButtonForm } from '../Components/Button'
import MenuNovo from '../Components/MenuNovo/MenuNovo';
import { Table } from 'react-bootstrap';

export default () => {

    useEffect(() => {
        setIdEcommerce(JSON.parse(sessionStorage.getItem("usuario")).ecommerce.idEcommerce);
    })

    function createData(id, name, login, email) {
        return { 
            id: id, 
            name: name, 
            login: login, 
            email: email 
        };
    }

    const [idEcommerce, setIdEcommerce] = useState(0);

    const [rows, setRows] = useState([
        createData(1, "Patrick Lessa", "patrick03", "p@gmail.com"),
        createData(2, "Rodrigo Busto", "busto10", "r@gmail.com"),
        createData(3, "Kaio Baleeiro", "kaio45", "k@gmail.com"),
    ]);

    const [collaboratorData, setCollaboratorData]= useState({
        loginUsuario: "",
        email: "",
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
        setRows([...rows, createData(1, collaboratorData.nomeCompleto, collaboratorData.loginUsuario, collaboratorData.email)])
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
                                    <label className="label-settings">Login:</label>
                                    <input 
                                        className="input-settings"
                                        id="loginUsuario"
                                        type="text"
                                        onBlur={(e) => addCollaborator(e)}
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
                                <th>Login</th>
                                <th>E-mail</th>
                            </tr>
                        </thead>
                        <tbody>
                            {rows.map((item) => {
                                return (
                                    <tr>
                                        <td>{item.id}</td>
                                        <td>{item.name}</td>
                                        <td>{item.login}</td>
                                        <td>{item.email}</td>
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