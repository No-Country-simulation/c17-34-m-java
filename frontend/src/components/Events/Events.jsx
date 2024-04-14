import React from "react";
import "./events.css";
import Party1 from "../../assets/images/Parties/party1.jpeg";
import Party2 from "../../assets/images/Parties/party2.jpeg";
import { Link } from "react-router-dom";
const Events = () => {
  return (
    <div className="mobile-event-container">
      <div className="mobile-events">
        <section className="mobile-event">
          <figure>
            <img src={Party1} className="mobile-event-header-img" />
          </figure>
          <div className="mobile-event-content">
            <h2>30/04 Aldea ME Press.:</h2>
            <h1>Ben Bohmer & Eelke Kleijn</h1>
            <address>Colectora ruta panamericana N9, KM 60, Cardales.</address>
            <div>
              <time>22PM - 06AM.</time>
              <Link to="/tickets/purchase">Buscar</Link>
            </div>
          </div>
        </section>
        <section className="mobile-event">
          <figure>
            <img src={Party2} className="mobile-event-header-img" />
          </figure>
          <div className="mobile-event-content">
            <h2>06/05 Vision Press.:</h2>
            <h1>Juan Hansen (Live), Transformation (Live)...</h1>
            <address>Espacio Duam, Neuquen.</address>
            <div>
              <time>23PM - 06AM.</time>
              <Link to="/tickets/purchase">Buscar</Link>
            </div>
          </div>
        </section>
        <section>
          
        </section>
      </div>
    </div>
  );
};

export default Events;
