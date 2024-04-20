import React from "react";
import "../offers.css";
import TopBarMobile from "../../Layout/Mobile/TopBarMobile/TopBarMobile";
import { PlusIcon } from "./../../Icons/Basic/PlusIcon";
import { Link, NavLink } from "react-router-dom";
import FloatingNavbarMobile from "./../../Layout/Mobile/FloatingNavbarMobile/FloatingNavbarMobile";
import SecondaryMobileButton from "../../Buttons/mobile/SecondaryMobileButton";
import { ThumbsUpIcon } from "../../Icons/Basic/ThumbsUpIcon";
const ActiveOffers = () => {
  return (
    <>
      <TopBarMobile linkTo="/tickets/purchase" title="Ofertas">
        <Link to="/order/purchase">
          <PlusIcon width="24px" height="24px" />
        </Link>
      </TopBarMobile>
      <ul className="offers-navbar">
        <NavLink to="/offers/active">Activas</NavLink>
        <NavLink to="/offers/completed">Finalizadas</NavLink>
      </ul>
      <div className="offers-container">
        <div className="offers">
          <section className="offer">
            <div className="header-info">
              <p>Troca User</p>
              <span>-</span>
              <ThumbsUpIcon width="10px" height="10px" />
              <span>99.53%</span>
            </div>
            <h1>
              <span style={{ color: "var(--color-red)" }}>Venta </span>Miss
              Monique
            </h1>
            <div className="bottom-info">
              <h2>
                <span>ARS$</span> 55,300
              </h2>
              <SecondaryMobileButton backColor="#6A6A6A">
                Pausada
              </SecondaryMobileButton>
            </div>
          </section>
          <section className="offer">
            <div className="header-info">
              <p>Troca User</p>
              <span>-</span>
              <ThumbsUpIcon width="10px" height="10px" />
              <span>99.53%</span>
            </div>
            <h1>
              <span style={{ color: "var(--color-green)" }}>Compra </span>Ben bohmer & Eelke kleijn
            </h1>
            <div className="bottom-info">
              <h2>
                <span>ARS$</span> 27,500
              </h2>
              <SecondaryMobileButton fontColor="var(--color-active)">
                Activa
              </SecondaryMobileButton>
            </div>
          </section>
          
        </div>
      </div>
      <div className="background-offer-navigator">
        <FloatingNavbarMobile />
      </div>
    </>
  );
};

export default ActiveOffers;
