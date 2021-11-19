import { useHistory } from "react-router";
import { useEffect } from "react"; 
import MenuNovo from "../Components/MenuNovo/MenuNovo";
import FilesS3Table from "../Components/FilesS3"

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
                <h1>Files S3</h1>
                <FilesS3Table />
            </div>
            </div>
        </div>
    )
}