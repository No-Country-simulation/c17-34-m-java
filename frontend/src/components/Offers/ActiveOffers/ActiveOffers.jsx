import React from "react";
import "../offers.css";
import TopBarMobile from "../../Layout/Mobile/TopBarMobile/TopBarMobile";
import { PlusIcon } from "./../../Icons/Basic/PlusIcon";
import { Link, NavLink } from "react-router-dom";
import FloatingNavbarMobile from "./../../Layout/Mobile/FloatingNavbarMobile/FloatingNavbarMobile";
import { ThumbsUpIcon } from "../../Icons/Basic/ThumbsUpIcon";
import Layout from './../../Layout/Desktop/Layout';
import SecondaryButton from './../../Buttons/SecondaryButton/SecondaryButton';
const ActiveOffers = () => {
  return (
    <Layout>
     <div className="background-offer-navigator">
        <FloatingNavbarMobile />
      </div>
      <TopBarMobile linkTo="/tickets/purchase" title="Ofertas">
        <Link to="/ticket/add">
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
              <p>Santiago</p>
              <span>-</span>
              <ThumbsUpIcon width="10px" height="10px" />
              <span>99.53%</span>
            </div>
            <h1>
              <span style={{ color: "var(--color-green)" }}>Compra </span>Recoleta Underground Experience
            </h1>
            <div className="bottom-info">
              <h2>
                <span>ARS$</span> 24000
              </h2>
              <SecondaryButton text="Activa" fontColor="var(--color-active)"/>
            </div>
          </section>
        </div>
      </div>
    </Layout>
  );
};

export default ActiveOffers;
