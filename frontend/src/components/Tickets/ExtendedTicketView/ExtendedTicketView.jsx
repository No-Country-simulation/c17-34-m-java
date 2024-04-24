import React from "react";
import "./extendedTicketView.css";
import TopBarMobile from "./../../Layout/Mobile/TopBarMobile/TopBarMobile";
import { MercadoPagoIcon } from "../../Icons/SocialMedia/MercadoPagoIcon";
import Qr from "../../../assets/images/qr/qr.png";
const ExtendedTicketView = ({
  eventName,
  eventDate,
  qr,
  address,
  schedule,
  seller,
  purchaseDate,
  total,
}) => {
  eventName = "Ben bohmer & Eelke kleijn";
  eventDate = "Martes 30/04";
  address = "Colectora ruta panamericana N9, KM 60, Cardales";
  schedule = "22PM - 06AM";
  seller = "Troca user";
  purchaseDate = "15/04";
  total = "28,325";
  return (
    <>
      <TopBarMobile linkTo="/wallet/upcoming" title={eventName} />
      <div className="extended-ticket-container">
        <section className="extended-ticket">
          <div className="content">
            <div className="top">
              <h1>Resumen de compra</h1>
              <ul>
                <li>
                  <span>Vendedor</span>
                  <p>{seller}</p>
                </li>
                <li>
                  <span>Evento</span>
                  <p>{eventName}</p>
                </li>
                <li>
                  <span>Fecha de compra</span>
                  <p>{purchaseDate}</p>
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
                    {total}
                  </p>
                </li>
              </ul>
            </div>
            <div className="bottom">
              <h2>{eventName}</h2>
              <img width="200px" height="200px" src={Qr} />
              <h3>{eventDate}</h3>
              <address>{address}</address>
              <p>
                <time>{schedule}</time>
              </p>
            </div>
          </div>
        </section>
      </div>
    </>
  );
};

export default ExtendedTicketView;
