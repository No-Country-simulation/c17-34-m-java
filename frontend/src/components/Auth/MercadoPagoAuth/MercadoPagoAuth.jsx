import React from "react";
import { initMercadoPago, Wallet } from "@mercadopago/sdk-react";
import { useEffect, useState } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import axios from "axios";

//simulacion del carrito de compras, en localstorage quizas
const profile = {
  id: 1,
  name: "Lalo",
  email: "test_user_435626719@testuser.com",
};

initMercadoPago(import.meta.env.VITE_PUBLICKEY, { locale: "es-AR" });

const urlBack = import.meta.env.VITE_URLBACK;
const MercadoPagoAuth = () => {
  const [tokenUrl, setTokenUrl] = useState(null);
  // obtenecion del codigo de autorizacion de mercado pago
  const location = useLocation();
  const navigate = useNavigate();

  const queryParams = new URLSearchParams(location.search);
  const paramCode = queryParams.get("code"); //code cuando se pide authorizacion al  vendedor para MP
  const paramState = queryParams.get("state"); //randomID cuando se pide authorizacion al  vendedor para MP

  useEffect(() => {
    if (paramCode && paramState) {
      createTokenAccess();
    }
  }, [paramCode, paramState]);

  const createTokenAccess = async () => {
    try {
      await axios.get(
        `${urlBack}/mp/oauth?code=${paramCode}&state=${paramState}`
      );
      navigate("/home");
    } catch (error) {
      console.log(error);
    }
  };

  const handlerToken = async () => {
    try {
      let response = await axios.post(
        `${urlBack}/profile/${profile.id}/auth-mp`
      );
      window.location.href= response.data.mp_url
      setTokenUrl(response.data.mp_url);
    } catch (error) {
      console.log(error);
    }
  };
  return <button onClick={handlerToken}>Vincular cuenta</button>;
};

export default MercadoPagoAuth;
