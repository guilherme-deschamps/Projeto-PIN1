import React, { Component } from "react";
import { Typography, Button, TextField, Grid, Card } from "@material-ui/core";
import { Link } from "react-router-dom";
import MySnackbar from "../../commons/Snackbar";
import history from "../../../history";

class Login extends Component {
  state = {
    user: null,
    email: "",
    password: "",
    disabled: true,
    openSnackbar: false,
    titleSnackbar: "Login ou senha incorreto. Tente novamente!"
  };

  handleChange = e => {
    this.setState({
      [e.target.name]: e.target.value
    });

    if (this.state.email === "" || this.state.password === "") {
      this.setState({ disabled: true });
    } else {
      this.setState({ disabled: false });
    }
  };

  login = async () => {
    let formData = new FormData();
    formData.append("email", this.state.email);
    formData.append("senha", this.state.password);
    let response = await fetch("http://backend:9090/api/usuario/login", {
      method: "POST",
      body: formData
    })
      .then(res => res.json())
      .catch(() => undefined);

    if (response !== undefined && response.id !== undefined) {
      localStorage.setItem("userId", response.id);
      history.push("/");
    } else this.setState({ openSnackbar: true });
  };

  onClose = () => {
    this.setState({
      openSnackbar: false
    });
  };

  openSnackbar = () => {
    this.setState({
      openSnackbar: true
    });
  };

  render() {
    return (
      <div style={{ backgroundColor: "#ED7644", height: "47.1em" }}>
        <Grid container justify="center">
          <Grid item xs={4}>
            <Card
              style={{
                marginTop: 150,
                padding: 20
              }}
            >
              <Typography
                variant="h5"
                style={{ textAlign: "center", padding: 15, marginBottom: 10 }}
              >
                Login
              </Typography>
              <TextField
                variant="outlined"
                name="email"
                label="Login"
                fullWidth
                onChange={e => this.handleChange(e)}
              />
              <TextField
                variant="outlined"
                name="password"
                label="Password"
                type="password"
                fullWidth
                style={{ marginTop: 10 }}
                onChange={e => this.handleChange(e)}
              />
              <Grid container justify="space-between" style={{ marginTop: 20 }}>
                <Grid item xs={5}>
                  <Link to="/" style={{ textDecoration: "none" }}>
                    <Button
                      color="secondary"
                      variant="outlined"
                      onClick={() => {}}
                    >
                      Voltar
                    </Button>
                  </Link>
                </Grid>
                <Grid item xs={3}>
                  <Link
                    to="/usuario/cadastro"
                    style={{ textDecoration: "none" }}
                  >
                    <Button
                      color="secondary"
                      variant="text"
                      fullWidth
                      onClick={() => {}}
                    >
                      Cadastrar
                    </Button>
                  </Link>
                </Grid>
                <Grid item xs={3}>
                  <Button
                    disabled={this.state.disabled}
                    color="secondary"
                    variant="contained"
                    fullWidth
                    onClick={() => this.login()}
                  >
                    Entrar
                  </Button>
                </Grid>
              </Grid>
            </Card>
          </Grid>
        </Grid>
        <MySnackbar
          title={this.state.titleSnackbar}
          open={this.state.openSnackbar}
          onClose={this.onClose}
        />
      </div>
    );
  }
}

export default Login;
