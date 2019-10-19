import React, { Component } from "react";
import { Typography, Button, TextField, Grid, Card } from "@material-ui/core";
import { Link } from "react-router-dom";

class Login extends Component {
  state = {
    email: "",
    senha: "",
    disabled: true
  };

  handleChange = e => {
    this.setState({
      [e.target.name]: e.target.value
    });

    if (this.state.email === "" || this.state.senha === "") {
      this.setState({ disabled: true });
    } else {
      this.setState({ disabled: false });
    }
  };

  login = () => {
    let formData = new FormData();
    formData.append("email", this.state.email);
    formData.append("senha", this.state.senha);
    let response = fetch("http://localhost:9090/api/usuario/login", {
      method: "POST",
      body: formData
    });

    console.log("Response: ", response);
  };

  render() {
    return (
      <Grid container justify="center">
        <Grid item xs={12} md={4}>
          <Card style={{ marginTop: 100, padding: 10 }}>
            <Typography
              variant="h5"
              style={{ textAlign: "center", padding: 15 }}
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
              name="senha"
              label="Password"
              type="password"
              fullWidth
              style={{ marginTop: 10 }}
              onChange={e => this.handleChange(e)}
            />
            <Grid container justify="space-between" style={{ marginTop: 20 }}>
              <Grid md={3}>
                <Link to="/cadastrar" style={{ textDecoration: "none" }}>
                  <Button
                    color="primary"
                    variant="outlined"
                    fullWidth
                    onClick={() => {}}
                  >
                    Cadastrar
                  </Button>
                </Link>
              </Grid>
              <Grid md={3}>
                <Button
                  disabled={this.state.disabled}
                  color="primary"
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
    );
  }
}

export default Login;
