import React from "react";
import { NavLink, useLocation } from "react-router-dom";
import "./floatingNavbarMobile.css";
import { UsersIcon } from "../../../Icons/Basic/UsersIcon";
import { ClipboardIcon } from "../../../Icons/Basic/ClipboardIcon";
import { CommentIcon } from "../../../Icons/Basic/CommentIcon";
import { UserProfileIcon } from "../../../Icons/Basic/UserProfileIcon";

const FloatingNavbarMobile = () => {
  const location = useLocation();
  const isP2PActive = location.pathname.startsWith('/tickets/purchase') || location.pathname.startsWith('/tickets/sale');
  const isOffersActive = location.pathname.startsWith('/offers/active') || location.pathname.startsWith('/offers/completed');
  return (
    <nav className="mobile-ticket-navigator">
      <ul>
        <li>
          <NavLink to="/tickets/purchase" className={isP2PActive ? 'active-link' : ''}>
            <UsersIcon width="24px" height="24px" />
            <span>P2P</span>
          </NavLink>
        </li>
        <li>
          <NavLink to="/offers/active" className={isOffersActive ? 'active-link' : ''}>
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

export default FloatingNavbarMobile;
