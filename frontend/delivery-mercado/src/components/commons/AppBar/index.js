import React, { Component } from "react";
import { withStyles } from "@material-ui/core/styles";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import Button from "@material-ui/core/Button";
import { Link } from "react-router-dom";
import history from "../../../history";

const useStyles = theme => ({
  root: {
    flexGrow: 1
  },
  menuButton: {
    marginRight: theme.spacing(2)
  },
  title: {
    flexGrow: 1
  }
});

class ButtonAppBar extends Component {
  logout = () => {
    localStorage.removeItem("userId");
    history.push("/");
  };

  render() {
    const { classes, user, cart } = this.props;

    return (
      <AppBar position="static" style={{ backgroundColor: "#873600" }}>
        <Toolbar>
          <Typography variant="h6" className={classes.title}>
            <Link to="" style={{ textDecoration: "none", color: "white" }}>
              Delivery Mercado
            </Link>
          </Typography>
          <Link
            to={{ pathname: "/carrinho", state: { cart: cart, user: user } }}
            style={{ textDecoration: "none", color: "white" }}
          >
            <Button color="inherit" style={{ marginRight: 20 }}>
              Carrinho
            </Button>
          </Link>
          {user === undefined || user === null ? (
            <Link
              to="/login"
              style={{ textDecoration: "none", color: "white" }}
            >
              <Button color="inherit" variant="outlined">
                Login
              </Button>
            </Link>
          ) : (
            <div>
              {user.supermercado === undefined || user.supermercado === null ? (
                <Link
                  to={{
                    pathname: "/supermercado/cadastro",
                    state: { user: user }
                  }}
                  style={{ textDecoration: "none", color: "white" }}
                >
                  <Button color="inherit" style={{ marginRight: 20 }}>
                    Cadastrar supermercado
                  </Button>
                </Link>
              ) : (
                <Link
                  to={{
                    pathname: `/supermercado/${user.supermercado.nome}`,
                    state: { user: user, supermarket: user.supermercado }
                  }}
                  style={{ textDecoration: "none", color: "white" }}
                >
                  <Button color="inherit" style={{ marginRight: 20 }}>
                    Ver {user.supermercado.nome}
                  </Button>
                </Link>
              )}
              <Button
                color="inherit"
                variant="outlined"
                onClick={() => this.logout()}
              >
                LOGOUT
              </Button>
            </div>
          )}
        </Toolbar>
      </AppBar>
    );
  }
}

export default withStyles(useStyles)(ButtonAppBar);
