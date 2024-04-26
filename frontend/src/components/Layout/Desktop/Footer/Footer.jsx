import React from "react";
import "./footer.css";
import TrocaLogo from "../../../Icons/TrocaLogo/TrocaLogo";

const Footer = () => {
  return (
    <footer className="desktop-only">
      <div className="troca-logo">
        <TrocaLogo />
      </div>
    </footer>
  );
};

export default Footer;
