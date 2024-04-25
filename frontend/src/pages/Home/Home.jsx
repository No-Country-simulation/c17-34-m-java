import React, { useState, useEffect } from 'react';
import "./home.css";
import Events from "../../components/Events/Events";
import Search from "../../components/Search/Search";
import MainNavbarMobile from "../../components/Layout/Mobile/MainNavbarMobile/MainNavbarMobile";
import TopBarMobile from "../../components/Layout/Mobile/TopBarMobile/TopBarMobile";
import Profile1 from "../../assets/images/Profile/profile1.jpg";
import Layout from "../../components/Layout/Desktop/Layout";
import Hero from '../../components/Hero/Hero';
const Home = () => {
  const [isMobile, setIsMobile] = useState(false);

  useEffect(() => {
    const handleResize = () => {
      setIsMobile(window.innerWidth <= 480);
    };
    window.addEventListener('resize', handleResize);
    return () => {
      window.removeEventListener('resize', handleResize);
    };
  }, []);
  return (
    <>
      <Layout>
        <Hero/>
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