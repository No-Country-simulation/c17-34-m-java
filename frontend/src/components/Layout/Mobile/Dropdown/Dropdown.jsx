import React, { useState } from "react";
import "./dropdown.css"; // Asegúrate de crear este archivo CSS
import Profile1 from "../../../../assets/images/Profile/profile1.jpg";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../../../Context/AuthProvider";
import { toast } from "react-toastify";
import { UserProfileIcon2 } from './../../../Icons/Basic/UserProfileIcon2';
const Dropdown = () => {
  const auth = useAuth();
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(false);
  const toggleOpen = () => setIsOpen(!isOpen);
  const logout = () => {
    auth.setUser(null);
    localStorage.removeItem("troca");
    navigate("/login");
    toast.success(`¡Saliste de TROCA!`, {
      position: "top-center",
      autoClose: 2500,
      hideProgressBar: true,
      closeOnClick: false,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
      theme: "dark",
    });
  };
  return (
    <div className="dropdown-profile">
      <UserProfileIcon2 onClick={toggleOpen} />
      {isOpen && (
        <div className="dropdown-menu">
          <Link to="/user/profile">Mi perfil</Link>
          <div>
            <hr/>
            <button
              onClick={() => {
                logout();
                setIsOpen(false);
              }}
            >
              Cerrar sesión
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default Dropdown;
