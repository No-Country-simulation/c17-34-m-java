import React from "react";
import {NavLink } from "react-router-dom";
import TopBarMobile from "../../Layout/Mobile/TopBarMobile/TopBarMobile";
import "../wallet.css";
import CompactTicketView from "../../Tickets/CompactTicketView/CompactTicketView";
import MainNavbarMobile from "./../../Layout/Mobile/MainNavbarMobile/MainNavbarMobile";
import Party2 from "../../../assets/images/Parties/party2.jpeg";
import { useAuth } from "../../Context/AuthProvider";
import Dropdown from "../../Layout/Mobile/Dropdown/Dropdown";
import Layout from "../../Layout/Desktop/Layout";
const CompletedTickets = () => {
  const auth = useAuth();
  const idUser = auth.user && auth.user.profile.id;
  return (
    <Layout>
      <TopBarMobile linkTo="/" title="Tus entradas">
        <Dropdown />
      </TopBarMobile>
      <ul className="wallet-navbar">
        <NavLink to={`/wallet/upcoming/${idUser}`}>Próximas</NavLink>
        <NavLink to={`/wallet/completed/${idUser}`}>Finalizadas</NavLink>
      </ul>
      <div className="wallet-container">
        <div className="wallet-tickets">
          <CompactTicketView
            img={Party2}
            eventDate="06/04 Vision Press.:"
            eventName="Juan Hansen, Transformation ..."
          />
        </div>
      </div>
      <MainNavbarMobile />
    </Layout>
  );
};

export default CompletedTickets;
