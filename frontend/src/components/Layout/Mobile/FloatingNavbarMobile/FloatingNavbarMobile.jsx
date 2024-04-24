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
  const isProfileActive = location.pathname.startsWith('/user/profile');
  return (
    <nav className="p2p-navbar">
      <ul>
        <li>
          <NavLink to="/tickets/purchase" className={isP2PActive ? 'active-link' : ''}>
            <div className="icon">
              <UsersIcon width="24px" height="24px" />
            </div>
            <span>P2P</span>
          </NavLink>
        </li>
        <li>
          <NavLink to="/offers/active" className={isOffersActive ? 'active-link' : ''}>
            <div className="icon">
            <ClipboardIcon width="24px" height="24px" />
            </div>
            <span>Mis Ofertas</span>
          </NavLink>
        </li>
        <li>
          <NavLink to="/" disabled>
            <div className="icon">
            <CommentIcon width="24px" height="24px" />
            </div>
            <span>Chats</span>
          </NavLink>
        </li>
        <li>
          <NavLink to="/user/profile" className={isProfileActive ? 'active-link' : ''}>
            <div className="icon">
            <UserProfileIcon width="24px" height="24px" />
            </div>
            <span>Perfil</span>
          </NavLink>
        </li>
      </ul>
    </nav>
  );
};

export default FloatingNavbarMobile;
