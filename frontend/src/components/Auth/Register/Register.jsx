import React,{useState} from "react";
import "./register.css";
import axios from "axios";
import PrimaryButton from "../../Buttons/PrimaryButton/PrimaryButton";
import TopBarMobile from "./../../Layout/Mobile/TopBarMobile/TopBarMobile";
import Layout from "../../Layout/Desktop/Layout";
import { useNavigate } from 'react-router-dom';
import { toast } from "react-toastify";
const Register = () => {

  const navigateTo = useNavigate();

  const [form, setForm] = useState({
    email: '',
    name: '',
    lastname: '',
    dni: '',
    password: '',
    confirmPassword: '',
  });

  const [errors, setErrors] = useState({
    email: '',
    password: '',
  });

  const isValidEmail = (email) => {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
  };

  const registerUser = async (e) => {
    e.preventDefault();

    setErrors({ email: ''});

    const user = {
      email: form.email,
      password: form.password,
      profile: {
        name: form.name,
        lastname: form.lastname,
        document: form.dni,
      },
    };

    try {
      const response = await axios.post('https://troca-prod.onrender.com/user/subscribe', user);
      navigateTo('/login')
      toast.success(`¡Registro exitoso! Ya sos parte de TROCA`, {
        position: "top-center",
        autoClose: 2500,
        hideProgressBar: true,
        closeOnClick: false,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "dark",
      });
    } catch (error) {
      if (error.response && error.response.status === 406) {
        setErrors((prevErrors) => ({ ...prevErrors, email: 'El email ya está en uso' }));
      } else {
        console.error(error);
      }
    }
  };

  const isFormValid = () => {
    return (
      form.email &&
      form.name &&
      form.lastname &&
      form.dni &&
      form.password &&
      form.confirmPassword &&
      form.password === form.confirmPassword &&
      form.password.length >= 8 &&
      isValidEmail(form.email)
    );
  };

  const handleChange = (e) => {
    setForm((prevForm) => ({ ...prevForm, [e.target.name]: e.target.value }));
  };
  
  return (
    <Layout>
      <TopBarMobile linkTo="/login" title="Crea tu cuenta" />
      <hr className="register-hr mobile-only" />
      <div className="register-container">
        <div className="register">
          <h1 className="register-title desktop-only">Crea tu cuenta</h1>
          <p className="register-info">
            Completa con tus datos para crear tu cuenta <span>TROCA</span>.
          </p>
          <form className="register-form" onSubmit={registerUser}>
            <div>
              <input type="email" name="email" placeholder="Email" onChange={handleChange} />
              {errors.email && <p className="error">{errors.email}</p>}
            </div>
            <input type="text" name="name" placeholder="Nombre" onChange={handleChange} />
            <input type="text" name="lastname" placeholder="Apellido" onChange={handleChange} />
            <input type="number" name="dni" placeholder="DNI" onChange={handleChange} />
            <div className="password">
              <input type="password" name="password" placeholder="Contraseña" onChange={handleChange} />
              <input type="password" name="confirmPassword" placeholder="Confirmar contraseña" onChange={handleChange} />
            </div>
            <PrimaryButton text="Registrarse" type="submit" disabled={!isFormValid()} />
          </form>
        </div>
      </div>
    </Layout>
  );
};

export default Register;