import download from '@iconify-icons/akar-icons/download';
import Icon from '@iconify/react';
import { getS3Files } from '../../services/uploadData';
import { useEffect, useState } from 'react';
import { useHistory } from 'react-router';
import { ToastContainer } from 'react-toastify';
import Loader from "react-loader-spinner";
import "react-loader-spinner/dist/loader/css/react-spinner-loader.css";
import { formatDateTime } from '../../services/utils'


export default () =>{ 

    const history = useHistory();

    const [isLoading, setIsLoading] = useState(false)
    const [data, setData] = useState([])

    const [headers, setHeaders] = useState({
        "Authorization": `${sessionStorage.getItem("tipo")} ${sessionStorage.getItem("token")}`
    });

    useEffect(() => {
        if(!sessionStorage.getItem("token")){
            return history.push('/login');
        }
    }, [])

    async function getData(date){
        setIsLoading(true)
        const files = await getS3Files(headers, date)
        setData(files)
        setIsLoading(false)
    }

    function handleDate(e){
        let date = e.target.value + "T00:00:00"
        getData(date)
    }

    return (
        <div>
            <form style={{marginBottom: '15px'}}>
                <label name="data" style={{marginBottom: '15px'}}>Selecione a data que subiu o arquivo: </label>
                <br />
                <input name="data" type="date" onChange={e => handleDate(e)} />
            </form>
            <table className="styledTable">
                <thead>
                <tr>
                    <th><h2>Nome</h2></th>
                    <th><h2>Data ultima alteração</h2></th>
                    <th><h2>Download</h2></th>
                </tr>
                </thead>
                <tbody>
                    {data.map((file) => 
                        <tr key={file.httpRequest.allHeaders[2].value}>
                            <td >{file.key}</td>
                            <td>{file.metadata.lastModified.slice(0, 10) + " as " + file.metadata.lastModified.slice(11, 19)}</td>
                            <td><a href={file.httpRequest.uri}><Icon className="icon-menu" icon={download} />download</a></td>
                        </tr>)
                    }
                </tbody>
            </table>
            <div style={{display: 'flex', justifyContent: 'space-around'}}>
                {isLoading == true && <Loader type="Oval" color="#00BFFF" height={80} width={80}/>} 
            </div>
            <ToastContainer />
        </div>
    )
}