import React from 'react'
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
import Dashboard from './Pages/Dashboard'
import Relation from './Pages/Sales'
import Setting from './Pages/Setting'
import Login from './Pages/Login'
import Register from './Pages/Register'
import NotFound from './Pages/NotFound'
import Institucional from './Pages/Institucional';
import upload from './Pages/upload'
import Password from './Pages/Password'
import AddCollaborator from './Pages/AddCollaborator'
import Access from './Pages/Access'
import FilesS3 from './Pages/FilesS3'

function Routes(){
    return(
        <Router>
            <Switch>
                <Route exact path="/" component={Institucional} />
                <Route exact path="/login/:setSenha?" component={Login} />
                <Route exact path="/register" component={Register} />
                <Route exact path="/home" component={Dashboard} />
                <Route exact path="/sales" component={Relation} />
                <Route exact path="/config" component={Setting} />
                <Route exact path="/upload" component={upload} />
                <Route exact path="/password/:token?" component={Password} />
                <Route exact path="/addCollaborator" component={AddCollaborator}/>
                <Route exact path="/access" component={Access}/>
                <Route exact path="/files" component={FilesS3} />
                <Route component={NotFound} />
            </Switch>
        </Router>

    )
}

export default Routes