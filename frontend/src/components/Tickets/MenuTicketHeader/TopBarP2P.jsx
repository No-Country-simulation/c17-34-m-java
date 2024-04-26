import React from "react";
import { Link } from "react-router-dom";
import { AngleLeftIcon } from "../../Icons/Basic/AngleLeftIcon";
import "./topBarP2P.css";
import P2PImage from "../../../assets/images/P2P/p2p.png";
import TrocaLogo from './../../Icons/TrocaLogo/TrocaLogo';
const TopBarP2P = () => {
  return (
    <div className="top-bar-p2p mobile-only">
      <div className="content">
        <div className="top">
          <Link to="/">
            <AngleLeftIcon
              width="20px"
              height="20px"
              className="angle-left-icon"
            />
          </Link>
        </div>
        <div className="bottom">
          <img src={P2PImage} />
          <div className="info">
            <div className="troca-logo">
              <TrocaLogo/>
            </div>
            <h1>Compra y vende tus entradas donde quieras</h1>
          </div>
        </div>
      </div>
    </div>
  );
};

export default TopBarP2P;
