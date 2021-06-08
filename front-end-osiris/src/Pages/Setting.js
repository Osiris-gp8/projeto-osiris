import MenuNovo from '../Components/MenuNovo/MenuNovo'
import Input from '../Components/Input/Input'
import Icon from '@iconify/react'
import pencilIcon from '@iconify-icons/akar-icons/pencil';
import {ButtonNoLink} from '../Components/Button'

export default () =>{ 
    return (
        <>
            <MenuNovo/>
            <div className="body-config">
                <div className="container">
                    <h1>Configuração</h1>
                    <div className="user-config">
                        <div className="configs-head">
                            <h2>Usuário</h2>
                            <span><Icon icon={pencilIcon}/> Editar</span>
                        </div>
                        <div className="row-configs">
                            <Input
                                id="nomeUser"
                                width="28%"
                                label="Nome:"
                                type="text"
                                value="patrick"
                                defaultValue="patrick"
                            />

                            <Input
                                id="emailUser"
                                width="28%"
                                label="E-mail:"
                                type="email"
                                value="patrick@gmail.com"
                                defaultValue="patrick@gmail.com"
                            />

                            <Input
                                id="senhaUser"
                                width="28%"
                                label="Senha:"
                                type="password"
                                value="*****"
                                defaultValue="******"
                            />
                        </div>
                    </div>

                    <div className="user-config">
                        <div className="configs-head">
                            <h2>Ecommerce</h2>
                            <span><Icon icon={pencilIcon}/> Editar</span>
                        </div>
                        <div className="row-configs">
                            <Input
                                id="nomeEcommerce"
                                width="28%"
                                label="Nome:"
                                type="text"
                                value="Netshoes"
                                defaultValue="Netshoes"
                            />

                            <Input
                                id="cnpjEcommerce"
                                width="28%"
                                label="Cnpj:"
                                type="text"
                                value="99.999.999/9999-99"
                                defaultValue="99.999.999/9999-99"
                            />
                        </div>
                    </div>

                    <div className="user-config">
                        <div className="configs-head">
                            <h2>Conexão</h2>
                            <span><Icon icon={pencilIcon}/> Editar</span>
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

                    <ButtonNoLink
                        type="btn-preenchido"
                        style={{width: "30%"}}
                    >
                        Adicionar Colaborador
                    </ButtonNoLink>

                </div>
            </div>
            
        </>

    );
}