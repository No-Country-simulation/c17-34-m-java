import React from 'react'
import { Link, NavLink } from "react-router-dom";
import { AngleLeftIcon } from "./../../Icons/Basic/AngleLeftIcon";
import "./menuTicketHeader.css"
const MenuTicketHeader = () => {
  return (
    <>
      <div className="ticket-trade-header">
        <div>
          <Link to="/">
            <AngleLeftIcon
              width="20px"
              height="20px"
              className="angle-left-icon"
            />
          </Link>
        </div>
        <h1>Tips Generales</h1>
      </div>
      <div className="sale-purchase-menu">
        <ul>
          <li>
            <NavLink to="/tickets/purchase">Compra</NavLink>
          </li>
          <li>
            <NavLink to="/tickets/sale">Venta</NavLink>
          </li>
        </ul>
      </div>
    </>
  )
}

export default MenuTicketHeader
