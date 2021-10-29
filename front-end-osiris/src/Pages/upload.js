import {React, useEffect} from 'react'
import { useHistory } from 'react-router-dom'
import MenuNovo from '../Components/MenuNovo/MenuNovo'
import Upload from '../Components/upload'

export default () =>{ 
    const history = useHistory();
    useEffect(() =>{
        if(!sessionStorage.getItem("token")){
            return history.push('/login');
        }
    }, []);
return (
    <>
        <MenuNovo/>
        <Upload/>
    </>
)
}