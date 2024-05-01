import React, { useEffect, useState } from "react";
import TopBarMobile from "../../Layout/Mobile/TopBarMobile/TopBarMobile";
import { Link, NavLink } from "react-router-dom";
import "../orders.css";
import { MercadoPagoIcon } from "../../Icons/SocialMedia/MercadoPagoIcon";
import PrimaryButton from "./../../Buttons/PrimaryButton/PrimaryButton";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import CompactTicketView from "../../Tickets/CompactTicketView/CompactTicketView";
import Party2 from "../../../assets/images/Parties/party2.jpeg";
import { AngleDownIcon } from "./../../Icons/Basic/AngleDownIcon";
import { useAuth } from "../../Context/AuthProvider";
import axios from "axios";
const SalesOrder = () => {
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
      console.error("Error al obtener los tickets:", error);
    }
  };
  useEffect(() => {
    getTickets();
    setIsLoading(true);
  }, [idUser]);

  return (
    <>
      <TopBarMobile title="Crea una oferta" linkTo="/offers/active" />
      <div className="order-container">
        <section className="order-content">
          <div className="top">
            <small>Quiero</small>
            <ul>
              <li>
                <NavLink to="/order/purchase" className="purchase-order">
                  Comprar
                </NavLink>
              </li>
              <li>
                <NavLink to="/order/sales" className="sales-order">
                  Vender
                </NavLink>
              </li>
            </ul>
            <h2>Detalles de su orden</h2>
            <form>
              <label htmlFor="eventName">Entrada</label>
              <select className="accordion-content">
                <option value="selected">
                  {" "}
                  Seleccione un ticket de su wallet
                </option>
                {tickets.map((ticket) => (
                  <option key={ticket.id} value={ticket.id}>
                    {ticket.event.name} - {ticket.eventDate}
                  </option>
                ))}
                
              </select>
              <label htmlFor="price">Precio</label>
              <input
                type="number"
                id="price"
                name="price"
                placeholder="ARS$ 0"
              />
              <div className="payment-method">
                <h3>Método de cobro</h3>
                <div>
                  <MercadoPagoIcon width="14px" height="14px" />
                  <span>Mercado Pago</span>
                </div>
              </div>
              <div className="payment-info">
                <h4>Recordatorio</h4>
                <p>
                  Al crear una orden de venta, su entrada será retenida por la
                  plataforma para proporcionar una experiencia de compra y venta
                  mas segura y rápida. En caso de querer cancelar su orden de
                  venta, puede administrarlo desde el apartado “Mis ordenes” y
                  eliminar la orden de venta. Su entrada estará disponible en su
                  wallet.
                </p>
              </div>
              <PrimaryButton
                type={"submit"}
                backColor="var(--color-red)"
                text="Crear orden de venta"
              />
            </form>
          </div>
        </section>
      </div>
    </>
  );
};

export default SalesOrder;
