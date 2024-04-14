import React from "react";
import "../tickets.css";
import { Link } from "react-router-dom";
import { ThumbsUpIcon } from "./../../Icons/Basic/ThumbsUpIcon";
import MenuTicketHeader from "./../MenuTicketHeader/MenuTicketHeader";
import TicketNavigator from "../../Layout/Mobile/TicketNavigator/TicketNavigator";
const PurchaseTickets = () => {
  return (
    <>
      <MenuTicketHeader />
      <div className="tickets-container">
        <div className="tickets">
          <section className="ticket">
            <div className="header-info">
              <p>Troca User</p>
              <span>-</span>
              <ThumbsUpIcon width="10px" height="10px" />
              <span>99.53%</span>
            </div>
            <h1>Miss Monique</h1>
            <div className="bottom-info">
              <h2>
                <span>ARS$</span> 55,300
              </h2>
              <button className="purchase-ticket-button">Comprar</button>
            </div>
          </section>
          <section className="ticket">
            <div className="header-info">
              <p>Troca User</p>
              <span>-</span>
              <ThumbsUpIcon width="10px" height="10px" />
              <span>99.53%</span>
            </div>
            <h1>Ben bohmer & Eelke kleijn</h1>
            <div className="bottom-info">
              <h2>
                <span>ARS$</span> 27,500
              </h2>
              <button className="purchase-ticket-button">Comprar</button>
            </div>
          </section>
          <section className="ticket">
            <div className="header-info">
              <p>Troca User</p>
              <span>-</span>
              <ThumbsUpIcon width="10px" height="10px" />
              <span>99.53%</span>
            </div>
            <h1>Juan Hansen (Live), transformation (Live)...</h1>
            <div className="bottom-info">
              <h2>
                <span>ARS$</span> 36,700
              </h2>
              <button className="purchase-ticket-button">Comprar</button>
            </div>
          </section>
          <section className="ticket">
            <div className="header-info">
              <p>Troca User</p>
              <span>-</span>
              <ThumbsUpIcon width="10px" height="10px" />
              <span>99.53%</span>
            </div>
            <h1>Miss Monique</h1>
            <div className="bottom-info">
              <h2>
                <span>ARS$</span> 49,800
              </h2>
              <button className="purchase-ticket-button">Comprar</button>
            </div>
          </section>
          <section className="ticket">
            <div className="header-info">
              <p>Troca User</p>
              <span>-</span>
              <ThumbsUpIcon width="10px" height="10px" />
              <span>99.53%</span>
            </div>
            <h1>Ben bohmer & Eelke kleijn</h1>
            <div className="bottom-info">
              <h2>
                <span>ARS$</span> 27,500
              </h2>
              <button className="purchase-ticket-button">Comprar</button>
            </div>
          </section>
          <section className="ticket">
            <div className="header-info">
              <p>Troca User</p>
              <span>-</span>
              <ThumbsUpIcon width="10px" height="10px" />
              <span>99.53%</span>
            </div>
            <h1>Juan Hansen (Live), transformation (Live)...</h1>
            <div className="bottom-info">
              <h2>
                <span>ARS$</span> 36,700
              </h2>
              <button className="purchase-ticket-button">Comprar</button>
            </div>
          </section>
          <section className="ticket">
            <div className="header-info">
              <p>Troca User</p>
              <span>-</span>
              <ThumbsUpIcon width="10px" height="10px" />
              <span>99.53%</span>
            </div>
            <h1>Miss Monique</h1>
            <div className="bottom-info">
              <h2>
                <span>ARS$</span> 55,300
              </h2>
              <button className="purchase-ticket-button">Comprar</button>
            </div>
          </section>
        </div>
      </div>
      <div className="background-ticket-navigator">
        <TicketNavigator />
      </div>
    </>
  );
};

export default PurchaseTickets;
