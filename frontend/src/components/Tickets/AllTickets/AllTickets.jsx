import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "../tickets.css";
import { Link, NavLink } from "react-router-dom";
import FloatingNavbarMobile from "./../../Layout/Mobile/FloatingNavbarMobile/FloatingNavbarMobile";
import Layout from "./../../Layout/Desktop/Layout";
import SecondaryButton from "../../Buttons/SecondaryButton/SecondaryButton";
import TopBarP2P from "../MenuTicketHeader/TopBarP2P";
import axios from "axios";
import { Ticket } from "../Ticket/Ticket";
import { initMercadoPago } from "@mercadopago/sdk-react";
import SpinnerLoader from "../../SpinnerLoader/SpinnerLoader";
import { PlusIcon } from "../../Icons/Basic/PlusIcon";
initMercadoPago(import.meta.env.VITE_PUBLICKEY, { locale: "es-AR" });
const AllTickets = () => {
 
  const [isLoading, setIsLoading] = useState(true);
  const [tickets, setTickets] = useState([]);
  const getTickets = async () => {
    try {
      const response = await axios.get(
        `https://troca-prod-main.onrender.com/ticket/all`,
        {
          params: {
            onService: true,
          }
        }
      );
      {
       
      }
      setTickets(response.data.data);
      setIsLoading(false);
    } catch (error) {
      console.error("Error al obtener los tickets:", error);
    }
  };
  useEffect(() => {
    getTickets();
    setIsLoading(true);
  }, []);
  return (
    <Layout>
      <div className="background-ticket-navigator">
        <FloatingNavbarMobile />
      </div>
      <TopBarP2P />
      <div className="sale-purchase-navbar">
        <div className="content">
          <ul>
            <li>
              <NavLink to="/tickets/purchase">Compra</NavLink>
            </li>
            <li>
              <NavLink to="/tickets/sale">Venta</NavLink>
            </li>
            <li className="mobile-only">
              <Link to="/ticket/add">
                <PlusIcon width="24px" height="24px" />
              </Link>
            </li>
          </ul>
          <div className="desktop-only">
            <Link to="/ticket/add">
              <SecondaryButton text="Crear Oferta" />
            </Link>
          </div>
        </div>
      </div>
      <>
        {isLoading ? (
          <SpinnerLoader />
        ) : (
          <div className="tickets-container">
            <div className="tickets">
              <table>
                <thead>
                  <tr className="desktop-only">
                    <th>Vendedor</th>
                    <th>Evento</th>
                    <th>Precio</th>
                    <th>Método de pago</th>
                    <th>Compra / Venta</th>
                  </tr>
                </thead>
                <tbody>
                  {
                    tickets ? (
                      tickets.map((ticket) => (
                        <tr key={ticket.id}>
                          <Ticket ticket={ticket} />
                        </tr>
                      ))
                    )
                    :(
                      <div>
                        No hay tickets disponibles
                      </div>
                    )
                  }
                </tbody>
              </table>
            </div>
          </div>
        )}
      </>
    </Layout>
  );
};

export default AllTickets;
