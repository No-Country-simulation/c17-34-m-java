import React from "react";
import "./login.css";
import { Link } from "react-router-dom";
import AuthButton from "./../../Buttons/AuthButton/AuthButton";
const Login = () => {
  return (
    <div className="login-container">
      <div className="start-content">
        <h1 className="welcome-title-login">Bienvenido a Troca</h1>
        <p className="login-info">
          Inicia sesión con tu correo electrónico y contraseña.
        </p>
        <form className="login-form">
          <input type="email" placeholder="Email" className="login-input" />
          <input
            type="password"
            placeholder="Contraseña"
            className="login-input"
          />
        </form>
        <div className="forgot-password">
          <Link to="/">Olvidaste tu contraseña?</Link>
        </div>
      </div>
      <div className="login-end-content">
        <AuthButton title="Inicia sesión" />
        <p className="register-redirection">
          No tenes cuenta? <Link to="/register">Regístrate</Link>
        </p>
      </div>
    </div>
  );
};

export default Login;
