import React, { useEffect, useState } from "react";
import { Link, NavLink} from "react-router-dom";
import TopBarMobile from "../../Layout/Mobile/TopBarMobile/TopBarMobile";
import "../wallet.css";
import CompactTicketView from "../../Tickets/CompactTicketView/CompactTicketView";
import MainNavbarMobile from "./../../Layout/Mobile/MainNavbarMobile/MainNavbarMobile";
import Party1 from "../../../assets/images/Parties/party1.jpeg";
import Layout from "../../Layout/Desktop/Layout";
import axios from "axios";
import SpinnerLoader from "../../SpinnerLoader/SpinnerLoader";
import SecondaryButton from "../../Buttons/SecondaryButton/SecondaryButton";
import { useAuth } from "../../Context/AuthProvider";
import Dropdown from "../../Layout/Mobile/Dropdown/Dropdown";
const UpcomingTickets = () => {
  const auth = useAuth();
  const idUser = auth.user && auth.user.profile.id;
  const [isLoading, setIsLoading] = useState(true);
  const [tickets, setTickets] = useState([]);
  const getTickets = async () => {
    try {
      const response = await axios.get(
        `https://troca-prod-main.onrender.com/ticket/profile/${idUser}`
      );
      setTickets(response.data.data);
      setIsLoading(false);
    } catch (error) {
      setTickets([]);
      setIsLoading(false);
      console.error("Error al obtener los tickets:", error);
    }
  };
  useEffect(() => {
    getTickets();
    setIsLoading(true);
  }, [idUser]);
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
          {isLoading ? (
            <SpinnerLoader />
          ) : (
            <>
              {!tickets.length ? (
                <div className="empty-wallet-container">
                  <div className="empty-wallet">
                    <h1>Todavía no tenés entradas adquiridas</h1>
                    <Link to="/tickets/purchase">
                      <SecondaryButton text="Ver entradas" />
                    </Link>
                  </div>
                </div>
              ) : (
                <div className="wallet-ticket-list">
                  {tickets.map((ticket) => (
                    <Link to={`/ticket/${ticket.id}`}>
                      <CompactTicketView
                        img={ticket.event.img || Party1}
                        eventDate="30/05 Aldea ME Press.:"
                        eventName={ticket.event.name}
                      />
                    </Link>
                  ))}
                </div>
              )}
            </>
          )}
        </div>
      </div>
      <MainNavbarMobile />
    </Layout>
  );
};

export default UpcomingTickets;
