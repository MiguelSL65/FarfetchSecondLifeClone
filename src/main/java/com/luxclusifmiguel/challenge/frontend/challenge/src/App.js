import React, { Component } from "react";
import logo from './logo.svg';
import './App.css';

import { 
  BrowserRouter as Router,
  Route, 
  Switch, 
  Link, 
  Redirect 
 } from "react-router-dom"; 

 // pages
 import MainPage from "./pages";
 import NotFoundPage from "./pages/404";
 import SellingForm from "./pages/form";

class App extends Component {
  render () {
    return (
    <Router>
      <Switch>
      <Route exact path="/" component={MainPage} />
      <Route exact path="/404" component={NotFoundPage} />
      <Route exact path="/form" component={SellingForm} />
      <Redirect to="/404" />
      </Switch>
    </Router>
    );
  }
}

export default App;
