import { createBrowserRouter } from "react-router-dom";
import Login from "../Features/Login/Login";
import Dashboard from "../Features/Dashboard/Dashboard";
import Checkin from "../Features/Dashboard/Checkin/Checkin"

export const router = createBrowserRouter([
  {
    path: "/",
    element: <Dashboard />,
    children: [
      {
        path: "checkin",
        element: <Checkin />
      },
    ],
  },
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "/register",
    element: <Login />,
  }
]);