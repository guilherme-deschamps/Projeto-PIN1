import React, { Component } from "react";
import { Switch, Route } from "react-router-dom";

import Login from "./components/pages/Login";
import Cadastrar from "./components/pages/Cadastrar";

class Routes extends Component {
  render() {
    return (
      <Switch>
        <Route exact path="/" component={Login} />
        <Route path="/cadastrar" component={Cadastrar} />
      </Switch>
    );
  }
}

export default Routes;
