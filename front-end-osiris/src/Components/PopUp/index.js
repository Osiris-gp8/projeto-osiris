import download from '@iconify-icons/akar-icons/download';
import Icon from '@iconify/react';
import { useEffect } from 'react';
import { useHistory } from 'react-router';


export default () =>{ 

    const history = useHistory();

    useEffect(() => {
        if(!sessionStorage.getItem("token")){
            return history.push('/login');
        }
    }, [])

    return (
        <div>
            <table className="styledTable">
                <tr>
                    <th><h2>Nome</h2></th>
                    <th><h2>Data ultima alteração</h2></th>
                    <th><h2>Download</h2></th>
                </tr>
                <tr>
                    <td>import 1</td>
                    <td>08/11/2021</td>
                    <td><Icon className="icon-menu" icon={download} /></td>
                </tr>
            </table>
        </div>
    )
}