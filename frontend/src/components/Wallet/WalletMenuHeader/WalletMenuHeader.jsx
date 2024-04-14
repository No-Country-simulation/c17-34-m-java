import React from "react";
import { Link, NavLink} from "react-router-dom";
import Profile1 from "../../../assets/images/Profile/profile1.jpg";
import { AngleLeftIcon } from "./../../Icons/Basic/AngleLeftIcon";
import "./walletMenuHeader.css"
const WalletMenuHeader = () => {
  return (
    <>
      <div className="wallet-menu-header">
        <ul className="wallet-header-content">
          <li>
            <Link to="/">
              <AngleLeftIcon width="20px" height="20px" />
            </Link>
          </li>
          <li>
            <h1>Tus entradas</h1>
          </li>
          <li>
            <img src={Profile1} width="50px" height="50px" />
          </li>
        </ul>
        <div className="wallet-bottom-content">
            <NavLink to="/wallet/upcoming" >Próximas</NavLink>
            <NavLink to="/wallet/completed">Finalizadas</NavLink>
        </div>
      </div>
    </>
  );
};

export default WalletMenuHeader;
