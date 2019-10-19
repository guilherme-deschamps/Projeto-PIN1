import React from "react";
import "./App.css";
import Routes from "./routes";
import { Router } from "react-router-dom";
import history from "./history";

function App() {
  return (
    <Router history={history}>
      <Routes />
    </Router>
  );
}

export default App;
