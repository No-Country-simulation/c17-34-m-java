import React from "react";
import Profile1 from "../../../assets/images/Profile/profile1.jpg";
import { NavLink, useParams } from "react-router-dom";
import TopBarMobile from "../../Layout/Mobile/TopBarMobile/TopBarMobile";
import "../wallet.css";
import CompactTicketView from "../../Tickets/CompactTicketView/CompactTicketView";
import MainNavbarMobile from "./../../Layout/Mobile/MainNavbarMobile/MainNavbarMobile";
import Party2 from "../../../assets/images/Parties/party2.jpeg";
import { useAuth } from "../../Context/AuthProvider";
const CompletedTickets = () => {
  const auth = useAuth();
  const idUser = auth.user && auth.user.id;
  return (
    <>
      <TopBarMobile linkTo="/" title="Tus entradas">
        <img
          src={Profile1}
          width="50px"
          height="50px"
          style={{ borderRadius: "50%" }}
        />
      </TopBarMobile>
      <div>
        <ul className="wallet-navbar">
          <NavLink  to={`/wallet/upcoming/${idUser}`}>Próximas</NavLink>
          <NavLink  to={`/wallet/completed/${idUser}`}>Finalizadas</NavLink>
        </ul>
      </div>
      <CompactTicketView
        img={Party2}
        eventDate="06/04 Vision Press.:"
        eventName="Juan Hansen, Transformation ..."
      />
      <MainNavbarMobile />
    </>
  );
};

export default CompletedTickets;
