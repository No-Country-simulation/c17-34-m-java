import React from "react";
import "./compactTicketView.css";
import { Link } from "react-router-dom";

const CompactTicketView = ({ img, eventDate, eventName }) => {
  return (
    <div className="compact-ticket-container">
      <section className="compact-ticket">
        <figure>
          <img src={img} width="80px" height="80px" />
        </figure>
        <div className="info">
          <h2>{eventDate}</h2>
          <h1>{eventName}</h1>
        </div>
      </section>
    </div>
  );
};

export default CompactTicketView;
