import React from "react";
import "./userRating.css";
import { ThumbsUpIcon } from "./../../Icons/Basic/ThumbsUpIcon";
import { ThumbsDownIcon } from "./../../Icons/Basic/ThumbsDownIcon";
const UserRating = () => {
  const initalValue = 25;
  const positiveRating = 80;
  const negativeRating = 20;
  const rating1 = initalValue + positiveRating;
  const rating2 = initalValue + negativeRating;
  return (
    <div className="user-rating-container">
      <div className="user-rating">
        <h2>Rating</h2>
        <div className="rating-bars">
          <ThumbsUpIcon width="22px" height="22px" className="thumbs-up"/>
          <div className="positive-rating" style={{ width: `${rating1}%` }}>
            <p>{positiveRating}%</p>
          </div>
          <div className="negative-rating" style={{ width: `${rating2}%` }}>
            <p>{negativeRating}%</p>
          </div>
          <ThumbsDownIcon width="22px" height="22px" className="thumbs-down"/>
        </div>
      </div>
    </div>
  );
};

export default UserRating;
