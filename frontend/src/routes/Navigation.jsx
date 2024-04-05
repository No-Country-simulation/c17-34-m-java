import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import Home from '../pages/Home/Home';
import NotFound from '../pages/NotFound/NotFound';

const routes = createBrowserRouter([
    {
      path: "/",
      element: <Home/>
    },
    {
        path: "/*",
        element: <NotFound/>
    },
  ]);
  
  const Navigation = () => {
    return <RouterProvider router={routes} />;
  };
  
  export default Navigation;