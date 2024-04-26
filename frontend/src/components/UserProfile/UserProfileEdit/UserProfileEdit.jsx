import React from "react";
import "./userProfileEdit.css";
import TopBarMobile from "../../Layout/Mobile/TopBarMobile/TopBarMobile";
const UserProfileEdit = () => {
  return (
    <div>
      <TopBarMobile linkTo="/user/profile" />
      <h1>Editar perfil</h1>
    </div>
  );
};

export default UserProfileEdit;
