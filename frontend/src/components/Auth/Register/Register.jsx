import React from "react";
import "./register.css";
import { Link } from "react-router-dom";
import AuthButton from "../../Buttons/AuthButton/AuthButton";
import { AngleLeftIcon } from "../../Icons/Basic/AngleLeftIcon";
const Register = () => {
  return (
    <>
      <div className="register-header">
        <Link to="/login" className="login-back">
          <AngleLeftIcon width="20px" height="20px" fill="black"/>
        </Link>
        <h1>Crea tu cuenta</h1>
      </div>
      <hr className="register-hr" />
      <div className="register-content">
        <div className="register-start-content">
          <p className="register-info">
            Ingresa tus datos para crear una cuenta.
          </p>
          <form className="register-form">
            <input type="email" placeholder="Email" />
            <input type="text" placeholder="Nombre de usuario" />
            <input type="number" placeholder="DNI" />
            <input type="password" placeholder="Contraseña" />
            <input type="password" placeholder="Confirmar contraseña" />
          </form>
        </div>
        <div className="register-end-content">
          <AuthButton title="Registrate" />
        </div>
      </div>
    </>
  );
};

export default Register;
