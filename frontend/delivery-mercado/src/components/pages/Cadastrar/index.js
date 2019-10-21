import React, { Component } from "react";
import { Typography, Button, TextField, Grid, Card } from "@material-ui/core";
import { Link } from "react-router-dom";

class Cadastrar extends Component {
  state = {
    nome: "",
    telefone: "",
    email: "",
    senha: "",
    confirmarSenha: ""
  };

  handleChange = e => {
    this.setState({
      [e.target.name]: e.target.value
    });
  };

  cadastrar = () => {
    if (this.state.senha === this.state.confirmarSenha) {
      let formData = new FormData();
      formData.append("nome", this.state.nome);
      formData.append("telefone", this.state.telefone);
      formData.append("email", this.state.email);
      formData.append("senha", this.state.senha);

      let response = fetch("http://localhost:9090/api/usuario/cadastro", {
        method: "POST",
        body: formData
      }).then(res => res.json());

      if (response.ok) {
        alert("Usuário cadastrado com sucesso!");
      }

      console.log(response);
    } else {
      alert("Senhas não conferem");
    }
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
              Cadastrar
            </Typography>
            <TextField
              variant="outlined"
              name="nome"
              label="Nome completo"
              fullWidth
              onChange={e => this.handleChange(e)}
            />
            <TextField
              variant="outlined"
              name="telefone"
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
              name="senha"
              label="Senha"
              type="password"
              fullWidth
              style={{ marginTop: 10 }}
              onChange={e => this.handleChange(e)}
            />
            <TextField
              variant="outlined"
              name="confirmarSenha"
              label="Confirmar senha"
              type="password"
              fullWidth
              style={{ marginTop: 10 }}
              onChange={e => this.handleChange(e)}
            />
            <Grid container justify="space-between" style={{ marginTop: 20 }}>
              <Grid>
                <Link to="/" style={{ textDecoration: "none" }}>
                  <Button
                    color="primary"
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
                  color="primary"
                  variant="contained"
                  fullWidth
                  onClick={() => this.cadastrar()}
                >
                  Cadastrar
                </Button>
              </Grid>
            </Grid>
          </Card>
        </Grid>
      </Grid>
    );
  }
}

export default Cadastrar;
