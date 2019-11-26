import React, { Component } from "react";
import { Switch, Route } from "react-router-dom";

import Login from "./components/pages/Login";
import RegisterUser from "./components/pages/RegisterUser";
import RegisterSupermarket from "./components/pages/RegisterSupermarket";
import SupermarketProfile from "./components/pages/SupermarketProfile";
import Supermarket from "./components/pages/Supermarkets";
import RegisterProduct from "./components/pages/RegisterProduct";
import Cart from "./components/pages/Cart";

class Routes extends Component {
  render() {
    return (
      <Switch>
        <Route path="/login" component={Login} />
        <Route path="/carrinho" component={Cart} />
        <Route path="/usuario/cadastro" component={RegisterUser} />
        <Route path="/supermercado/cadastro" component={RegisterSupermarket} />
        <Route
          path="/supermercado/:nome"
          exact
          component={SupermarketProfile}
        />
        <Route path="/supermercado/:nome/produto" component={RegisterProduct} />
        <Route exact path="/" component={Supermarket} />
      </Switch>
    );
  }
}

export default Routes;
