import React from "react";
import { NavLink, useLocation } from "react-router-dom";
import "./ticketNavigator.css";
import { UsersIcon } from "../../../Icons/Basic/UsersIcon";
import { ClipboardIcon } from "./../../../Icons/Basic/ClipboardIcon";
import { CommentIcon } from "./../../../Icons/Basic/CommentIcon";
import { UserProfileIcon } from "../../../Icons/Basic/UserProfileIcon";

const TicketNavigator = () => {
  const location = useLocation();
  const isActive = location.pathname.startsWith('/tickets/purchase') || location.pathname.startsWith('/tickets/sale');
  return (
    <nav className="mobile-ticket-navigator">
      <ul>
        <li>
          <NavLink to="/tickets/purchase" className={isActive ? 'active-link-p2p' : ''}>
            <UsersIcon width="24px" height="24px" />
            <span>P2P</span>
          </NavLink>
        </li>
        <li>
          <NavLink to="/">
            <ClipboardIcon width="24px" height="24px" />
            <span>Tus Ofertas</span>
          </NavLink>
        </li>
        <li>
          <NavLink to="/" disabled>
            <CommentIcon width="24px" height="24px" />
            <span>Chats</span>
          </NavLink>
        </li>
        <li>
          <NavLink to="/user/profile">
            <UserProfileIcon width="24px" height="24px" />
            <span>Perfil</span>
          </NavLink>
        </li>
      </ul>
    </nav>
  );
};

export default TicketNavigator;
