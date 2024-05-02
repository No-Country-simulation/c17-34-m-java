import React from "react";
import "./userProfileView.css";
import { Link } from "react-router-dom";
import { SettingsIcon } from "./../../Icons/Basic/SettingsIcon";
import { CheckIcon } from "./../../Icons/Basic/CheckIcon";
import { MercadoPagoIcon } from "./../../Icons/SocialMedia/MercadoPagoIcon";
import Profile1 from "../../../assets/images/Profile/profile1.jpg";
import UserRating from "../UserRating/UserRating";
import TopBarMobile from "../../Layout/Mobile/TopBarMobile/TopBarMobile";
import SecondaryButton from "./../../Buttons/SecondaryButton/SecondaryButton";
import Layout from "./../../Layout/Desktop/Layout";
import FloatingNavbarMobile from "./../../Layout/Mobile/FloatingNavbarMobile/FloatingNavbarMobile";
import { UserAuthMP } from "../AuthMP/UserAuthMP";
import { useAuth } from "../../Context/AuthProvider";
const UserProfileView = () => {
  const auth = useAuth();
  const user = auth.user;
  return (
    <Layout>
      <div className="desktop-only">
        <FloatingNavbarMobile />
      </div>
      <TopBarMobile linkTo="/tickets/purchase">
        <Link to="/">
          <SettingsIcon width="20px" height="20px" />
        </Link>
      </TopBarMobile>
      <div className="user-profile-view container">
        <div className="user-profile-view content">
          <div className="user-profile-view content-bottom">
            <img src={Profile1} width="80px" height="80px" />
            <h1>{user.profile.name} {user.profile.lastname}</h1>
            <div className="user-info">
              <div className="email-info">
                <CheckIcon width="14px" height="14px" />
                <span>{user.email}</span>
              </div>
            </div>
            <div className="mp-info">
              <UserAuthMP />
            </div>
            <div className="edit-profile">
              {/* <Link to="/user/profile/edit">
                <SecondaryButton text="Editar" />
              </Link> */}
            </div>
          </div>
        </div>
      </div>
      <UserRating />
    </Layout>
  );
};

export default UserProfileView;
