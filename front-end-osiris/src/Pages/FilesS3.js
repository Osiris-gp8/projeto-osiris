import { useHistory } from "react-router";
import { useEffect } from "react"; 
import MenuNovo from "../Components/MenuNovo/MenuNovo";
import PopUp from "../Components/PopUp"

export default () => {
    
    const history = useHistory();

    useEffect(() => {
        if(!sessionStorage.getItem("token")){
            return history.push('/login');
        }
    }, [])

    return (
        <div>
            <MenuNovo />
            <div className="body-config">
            <div className="container">
                <h1>PopUp</h1>
                <PopUp />
            </div>
            </div>
        </div>
    )
}