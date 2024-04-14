import React from "react";
import "./home.css";
import Events from "../../components/Events/Events";
import Search from "../../components/Search/Search";
import MainMobileNavbar from "./../../components/Layout/Mobile/MainNavbar/MainMobileNavbar";
const Home = () => {
  return (
    <>
      <Search />
      <Events />
      <MainMobileNavbar />
    </>
  );
};
export default Home;
