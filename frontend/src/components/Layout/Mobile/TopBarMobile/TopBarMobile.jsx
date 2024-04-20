import React from "react";
import { Link } from "react-router-dom";
import { AngleLeftIcon } from "../../../Icons/Basic/AngleLeftIcon";
import TrocaLogo from "../../../Icons/TrocaLogo/TrocaLogo";
import "./topBarMobile.css"
const TopBarMobile = ({ children, linkTo, title }) => {
  const styles = {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    padding: "8.205vw 6.923vw 0",
    listStyleType: "none",
  };
  return (
    <ul style={styles}>
      {linkTo ? (
        <li>
          <Link to={linkTo}>
            <AngleLeftIcon width="20px" height="20px" />
          </Link>
        </li>
      ) : (
        <li className="troca-logo-mobile">
          <TrocaLogo />
        </li>
      )}
      <li>
        <h1 style={{ fontSize: "5.128vw", letterSpacing: "-0.105vw" }}>
          {title}
        </h1>
      </li>
      <li>{children}</li>
    </ul>
  );
};

export default TopBarMobile;
