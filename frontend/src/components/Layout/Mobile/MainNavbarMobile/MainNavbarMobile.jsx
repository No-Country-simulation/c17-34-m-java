import React from "react";
import { HomeIcon } from "../../../Icons/Basic/HomeIcon";
import { NavLink, useLocation } from "react-router-dom";
import { WalletIcon } from "../../../Icons/Basic/WalletIcon";
import "./mainNavbarMobile.css";
import { UsersIcon } from "../../../Icons/Basic/UsersIcon";
const MainNavbarMobile = () => {
  const location = useLocation();
  const isActiveWallet =
    location.pathname.startsWith("/wallet/upcoming") ||
    location.pathname.startsWith("/wallet/completed");
  return (
    <div className="mobile-navbar-container">
      <div className="mobile-header-content">
        <div>
          <NavLink to="/tickets/purchase" className="p2p-link">
            <UsersIcon />
          </NavLink>
        </div>
      </div>
      <ul className="mobile-main-content">
        <li>
          <NavLink to="/" >
            <HomeIcon />
            <span>Home</span>
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/wallet/upcoming"
            className={isActiveWallet ? "active-link-wallet" : ""}
          >
            <WalletIcon />
            <span>Wallet</span>
          </NavLink>
        </li>
      </ul>
    </div>
  );
};

export default MainNavbarMobile;
