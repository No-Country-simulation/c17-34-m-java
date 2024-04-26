import React from "react";
import TopBarMobile from "../../Layout/Mobile/TopBarMobile/TopBarMobile";
import { NavLink } from "react-router-dom";
import "../orders.css";
import { MercadoPagoIcon } from "../../Icons/SocialMedia/MercadoPagoIcon";
import PrimaryButton from "../../Buttons/PrimaryButton/PrimaryButton";
const PurchaseOrder = () => {
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
                placeholder="Nombre del evento"
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
              <h3>Método de pago</h3>
              <div>
                <MercadoPagoIcon width="14px" height="14px" />
                <span>Mercado Pago</span>
              </div>
            </div>
            <div className="payment-info">
              <h4>Recordatorio</h4>
              <p>
                Al crear una orden de compra, se le proporcionara un link de
                pago. Para confirmar la orden, debe pagar el monto definido por
                usted; para una transacción segura y rápida. En caso de querer
                cancelar la orden de compra, su dinero será reembolsado en su
                cuenta.
              </p>
            </div>
            <div className="total-price">
              <h5>Total a pagar</h5>
              <p>
                <span>ARS$ </span>27,500
              </p>
            </div>
          </div>

          <div className="bottom">
            <h6>Será redirigido a Mercado Pago para finalizar la orden.</h6>
            <PrimaryButton backColor="var(--color-green)" text=" Crear orden de compra"/>
          </div>
        </section>
      </div>
    </>
  );
};

export default PurchaseOrder;
