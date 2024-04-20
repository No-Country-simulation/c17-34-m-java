import React from "react";
import "./userProfileView.css";
import { Link } from "react-router-dom";
import { SettingsIcon } from "./../../Icons/Basic/SettingsIcon";
import { CheckIcon } from "./../../Icons/Basic/CheckIcon";
import { MercadoPagoIcon } from "./../../Icons/SocialMedia/MercadoPagoIcon";
import Profile1 from "../../../assets/images/Profile/profile1.jpg";
import UserRating from "../UserRating/UserRating";
import TopBarMobile from "../../Layout/Mobile/TopBarMobile/TopBarMobile";
import SecondaryMobileButton from "./../../Buttons/mobile/SecondaryMobileButton";
const UserProfileView = () => {
  return (
    <>
      <TopBarMobile linkTo="/tickets/purchase">
        <Link to="/">
          <SettingsIcon width="20px" height="20px" />
        </Link>
      </TopBarMobile>
      <div className="user-profile-view container">
        <div className="user-profile-view content">
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
            <div className="edit-profile">
              <Link to="/user/profile/edit">
                <SecondaryMobileButton>Editar</SecondaryMobileButton>
              </Link>
            </div>
          </div>
        </div>
      </div>
      <UserRating />
    </>
  );
};

export default UserProfileView;
