import React from 'react'
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
import Dashboard from './Pages/Dashboard'
import Cluster from './Pages/Cluster.js'
import Relation from './Pages/Relation'
import Setting from './Pages/Setting'
import Login from './Pages/Login'
import Register from './Pages/Register'
import NotFound from './Pages/NotFound'

function Routes(){
    return(
        <Router>
            <Switch>
                <Route exact path="/" component={Login} />
                <Route exact path="/register" component={Register} />
                <Route exact path="/home" component={Dashboard} />
                <Route exact path="/cluster-cliente" component={Cluster} />
                <Route exact path="/relation" component={Relation} />
                <Route exact path="/config" component={Setting} />
                <Route component={NotFound} />
            </Switch>
        </Router>

    )
}

export default Routes