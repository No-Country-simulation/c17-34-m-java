import React from "react";
import "../tickets.css";
import { ThumbsUpIcon } from "./../../Icons/Basic/ThumbsUpIcon";
import MenuTicketHeader from "./../MenuTicketHeader/MenuTicketHeader";
import SecondaryMobileButton from "./../../Buttons/mobile/SecondaryMobileButton";
import FloatingNavbarMobile from "./../../Layout/Mobile/FloatingNavbarMobile/FloatingNavbarMobile";
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
              <SecondaryMobileButton backColor="var(--color-green)">
                Comprar
              </SecondaryMobileButton>
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
              <SecondaryMobileButton backColor="var(--color-green)">
                Comprar
              </SecondaryMobileButton>
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
              <SecondaryMobileButton backColor="var(--color-green)">
                Comprar
              </SecondaryMobileButton>
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
              <SecondaryMobileButton backColor="var(--color-green)">
                Comprar
              </SecondaryMobileButton>
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
              <SecondaryMobileButton backColor="var(--color-green)">
                Comprar
              </SecondaryMobileButton>
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
              <SecondaryMobileButton backColor="var(--color-green)">
                Comprar
              </SecondaryMobileButton>
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
              <SecondaryMobileButton backColor="var(--color-green)">
                Comprar
              </SecondaryMobileButton>
            </div>
          </section>
        </div>
      </div>
      <div className="background-ticket-navigator">
        <FloatingNavbarMobile />
      </div>
    </>
  );
};

export default PurchaseTickets;
