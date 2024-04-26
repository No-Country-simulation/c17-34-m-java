import "./App.css";
import AuthProvider from "./components/Context/AuthProvider";
import Navigation from "./routes/Navigation";
import { ToastContainer } from "react-toastify";
function App() {
  return (
    <>
      <AuthProvider>
        <Navigation />
        <ToastContainer />
      </AuthProvider>
    </>
  );
}

export default App;
