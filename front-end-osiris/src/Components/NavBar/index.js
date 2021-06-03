import {React, useState} from 'react';
// import { Container } from '../../style/style';
// import { Navbar, Logo } from './style';
import logoOsiris from '../../Images/logo-osiris.svg';
import {Button, ButtonNoLink} from '../Button';

export default () => {

    const btnContato = {
        marginRight: '2%'
    }   

    const buttons = {
        width: '100%'
    }

    return(
        // <Navbar>
        //     <Container>
        //         <Logo src={logoOsiris}/>
        //         <Button uri="#" buttonColor="#666BC2" fontColor="#ECF0F7" lado="right" >Login</Button>
        //         <Button uri="#" lado="right" >Contato</Button>
        //     </Container>
        // </Navbar>    
        <div className="nav">
            <div className="container">
                <img src={logoOsiris} className="logo"/>
                <div style={buttons}>
                    <Button uri="/login" side="right" type="btn-preenchido">Login</Button>
                    {/* <Button uri="#contato" side="right" style={btnContato}>Contato</Button> */}
                    {/* <a href="#contato" className="btn link right" style={btnContato}>Contato</a> */}
                    <ButtonNoLink section="#contato" side="right" style={btnContato}>Contato</ButtonNoLink>
                </div>
                
            </div>
        </div>
    );
}