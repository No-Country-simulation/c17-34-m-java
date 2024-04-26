import { useContext, createContext, useState } from "react";
import axios from "axios";

const AuthContext = createContext();

const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(JSON.parse(localStorage.getItem("troca")) || null);
  const loginAction = async (user) => {
    try {
      const response = await axios.post(
        `https://troca-prod-main.onrender.com/user/login?email=${user.email}&password=${user.password}`
      );
      const {user: userData}  = response.data;
      if (userData) {
        setUser(userData);
        localStorage.setItem("troca", JSON.stringify(userData));
        return;
      }
      throw new Error(res.message);
    } catch (error) {
      throw error;
    }
  };


  return (
    <AuthContext.Provider value={{ user, setUser, loginAction}}>
      {children}
    </AuthContext.Provider>
  );
};
export default AuthProvider;
export const useAuth = () => {
  return useContext(AuthContext);
};
