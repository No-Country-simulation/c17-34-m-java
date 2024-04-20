import React from "react";
import "./extendedTicketView.css";
import TopBarMobile from "./../../Layout/Mobile/TopBarMobile/TopBarMobile";
import { MercadoPagoIcon } from "../../Icons/SocialMedia/MercadoPagoIcon";
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
                  <span>{seller}</span>
                </li>
                <li>
                  <span>Evento</span>
                  <span>{eventName}</span>
                </li>
                <li>
                  <span>Fecha de compra</span>
                  <span>{purchaseDate}</span>
                </li>
                <li>
                  <span>Método de pago</span>
                  <div>
                    <MercadoPagoIcon/>
                    <span>Mercado Pago</span>
                  </div>
                </li>
                <hr/>
                <li>
                  <span>Total de compra</span>
                  <p><span>ARS$ </span>{total}</p>
                </li>
              </ul>
            </div>
            <div className="bottom">
              <h2>{eventName}</h2>
              <img
                src=""              
              />
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
