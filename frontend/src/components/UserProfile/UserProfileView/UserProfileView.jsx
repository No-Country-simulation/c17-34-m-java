import React from "react";
import "./userProfileView.css";
import { Link } from "react-router-dom";
import { AngleLeftIcon } from "./../../Icons/Basic/AngleLeftIcon";
import { SettingsIcon } from "./../../Icons/Basic/SettingsIcon";
import { CheckIcon } from "./../../Icons/Basic/CheckIcon";
import { MercadoPagoIcon } from "./../../Icons/SocialMedia/MercadoPagoIcon";
import Profile1 from "../../../assets/images/Profile/profile1.jpg";
import UserRating from "../UserRating/UserRating";
const UserProfileView = () => {
  return (
    <>
      <div className="user-profile-view container">
        <div className="user-profile-view content">
          <div className="user-profile-view content-header">
            <Link to="/">
              <AngleLeftIcon width="20px" height="20px" />
            </Link>
            <Link to="/">
              <SettingsIcon width="20px" height="20px" />
            </Link>
          </div>
          <div className="user-profile-view content-bottom">
            <img src={Profile1} width="80px" height="80px" />
            <h1>UserName</h1>
            <div className="user-info">
              <div className="email-info">
                <CheckIcon width="14px" height="14px" />
                <span>example@email.com</span>
              </div>
              <div className="mp-info">
                <MercadoPagoIcon width="14px" height="14px" />
                <span>User Name</span>
              </div>
            </div>
            <Link to="/user/profile/edit">Editar</Link>
          </div>
        </div>
      </div>
      <UserRating/>
    </>
  );
};

export default UserProfileView;
