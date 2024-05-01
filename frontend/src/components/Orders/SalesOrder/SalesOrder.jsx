import React, { useEffect, useState } from "react";
import TopBarMobile from "../../Layout/Mobile/TopBarMobile/TopBarMobile";
import { Link, NavLink } from "react-router-dom";
import "../orders.css";
import { MercadoPagoIcon } from "../../Icons/SocialMedia/MercadoPagoIcon";
import PrimaryButton from "./../../Buttons/PrimaryButton/PrimaryButton";
import { useAuth } from "../../Context/AuthProvider";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
const SalesOrder = () => {
  const navigate = useNavigate();
  const auth = useAuth();
  const idUser = auth.user && auth.user.profile.id;
  const [isLoading, setIsLoading] = useState(true);
  const [tickets, setTickets] = useState([]);
  const [selectedTicketId, setSelectedTicketId] = useState("");
  const [selectedTicket, setSelectedTicket] = useState(null);
  const [price, setPrice] = useState("");

  console.log("selectedTicket", selectedTicket);

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

  const handleSubmit = async (event) => {
    event.preventDefault();
    
    const data = {
      event: {
        id: selectedTicket.event.id,
        name: "",
        dateStart: "2024-05-01T14:52:33.669Z",
        dateEnd: "2024-05-01T14:52:33.669Z",
        address: "",
      },
      owner: {
        id: idUser,
      },
      price: price,
    };

    try {
      const response = await axios.post(
        "https://troca-prod-main.onrender.com/ticket/create/fast",
        data
      );
      if (response.status === 201) {
        navigate("/tickets/purchase");
        console.log("Orden de venta creada exitosamente.");
      } else {
        console.error("Error al crear la orden de venta.");
      }
    } catch (error) {
      console.error("Ocurrió un error inesperado.", error);
    }
  };

  const handleTicketChange = (event) => {
    const selectedId = event.target.value;
    console.log("selectedId", selectedId);
  
    setSelectedTicketId(selectedId);
    const ticket = tickets.find((ticket) => ticket.id === selectedId);
    setSelectedTicket(ticket);
  };
  const handlePriceChange = (event) => {
    setPrice(event.target.value);
  };
  
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
            <form onSubmit={handleSubmit}>
              <label htmlFor="eventName">Entrada</label>
              <select
                className="accordion-content"
                onChange={handleTicketChange}
              >
                <option value="">Seleccione un ticket de su wallet</option>
                {tickets.map((ticket) => (
                  <option key={ticket.id} value={ticket.id}>
                    {ticket.event.name}
                  </option>
                ))}
              </select>
              <label htmlFor="price">Precio</label>
              <input
                type="number"
                id="price"
                name="price"
                placeholder="ARS$ 0"
                value={price}
                onChange={handlePriceChange}
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
                  más segura y rápida. En caso de querer cancelar su orden de
                  venta, puede administrarlo desde el apartado “Mis ordenes” y
                  eliminar la orden de venta. Su entrada estará disponible en su
                  wallet.
                </p>
              </div>
              <PrimaryButton
                type="submit"
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
