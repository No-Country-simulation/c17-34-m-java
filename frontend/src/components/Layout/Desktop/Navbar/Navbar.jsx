import React, { useEffect } from "react";
import { useLocation } from "react-router-dom";
import { Link, NavLink } from "react-router-dom";

import "./navbar.css";
import TrocaLogo from "../../../Icons/TrocaLogo/TrocaLogo";
const Navbar = () => {
  const { pathname } = useLocation();

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
            <Link to="/" aria-label="Iniciar sesión" className="login-link">
              Inicia sesión
            </Link>
            <Link to="/" aria-label="Registrarse">
              <button className="register-button">
                Registrate
              </button>
            </Link>
          </div>
        </div>
      </nav>
    </div>
  );
};

export default Navbar;
