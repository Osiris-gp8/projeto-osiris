import React from 'react';
import { ButtonForm } from '../Components/Button'
import MenuNovo from '../Components/MenuNovo/MenuNovo';
import { Table } from 'react-bootstrap';

export default () => {

    function createData(id, name, login, email) {
        return { 
            id: id, 
            name: name, 
            login: login, 
            email: email 
        };
      }
      
      const rows = [
        createData(1, "Patrick Lessa", "patrick03", "p@gmail.com"),
        createData(2, "Rodrigo Busto", "busto10", "r@gmail.com"),
        createData(3, "Kaio Baleeiro", "kaio45", "k@gmail.com"),
      ];

      console.log(rows)

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