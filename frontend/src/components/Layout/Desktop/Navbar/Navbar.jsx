import React, { useEffect } from "react";
import { useLocation } from "react-router-dom";
import { Link, NavLink } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import "./navbar.css";
import TrocaLogo from "../../../Icons/TrocaLogo/TrocaLogo";
import { useAuth } from "../../../Context/AuthProvider";
import { toast } from "react-toastify";
const Navbar = () => {
  const auth = useAuth();
  const navigate = useNavigate();
  const { pathname } = useLocation();

  const logout = () => {
    auth.setUser(null);
    localStorage.removeItem("troca");
    navigate("/login");
    toast.success(`¡Saliste de TROCA!`, {
      position: "top-center",
      autoClose: 2500,
      hideProgressBar: true,
      closeOnClick: false,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
      theme: "dark",
    });
  };

  useEffect(() => {
    const titleMap = {
      "/": "Home",
      "/candidatos": "Candidatos",
    };
    document.title = titleMap[pathname] || "Troca";
    window.scrollTo({ top: 0, behavior: "smooth" });
  }, [pathname]);

  return (
    <div className="navbar-container desktop-only">
      <nav className="navbar ">
        <div className="nav-menu">
          <ul>
            <li className="troca-logo ">
              <Link to="/" aria-label="Volver a la página principal">
                <div>
                  <TrocaLogo />
                </div>
              </Link>
            </li>
            <li>
              <NavLink to="/">Home</NavLink>
            </li>
            <li>
              <NavLink to="/tickets/purchase">Mercado P2P</NavLink>
            </li>
            <li>
              <NavLink to="/wallet/upcoming">Wallet</NavLink>
            </li>
          </ul>

          <div className="auth-links">
            {auth.user ? (
               <button className="register-button" onClick={logout}>Cerrar sesion</button>
            ) : (
              <>
                {" "}
                <Link
                  to="/login"
                  aria-label="Iniciar sesión"
                  className="login-link"
                >
                  Inicia sesión
                </Link>
                <Link to="/register" aria-label="Registrarse">
                  <button className="register-button">Registrate</button>
                </Link>
              </>
            )}
          </div>
        </div>
      </nav>
    </div>
  );
};

export default Navbar;
