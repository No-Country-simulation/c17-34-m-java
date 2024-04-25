import React,{useState} from "react";
import "./login.css";
import { Link } from "react-router-dom";
import PrimaryButton from "../../Buttons/PrimaryButton/PrimaryButton";
import Layout from "./../../Layout/Desktop/Layout";
import { useNavigate } from 'react-router-dom';
import { toast } from "react-toastify";
import { useAuth } from "../../Context/AuthProvider";
const Login = () => {
  const auth =useAuth();
  const navigate = useNavigate();

  const [form, setForm] = useState({
    email: '',
    password: '',
  });

  const [error, setError] = useState('');

  const isValidEmail = (email) => {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    setError('');

    const user = {
      email: form.email,
      password: form.password,
    };
    try {
      await auth.loginAction(user);
      navigate('/')
      toast.success(`¡Bienvenido a TROCA!`, {
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
      if (error.response && error.response.status === 404) {
        setError("El email igresado no existe.")
      } else if (error.response && error.response.status === 403){
        setError("Contraseña incorrecta.")
      } else {
        setError("Ocurrió un error inesperado. Por favor, intente nuevamente.")
        console.error(error);
      }
    }
  };

  const isFormValid = () => {
    return (
      form.email &&
      form.password &&
      form.password.length >= 8 &&
      isValidEmail(form.email)
    );
  };

  const handleChange = (e) => {
    setForm((prevForm) => ({ ...prevForm, [e.target.name]: e.target.value }));
  };
  return (
    
    <Layout>
      <div className="login-container">
        <div className="login">
          <h1 className="welcome-title-login mobile-only">
            Bienvenido a Troca
          </h1>
          <h1 className="welcome-title-login desktop-only">Inicia sesión</h1>
          <p className="login-info">
            Inicia sesión con tu correo electrónico y contraseña.
          </p>
          <form className="login-form" onSubmit={handleSubmit}>
            <input type="email" name="email" placeholder="Email" className="login-input" onChange={handleChange}/>
            <div>
              <input
                type="password"
                name= "password"
                placeholder="Contraseña"
                className="login-input"
                onChange={handleChange}
              />
               {error && <p className="error">{error}</p>}
               <div className="forgot-password">
              <Link to="/login">Olvidaste tu contraseña?</Link>
            </div>
            </div>
            <div>
              <PrimaryButton text="Inicia sesión" type="submit" disabled={!isFormValid()}/>
              <p className="register-redirection">
                No tenes cuenta? <Link to="/register">Regístrate</Link>
              </p>
            </div>
          </form>
         
        </div>
      </div>
    </Layout>
  );
};

export default Login;
