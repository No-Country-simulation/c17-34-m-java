import React from "react";
import "./register.css";
import { Link } from "react-router-dom";
import { AngleLeftIcon } from "../../Icons/Basic/AngleLeftIcon";
import PrimaryButton from "../../Buttons/PrimaryButton/PrimaryButton";
import TopBarMobile from './../../Layout/Mobile/TopBarMobile/TopBarMobile';
const Register = () => {
  return (
    <>
      <TopBarMobile linkTo="/login" title="Crea tu cuenta"/>
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
          <PrimaryButton text="Registrarse" />
        </div>
      </div>
    </>
  );
};

export default Register;
