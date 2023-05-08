import { createBrowserRouter } from "react-router-dom";
import Login from "../Features/Login/Login";
import Dashboard from "../Features/Dashboard/Dashboard";
import Profile from "../Features/Dashboard/Profile/Profile"
import MyTimesheets from "../Features/Dashboard/MyTimesheets/MyTimesheets"
import MyRequestOff from "../Features/Dashboard/MyRequestOff/MyRequestOff"
import Error from "../Features/Error/Error";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <Dashboard />,
    children: [
      {
        path: "",
        element: <MyTimesheets />
      },
      {
        path: "profile",
        element: <Profile />
      },
      {
        path: "my-request-off",
        element: <MyRequestOff />
      },
    ],
  },
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "*",
    element: <Error />,
  }
]);