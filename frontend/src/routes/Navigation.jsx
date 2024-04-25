import { createBrowserRouter, RouterProvider } from "react-router-dom";
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

const routes = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
  },
  {
    path: "/login",
    element: <Login/>,
  },
  {
    path: "/register",
    element: <Register />,
  },
  {
    path: "/wallet/upcoming",
    element: <UpcomingTickets />,
  },
  {
    path: "/wallet/completed",
    element: <CompletedTickets />,
  },
  {
    path: "/tickets/purchase",
    element: <PurchaseTickets />,
  },
  {
    path: "/tickets/sale",
    element: <SalesTickets />,
  },
  {
    path: "/ticket/add",
    element: <AddManualTicket/>
  },
  {
    path: "/ticket/1",
    element: <ExtendedTicketView/>
  },
  {
    path: "/offers/active",
    element: <ActiveOffers/>
  },
  {
    path:"/offers/completed",
    element: <CompletedOffers/>
  },
  {
    path: "/order/purchase",
    element: <PurchaseOrder/>
  },
  {
    path: "/order/sales",
    element: <SalesOrder/>
  },
  {
    path: "/user/profile",
    element: <UserProfileView />,
  },
  {
    path: "/user/profile/edit",
    element: <UserProfileEdit />,
  },
  {
    path: "/*",
    element: <NotFound />,
  },
]);

const Navigation = () => {
  return <RouterProvider router={routes} />;
};

export default Navigation;
