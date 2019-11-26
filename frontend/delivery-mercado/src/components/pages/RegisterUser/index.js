import React, { Component } from "react";
import { Typography, Button, TextField, Grid, Card } from "@material-ui/core";
import { Link } from "react-router-dom";
import history from "../../../history";

class RegisterUser extends Component {
  state = {
    name: "",
    fone: "",
    email: "",
    password: "",
    validatePassword: ""
  };

  handleChange = e => {
    this.setState({
      [e.target.name]: e.target.value
    });
  };

  register = () => {
    if (this.state.password === this.state.validatePassword) {
      let formData = new FormData();
      formData.append("nome", this.state.name);
      formData.append("telefone", this.state.fone);
      formData.append("email", this.state.email);
      formData.append("senha", this.state.password);

      let response = fetch("http://localhost:9090/api/usuario/cadastro", {
        method: "POST",
        body: formData
      }).then(function(response) {
        if (response.status === 200) {
          alert("Usuário cadastrado com sucesso!");
          history.push("/login");
        } else {
          alert("teste");
        }
      });
    }
  };

  render() {
    return (
      <div style={{ backgroundColor: "#ED7644", height: "47.1em" }}>
        <Grid container justify="center">
          <Grid item xs={12} md={4}>
            <Card style={{ marginTop: 100, padding: 10 }}>
              <Typography
                variant="h5"
                style={{ textAlign: "center", padding: 15 }}
              >
                Cadastre-se
              </Typography>
              <TextField
                variant="outlined"
                name="name"
                label="Nome completo"
                fullWidth
                onChange={e => this.handleChange(e)}
              />
              <TextField
                variant="outlined"
                name="fone"
                label="Telefone (somente números)"
                fullWidth
                style={{ marginTop: 10 }}
                onChange={e => this.handleChange(e)}
              />
              <TextField
                variant="outlined"
                name="email"
                label="E-mail"
                fullWidth
                style={{ marginTop: 10 }}
                onChange={e => this.handleChange(e)}
              />
              <TextField
                variant="outlined"
                name="password"
                label="Senha"
                type="password"
                fullWidth
                style={{ marginTop: 10 }}
                onChange={e => this.handleChange(e)}
              />
              <TextField
                variant="outlined"
                name="validatePassword"
                label="Confirmar senha"
                type="password"
                fullWidth
                style={{ marginTop: 10 }}
                onChange={e => this.handleChange(e)}
              />
              <Grid container justify="space-between" style={{ marginTop: 20 }}>
                <Grid>
                  <Link to="/login" style={{ textDecoration: "none" }}>
                    <Button
                      color="secondary"
                      variant="outlined"
                      fullWidth
                      onClick={() => {}}
                    >
                      Voltar
                    </Button>
                  </Link>
                </Grid>
                <Grid>
                  <Button
                    color="secondary"
                    variant="contained"
                    fullWidth
                    onClick={() => this.register()}
                  >
                    Cadastrar
                  </Button>
                </Grid>
              </Grid>
            </Card>
          </Grid>
        </Grid>
      </div>
    );
  }
}

export default RegisterUser;
