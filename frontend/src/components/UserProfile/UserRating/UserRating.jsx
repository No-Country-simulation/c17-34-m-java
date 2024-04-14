import React from "react";
import "./userRating.css";
import { ThumbsUpIcon } from "./../../Icons/Basic/ThumbsUpIcon";
import { ThumbsDownIcon } from "./../../Icons/Basic/ThumbsDownIcon";
const UserRating = () => {
  const rating1 = 60;
  const rating2 = 40;
  return (
    <div className="user-rating-container">
      <div className="user-rating">
        <h2>Rating</h2>
        <div className="rating-bars">
          <ThumbsUpIcon width="22px" height="22px" className="thumbs-up"/>
          <div className="positive-rating" style={{ width: `${rating1}%` }}>
            <p>{rating1}%</p>
          </div>
          <div className="negative-rating" style={{ width: `${rating2}%` }}>
            <p>{rating2}%</p>
          </div>
          <ThumbsDownIcon width="22px" height="22px" className="thumbs-down"/>
        </div>
      </div>
    </div>
  );
};

export default UserRating;
