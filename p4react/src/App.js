import React from 'react';
import logo from './logo.svg';
import './App.css';

import { BrowserRouter, Route, Switch } from 'react-router-dom';

import GameBoard from './Components/TicTacToe/GameBoardSP';
import Lobby from './Components/Lobby/Lobby';
import SignIn from './Components/Login/SignIn';
import UpdatePassword from './Components/Login/UpdatePassword';
import ForgotPassword from './Components/Login/ForgotPassword';
import SignUp from './Components/Login/SignUp';

function App() {
  return (
    
    <BrowserRouter>
        <div>
          
            <Switch>
             <Route path="/" component={SignIn} exact/>
             <Route path="/index" component={SignIn} exact/>
             <Route path="/login" component={SignIn} exact/>
             <Route path="/addUser" component={SignUp}/>
             <Route path="/homepage" component={Lobby}/>
             <Route path="/updatePass" component={UpdatePassword}/>
             <Route path="/recoveryPass" component={ForgotPassword}/>
            <Route component={Error}/>
           </Switch>
        </div> 
      </BrowserRouter>
  );
}

export default App;
