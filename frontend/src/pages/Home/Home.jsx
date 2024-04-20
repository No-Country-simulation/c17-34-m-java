import React from "react";
import "./home.css";
import Events from "../../components/Events/Events";
import Search from "../../components/Search/Search";
import MainNavbarMobile from "../../components/Layout/Mobile/MainNavbarMobile/MainNavbarMobile";
import TopBarMobile from "../../components/Layout/Mobile/TopBarMobile/TopBarMobile";
import Profile1 from "../../assets/images/Profile/profile1.jpg"
import MercadoPagoAuth from "../../components/Auth/MercadoPagoAuth/MercadoPagoAuth";
const Home = () => {
  return (
    <>
      {/* <TopBarMobile>
        <img
        src={Profile1}
        width="50px"
        height="50px"
        style={{borderRadius : "50%"}}
        />
      </TopBarMobile> */}
      {/* <Search /> */}
      {/* <Events /> */}
      <MercadoPagoAuth/>
      {/* <MainNavbarMobile/> */}
    </>
  );
};
export default Home;
