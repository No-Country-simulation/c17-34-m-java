import React from "react";
import Profile1 from "../../../assets/images/Profile/profile1.jpg";
import { NavLink } from "react-router-dom";
import TopBarMobile from "../../Layout/Mobile/TopBarMobile/TopBarMobile";
import "../wallet.css";
import CompactTicketView from "../../Tickets/CompactTicketView/CompactTicketView";
import MainNavbarMobile from "./../../Layout/Mobile/MainNavbarMobile/MainNavbarMobile";
import Party1 from "../../../assets/images/Parties/party1.jpeg";
import Layout from "../../Layout/Desktop/Layout";
const UpcomingTickets = () => {
  return (
      <Layout>
        <TopBarMobile linkTo="/" title="Tus entradas">
          <img
            src={Profile1}
            width="50px"
            height="50px"
            style={{ borderRadius: "50%" }}
          />
        </TopBarMobile>
        <ul className="wallet-navbar">
          <NavLink to="/wallet/upcoming">Próximas</NavLink>
          <NavLink to="/wallet/completed">Finalizadas</NavLink>
        </ul>
        <CompactTicketView
          img={Party1}
          eventDate="30/04 Aldea ME Press.:"
          eventName="Ben bohmer & Eelke kleijn"
        />
        <MainNavbarMobile />
      </Layout>
  );
};

export default UpcomingTickets;
