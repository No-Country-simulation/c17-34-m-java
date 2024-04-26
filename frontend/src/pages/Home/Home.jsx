import React, { useState, useEffect } from "react";
import "./home.css";
import Events from "../../components/Events/Events";
import Search from "../../components/Search/Search";
import MainNavbarMobile from "../../components/Layout/Mobile/MainNavbarMobile/MainNavbarMobile";
import TopBarMobile from "../../components/Layout/Mobile/TopBarMobile/TopBarMobile";
import Profile1 from "../../assets/images/Profile/profile1.jpg";
import Layout from "../../components/Layout/Desktop/Layout";
import Hero from "../../components/Hero/Hero";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../components/Context/AuthProvider";
const Home = () => {
  const auth = useAuth();
  const navigate = useNavigate();
  const isMobile = window.matchMedia("(max-width: 768px)").matches;
  useEffect(() => {
    if (isMobile && auth.user === null) {
      navigate("/login");
    }
  }, [isMobile, navigate]);
  return (
    <>
      <Layout>
        <Hero />
        <TopBarMobile>
          <img
            src={Profile1}
            width="50px"
            height="50px"
            style={{ borderRadius: "50%" }}
          />
        </TopBarMobile>
        <Search />
        <Events />
        <MainNavbarMobile />
      </Layout>
    </>
  );
};
export default Home;
