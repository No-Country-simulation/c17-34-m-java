import React from "react";
import TopBarMobile from "../../Layout/Mobile/TopBarMobile/TopBarMobile";
import { NavLink } from "react-router-dom";
import "../orders.css";
import { MercadoPagoIcon } from "../../Icons/SocialMedia/MercadoPagoIcon";
import PrimaryMobileButton from "./../../Buttons/mobile/PrimaryMobileButton";
const SalesOrder = () => {
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
              <label htmlFor="eventName">Precio unitario</label>
              <input
                type="text"
                id="eventName"
                name="eventName"
                placeholder="Seleccione un evento de su wallet"
              />
              <label htmlFor="price">Evento</label>
              <input
                type="number"
                id="price"
                name="price"
                placeholder="ARS$ 0"
              />
            </form>
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
          </div>

          <div className="bottom">
            <PrimaryMobileButton backColor="var(--color-red)">
              Crear orden de venta
            </PrimaryMobileButton>
          </div>
        </section>
      </div>
    </>
  );
};

export default SalesOrder;
