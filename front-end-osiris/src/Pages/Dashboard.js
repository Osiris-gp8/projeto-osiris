import Menu from '../Components/Menu'
import Dashboard from '../Components/dashboard'
import Suport_flex from '../Components/Style-Suport/Suport_flex'
import { useHistory } from 'react-router-dom';
import { useEffect } from 'react';

export default () =>{ 
    const history = useHistory();
    useEffect(() => {
        if(sessionStorage.getItem("usuarioLogado") == null || !sessionStorage.getItem("usuarioLogado")){
            console.log("volta")
            return history.push("/login"); 
        }
      
        api.get("/metas").then(res => {
            console.log(res);
        }).catch(err => {
            console.log(err);
        });
    }, []);
    
return (
    <Suport_flex>
        <Menu/>
        <Dashboard/>
    </Suport_flex>
)
}