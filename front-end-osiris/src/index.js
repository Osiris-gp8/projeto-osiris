import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import ResetStyle from "./style/globalStyle"


ReactDOM.render(
  <React.StrictMode>
    <App/>
    <ResetStyle/>
  </React.StrictMode>,
  document.getElementById('root')
);