import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import PrivateRoutes from "./PrivateRoutes";
import Home from "../pages/Home/Home";
import NotFound from "../pages/NotFound/NotFound";
import Login from "../components/Auth/Login/Login";
import Register from "../components/Auth/Register/Register";
import PurchaseTickets from "../components/Tickets/PurchaseTickets/PurchaseTickets";
import SalesTickets from "../components/Tickets/SalesTickets/SalesTickets";
import UpcomingTickets from "../components/Wallet/UpcomingTickets/UpcomingTickets";
import CompletedTickets from "../components/Wallet/CompletedTickets/CompletedTickets";
import UserProfileView from "../components/UserProfile/UserProfileView/UserProfileView";
import UserProfileEdit from "./../components/UserProfile/UserProfileEdit/UserProfileEdit";
import ActiveOffers from "../components/Offers/ActiveOffers/ActiveOffers";
import CompletedOffers from "../components/Offers/CompletedOffers/CompletedOffers";
import PurchaseOrder from "../components/Orders/PurchaseOrder/PurchaseOrder";
import SalesOrder from "../components/Orders/SalesOrder/SalesOrder";
import ExtendedTicketView from "../components/Tickets/ExtendedTicketView/ExtendedTicketView";
import AddManualTicket from "../components/Tickets/AddManualTicket/AddManualTicket";
import BuyTicket from "../components/Tickets/BuyTicket/BuyTicket";
import AllTickets from "../components/Tickets/AllTickets/AllTickets";

const Navigation = () => {
  return (
    <Router>
      <Routes>
        <Route element={<PrivateRoutes />}>
          <Route path="/ticket/purchase/:ticketId" element={<BuyTicket/>} />
          <Route path="/ticket/:ticketId" element={<ExtendedTicketView />} />
          <Route path="/ticket/add" element={<AddManualTicket />} />
          <Route path="/wallet/upcoming/:idUser" element={<UpcomingTickets />} />
          <Route path="/wallet/completed/:idUser" element={<CompletedTickets />} />
          <Route path="/order/purchase" element={<PurchaseOrder />} />
          <Route path="/order/sales" element={<SalesOrder />} />
          <Route path="/offers/active" element={<ActiveOffers />} />
          <Route path="/offers/completed" element={<CompletedOffers />} />
          <Route path="/user/profile/edit" element={<UserProfileEdit />} />
        </Route>
        <Route element={<Home />} path="/" exact />
        <Route element={<Login />} path="/login" />
        <Route element={<Register />} path="/register" />
        <Route path="/tickets/purchase" element={<AllTickets />} />
        <Route path="/tickets/sale" element={<SalesTickets />} />
        <Route path="/tickets/purchase/event/:idEvent" element={<PurchaseTickets />} />
        <Route path="/tickets/sale/event/:idEvent" element={<SalesTickets />} />
        <Route path="/user/profile" element={<UserProfileView />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </Router>
  );
};

export default Navigation;
