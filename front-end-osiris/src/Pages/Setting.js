import Menu from '../Components/Menu'
import Setting from '../Components/settings'
import Suport_flex from '../Components/Style-Suport/Suport_flex'

export default () =>{ 

    const history = useHistory();

    const [user, setUser] = useState({});
    const [ecommerce, setEcommerce] = useState({});

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

    useEffect(() =>{
        if(!sessionStorage.getItem("token")){
            return history.push('/login');
        }

        setUser(JSON.parse(sessionStorage.getItem("usuario")));
        setEcommerce(JSON.parse(sessionStorage.getItem("usuario")).ecommerce);
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

    function atualizarUser(e){
        e.preventDefault();
        api.put(`/usuarios/${JSON.parse(sessionStorage.getItem("usuario")).idUsuario}`, user)
            .then(res => {
                console.log(res)
                if(res.status == 200){
                    alert("Usuário alterado com sucesso");
                }else{
                    alert("Ops, houve um erro :(")
                }
            }).catch(err => {
                console.log(err);
                alert("Ops, houve um erro :(");
            })
    }

    function atualizarEcommerce(e){
        e.preventDefault();
        api.put(`/ecommerces/${JSON.parse(sessionStorage.getItem("usuario")).ecommerce.idEcommerce}`, ecommerce)
            .then(res => {
                console.log(res)
                if(res.status == 200){
                    alert("Ecommerce alterado com sucesso");
                }else{
                    alert("Ops, houve um erro :(");
                }
            }).catch(err => {
                console.log(err);
                alert("Ops, houve um erro :(");
            })
    }

    return (
        <>
            <MenuNovo/>
            <div className="body-config">
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
                                        vvalue={ecommerce.cnpj}
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
                            <h2>Conexão</h2>
                        </div>
                        <div className="row-configs">
                            <Input
                                id="uriConnection"
                                width="61%"
                                label="Url:"
                                type="text"
                                value="http://osiris/netshoes/WJNjs5"
                                defaultValue="http://osiris/netshoes/WJNjs5"
                            />
                        </div>
                    </div>

                    {/* <ButtonNoLink
                        type="btn-preenchido"
                        style={{width: "30%"}}
                    >
                        Adicionar Colaborador
                    </ButtonNoLink> */}

                </div>
            </div>
            
        </>

    );
}