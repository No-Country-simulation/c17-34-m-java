import React, { useEffect, useState } from "react";
import "./extendedTicketView.css";
import TopBarMobile from "./../../Layout/Mobile/TopBarMobile/TopBarMobile";
import { MercadoPagoIcon } from "../../Icons/SocialMedia/MercadoPagoIcon";
import Qr from "../../../assets/images/qr/qr.png";
import TrocaSimple from "../../../assets/images/TrocaLogo/troca-simple.jpg";
import { useAuth } from "../../Context/AuthProvider";
import { Link, useParams } from "react-router-dom";
import axios from "axios";
import SpinnerLoader from "./../../SpinnerLoader/SpinnerLoader";
import SecondaryButton from "../../Buttons/SecondaryButton/SecondaryButton";
import TrocaLogo from "../../Icons/TrocaLogo/TrocaLogo";
const ExtendedTicketView = () => {
  const auth = useAuth();
  const idUser = auth.user && auth.user.profile.id;
  const { ticketId } = useParams();
  const [isLoading, setIsLoading] = useState(true);
  const [ticket, setTicket] = useState([]);
  const getTicket = async () => {
    try {
      const response = await axios.get(
        `https://troca-prod-main.onrender.com/ticket/id/${ticketId}`
      );
      setTicket(response.data.data);
      console.log("response", response.data.data);
      setIsLoading(false);
    } catch (error) {
      setTicket([]);
      setIsLoading(false);
      console.error("Error al obtener el ticket:", error);
    }
  };
  console.log("ticket", ticket);
  useEffect(() => {
    getTicket();
    setIsLoading(true);
  }, [ticketId]);
  return (
    <>
      {isLoading ? (
        <SpinnerLoader />
      ) : (
        <div>
          {ticket.length == 0 ? (
            <>
              <TopBarMobile
                linkTo={`/wallet/upcoming/${idUser}`}
                title="Ticket no encontrado"
              />
              <div className="extended-ticket-container">
                <section className="extended-ticket">
                  <div className="content">
                    <div className="top"></div>
                    <div className="bottom">
                      <TrocaLogo />
                      <h2 style={{ marginTop: "4vw" }}>
                        Error al mostrar el ticket
                      </h2>
                      <Link to={`/wallet/upcoming/${idUser}`}>
                        <SecondaryButton text="Volver a la wallet" />
                      </Link>
                    </div>
                  </div>
                </section>
              </div>
            </>
          ) : (
            <>
              <TopBarMobile
                linkTo={`/wallet/upcoming/${idUser}`}
                title={ticket && ticket.event.name}
              />
              <div className="extended-ticket-container">
                <section className="extended-ticket">
                  <div className="content">
                    <div className="top">
                      <h1>Resumen de compra</h1>
                      <ul>
                        <li>
                          <span>Nombre y apellido</span>
                          <p>
                            {ticket && ticket.owner.name}{" "}
                            {ticket && ticket.owner.lastname}
                          </p>
                        </li>
                        <li>
                          <span>Evento</span>
                          <p>{ticket && ticket.event.name}</p>
                        </li>
                        <li>
                          <span>Fecha de compra</span>
                          <p>02/05/24</p>
                        </li>
                        <li>
                          <span>Método de pago</span>
                          <div className="mp">
                            <MercadoPagoIcon />
                            <p>Mercado Pago</p>
                          </div>
                        </li>
                        <hr />
                        <li>
                          <span>Total de compra</span>
                          <p className="price">
                            <span>ARS$ </span>
                            {ticket && ticket.price}
                          </p>
                        </li>
                      </ul>
                    </div>
                    <div className="bottom">
                      <h2>{ticket && ticket.event.name}</h2>
                      <img width="200px" height="200px" src={Qr} />
                      <h3>Martes 30/04</h3>
                      <address>
                        {(ticket && ticket.event.address) ||
                          "Espacio Duam, Neuquen."}
                      </address>
                      <p>
                        <time>22PM - 06AM</time>
                      </p>
                    </div>
                  </div>
                </section>
              </div>
            </>
          )}
        </div>
      )}
    </>
  );
};

export default ExtendedTicketView;
