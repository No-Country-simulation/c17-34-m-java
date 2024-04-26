import React, { useEffect, useState } from "react";
import "./events.css";
import Party1 from "../../assets/images/Parties/party1.jpeg";
import Party2 from "../../assets/images/Parties/party2.jpeg";
import { Link } from "react-router-dom";
import axios from "axios";
const Events = () => {
  const [events, setEvents] = useState([]);
  useEffect(() => {
    const getEvents = async () => {
      try {
        const response = await axios.get(
          "https://troca-prod-main.onrender.com/event/all"
        );
        setEvents(response.data.data);
      } catch (error) {
        console.error("Error al obtener los eventos:", error);
      }
    };
    getEvents(); 
  }, []);
  return (
    <div className="event-container">
      <div className="events">
        {events.map((event) => (
        <section className="event" key={event.id}>
          <figure>
            <img src={Party1} className="event-header-img" />
          </figure>
          <div className="event-content">
            <h2>30/04 Aldea ME Press.:</h2>
            <h1>{event.name}</h1>
            <address>Colectora ruta panamericana N9, KM 60, Cardales.</address>
            <div>
              <time>22PM - 06AM.</time>
              <Link to={`/tickets/purchase/event/${event.id}`}>Buscar</Link>
            </div>
          </div>
        </section>
        ))}
      </div>
    </div>
  );
};

export default Events;
