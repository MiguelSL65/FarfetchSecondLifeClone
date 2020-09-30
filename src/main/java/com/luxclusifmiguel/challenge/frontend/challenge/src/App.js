import React, { Component } from "react";
import './App.css';

import { 
  BrowserRouter as Router,
  Route, 
  Switch, 
  Redirect 
 } from "react-router-dom"; 

 // pages
 import MainPage from "./components/index";
 import NotFoundPage from "./components/404";
 import SellingForm from "./components/form";

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
