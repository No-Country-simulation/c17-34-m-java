import React from "react";
import "./hero.css";
import Mockup from "../../assets/images/Mockup/mockup_hero.png";
import { Link } from "react-router-dom";
const Hero = () => {
  return (
    <div className="hero-container desktop-only">
      <section className="hero">
        <div className="content">
          <h1>Compra y vende reventas de forma segura</h1>
          <p>
            Tu Marketplace para comprar y vender entradas para tus eventos
            favoritos.
          </p>
          <Link to="/tickets/purchase">
            <button>Compra tus entradas</button>
          </Link>
        </div>
        <img src={Mockup} alt="mockup" />
      </section>
    </div>
  );
};

export default Hero;
