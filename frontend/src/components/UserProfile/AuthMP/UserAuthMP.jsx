import axios from "axios";
import SecondaryButton from "../../Buttons/SecondaryButton/SecondaryButton";
import { useEffect, useState } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { MercadoPagoIcon } from "../../Icons/SocialMedia/MercadoPagoIcon";
import { initMercadoPago } from "@mercadopago/sdk-react";

initMercadoPago(import.meta.env.VITE_PUBLICKEY, { locale: "es-AR" });
const urlBack = import.meta.env.VITE_URLBACK;

export const UserAuthMP = () => {
  const [profileData, setProfileData] = useState(null);
  const [tokenUrl, setTokenUrl] = useState(null);
  // obtenecion del codigo de autorizacion de mercado pago
  const location = useLocation();
  const navigate = useNavigate();
  console.log(profileData);

  const queryParams = new URLSearchParams(location.search);
  const paramCode = queryParams.get("code"); //code cuando se pide authorizacion al  vendedor para MP
  const paramState = queryParams.get("state"); //randomID cuando se pide authorizacion al  vendedor para MP

  useEffect(() => {
    const { profile } = JSON.parse(localStorage.getItem("troca"));
    const { email } = JSON.parse(localStorage.getItem("troca"));
    const { id, name } = profile;
    if (id) {
      setProfileData({ id, name, email });
    }

    if (paramCode && paramState) {
      createTokenAccess();
    }
  }, [paramCode, paramState]);

  useEffect(() => {
    if (tokenUrl) window.location.href = tokenUrl;
  }, [tokenUrl]);

  const createTokenAccess = async () => {
    try {
      await axios.get(
        `${urlBack}/mp/oauth?code=${paramCode}&state=${paramState}`
      );
      navigate("/");
    } catch (error) {
      console.log(error);
    }
  };

  const handlerToken = async () => {
    try {
      let response = await axios.post(
        `${urlBack}/profile/${profileData.id}/auth-mp`
      );
      setTokenUrl(response.data.mp_url);
    } catch (error) {
      console.log(error);
    }
  };
  return (
    <>
      <SecondaryButton
        text={
          <div>
            {" "}
            Autorizar <MercadoPagoIcon width="20px" height="20px" />
          </div>
        }
        onClick={handlerToken}
      />
    </>
  );
};
