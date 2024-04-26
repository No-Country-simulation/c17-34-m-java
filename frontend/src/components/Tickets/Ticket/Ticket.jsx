import { useLocation} from "react-router-dom";
import SecondaryButton from "../../Buttons/SecondaryButton/SecondaryButton";
import { ThumbsUpIcon } from "../../Icons/Basic/ThumbsUpIcon";
import { MercadoPagoIcon } from "../../Icons/SocialMedia/MercadoPagoIcon";
import axios from "axios";
import { useEffect } from "react";
import "../tickets.css";


const urlBack = import.meta.env.VITE_URLBACK;

const profile = {
  // MOCK DATA profile del comprador
  id: 1,
  name: "Lalo",
  email: "test_user_435626719@testuser.com",
};

export const Ticket = ({ ticket }) => {
  const { id, owner, event, price, moneda } = ticket;

  const location = useLocation();

  const queryParams = new URLSearchParams(location.search);
  const status = queryParams.get("status");

  useEffect(() => {
    if (status == "approved") {
      console.log("Compra exitosa!");
      alert("Compra exitosa!");
    }
  }, [status]);

  const createPreference = async () => {
    const newArray = {
      id: id,
      unit_price: price,
      quantity: 1,
    };
    const body = {
      items: newArray,
      payer: profile,
    };
    try {
      let response = await axios.post(
        `${urlBack}/shop/create-preference`,
        body
      );
      return response.data;
    } catch (error) {
      console.log(error);
    }
  };

  const handleBuy = async () => {
    const mp_url = await createPreference();
    if (mp_url) {
      window.location.href = mp_url;
    }
  };

  return (
    <>
      <td className="seller-info">
        <p>{owner.name}</p>
        <div>
          <span className="mobile-only">-</span>
          <ThumbsUpIcon width="10px" height="10px" className="icon" />
          <span>99.5%</span>
        </div>
      </td>
      <td className="event-name">{event.name}</td>
      <td className="price">
        <p>
          <span>{moneda}</span> {price}
        </p>
        <div className="mobile-only">
          <SecondaryButton
            onClick={handleBuy}
            text="Comprar"
            backColor="var(--color-green)"
          />
        </div>
      </td>
      <td className="payment desktop-only">
        <MercadoPagoIcon />
        Mercado Pago
      </td>
      <td className="desktop-only">
        <SecondaryButton
          onClick={handleBuy}
          text="Comprar"
          backColor="var(--color-green)"
        />
      </td>
    </>
  );
};
