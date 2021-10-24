import {React} from 'react';
import logoOsiris from '../../Images/logo-osiris.svg';
import {Button, ButtonNoLink} from '../Button';

const Component = () => {

    const btnContato = {
        marginRight: '2%'
    };

    const buttons = {
        width: '100%'
    };

    return (
        <div className="nav">
            <div className="container">
                <img src={logoOsiris} alt="Logo Roxo Escrito Osiris" className="logo" />
                <div style={buttons}>
                    <Button uri="/login" side="right" type="btn-preenchido">Login</Button>
                    <ButtonNoLink section="#contato" side="right" style={btnContato}>Contato</ButtonNoLink>
                </div>

            </div>
        </div>
    );
};
export default Component